package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Deplacement;
import model.Nid;
import model.ObjetFixe;
import model.Terrain;

public class TerrainPanel extends JPanel {
    private Terrain terrain;

    // Interface pour notifier le clic sur le nid
    public interface ControlPanelListener {
        void nidClicked(Nid nid);
    }

    private ControlPanelListener controlPanelListener;

    public TerrainPanel(Terrain terrain) {
        this.terrain = terrain;
        setPreferredSize(new Dimension(Terrain.LARGEUR, Terrain.HAUTEUR));

        // Ajout du listener pour détecter le clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // On demande au terrain quel objet a été cliqué
                ObjetFixe clickedObj = terrain.getEltClic(e.getX(), e.getY());
                if (clickedObj != null && clickedObj instanceof Nid) {
                    Nid nid = (Nid) clickedObj;
                    // On notifie l'écouteur que le nid a été cliqué
                    if (controlPanelListener != null) {
                        controlPanelListener.nidClicked(nid);
                    }
                }
            }
        });
    }

    public void setControlPanelListener(ControlPanelListener listener) {
        this.controlPanelListener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Parcours des objets fixes et affichage de leur image
        for (ObjetFixe obj : Terrain.GetObjetsFixes()) {
            Image img = obj.getImage();
            if (img != null) {
                // Affichage centré sur (x,y) avec une taille de 2 * HALF_SIZE
                int x = obj.getX() - ObjetFixe.HALF_SIZE;
                int y = obj.getY() - ObjetFixe.HALF_SIZE;
                g.drawImage(img, x, y, ObjetFixe.HALF_SIZE * 2, ObjetFixe.HALF_SIZE * 2, this);
            }
            if (obj.getNbFourmis() > 0) {
                // Affichage du nombre de fourmis
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(obj.getNbFourmis()), obj.getX() - 5, obj.getY() + 5);
            }
        }
        // parcours des Deplacements et affichage
        for (Deplacement dep : terrain.getDeplacements()) {
            g.drawLine(dep.getDepX(), dep.getDepY(), dep.getDestX(), dep.getDestY());
            g.drawOval(dep.getX(), dep.getY(), 5, 5);
        }
    }
}
