package view;

import java.awt.*;
import javax.swing.*;
import model.DeplacementRessource;
import model.ObjetFixe;
import model.Ressource;
import model.Terrain;

public class TerrainPanel extends JPanel {
    private Terrain terrain;
    private ControlPanelListener controlListener;

    public interface ControlPanelListener {
        void nidClicked(model.Nid nid);

        void ressourceClicked(Ressource ressource);
    }

    public TerrainPanel(Terrain terrain) {
        this.terrain = terrain;
        setPreferredSize(new Dimension(Terrain.LARGEUR, Terrain.HAUTEUR));
    }

    public void setEcouteurPanneauDeControle(ControlPanelListener ecouteur) {
        this.controlListener = ecouteur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Affichage de l'arrière-plan :
        g.drawImage(Terrain.BACKGROUND, 0, 0, Terrain.LARGEUR, Terrain.HAUTEUR, this);
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
            SpriteAnimation anim = dep.getSpriteAnim();
            if (anim != null) {
                var frame = anim.getCurrentSprite();
                if (frame != null) {
                    int x = dep.getX();
                    int y = dep.getY();
                    g.drawImage(frame,
                            x,
                            y,
                            anim.getLargeurCadreCourant() / 10,
                            anim.getHauteurCadreCourant() / 10,
                            this);
                }
            }
            // Affichage des ressources en déplacement
            if (dep instanceof DeplacementRessource) {
                Ressource ressource = ((DeplacementRessource) dep).getRessource();
                Image img = ressource.getImage();
                if (img != null) {
                    int x = dep.getX() - ObjetFixe.HALF_SIZE;
                    int y = dep.getY() - ObjetFixe.HALF_SIZE;
                    g.drawImage(img, x, y, ObjetFixe.HALF_SIZE * 2, ObjetFixe.HALF_SIZE * 2, this);
                }
            }
        }
        // Affichage du crapaud
        model.Crapaud crapaud = terrain.getCrapaud();
        if (crapaud != null) {
            java.awt.image.BufferedImage frame = crapaud.getCurrentFrame();
            int drawX = crapaud.getX() - frame.getWidth() / 2;
            int drawY = crapaud.getY() - frame.getHeight() / 2;
            g.drawImage(frame, drawX, drawY, 100, 100, this);
        }

    }
}
