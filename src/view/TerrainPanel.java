package view;

import java.awt.*;
import javax.swing.*;
import model.ObjetFixe;
import model.Terrain;

public class TerrainPanel extends JPanel {
    private Terrain terrain;
    private ControlPanelListener controlListener;

    public interface ControlPanelListener {
        void nidClicked(model.Nid nid);
    }

    public TerrainPanel(Terrain terrain) {
        this.terrain = terrain;
        setPreferredSize(new Dimension(Terrain.LARGEUR, Terrain.HAUTEUR));
    }

    public void setControlPanelListener(ControlPanelListener listener) {
        this.controlListener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Affichage des objets fixes
        for (ObjetFixe obj : Terrain.getObjetsFixes()) {
            Image img = obj.getImage();
            if (img != null) {
                int x = obj.getX() - ObjetFixe.HALF_SIZE;
                int y = obj.getY() - ObjetFixe.HALF_SIZE;
                g.drawImage(img, x, y, ObjetFixe.HALF_SIZE * 2, ObjetFixe.HALF_SIZE * 2, this);
            }
            if (obj.getNbFourmis() > 0) {
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(obj.getNbFourmis()), obj.getX() - 5, obj.getY() + 5);
            }
        }
        // Affichage des déplacements
        for (var dep : terrain.getDeplacements()) {
            g.drawLine(dep.getDepX(), dep.getDepY(), dep.getDestX(), dep.getDestY());
            g.drawOval(dep.getX(), dep.getY(), 5, 5);
        }
    }
}
