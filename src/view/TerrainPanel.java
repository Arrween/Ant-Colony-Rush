package view;

import java.awt.*;
import javax.swing.*;
import model.Abri;
import model.DeplacementRessource;
import model.Nid;
import model.ObjetFixe;
import model.Ressource;
import model.Terrain;

public class TerrainPanel extends JPanel {
    private Terrain terrain;
    private ControlPanelListener controlListener;
    public static final int TAILLE_CRAPAUD = 160;
    public static final int TAILLE_FOURMIS = 40;
    public static final int TAILLE_OBJETS = (int) (2*ObjetFixe.HALF_SIZE * 1);

    public interface ControlPanelListener {
        void nidClicked(Nid nid);

        void ressourceClicked(Ressource ressource);

        void abriClicked(Abri abri);
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
                int x = obj.getX();
                int y = obj.getY();
                g.drawImage(img, x - TAILLE_OBJETS/2, y - TAILLE_OBJETS/2, TAILLE_OBJETS, TAILLE_OBJETS, this);
                //temporairement pour se repérer
                g.drawOval(x - 3, y - 3, 6, 6);

                // Dessiner un contour clignotant si l'objet est sélectionné
                if (obj.isSelected()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(Color.RED);
                    g2d.drawRect(x - TAILLE_OBJETS/2, y - TAILLE_OBJETS/2, TAILLE_OBJETS, TAILLE_OBJETS);
                }
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
                            x - TAILLE_FOURMIS/2,
                            y - TAILLE_FOURMIS/2,
                            TAILLE_FOURMIS,
                            TAILLE_FOURMIS,
                            this);
                    //temporairement pour se repérer
                    g.drawOval(x - 3, y - 3, 6, 6);
                }
            }
            // Affichage des ressources en déplacement
            if (dep instanceof DeplacementRessource) {
                Ressource ressource = ((DeplacementRessource) dep).getRessource();
                Image img = ressource.getImage();
                if (img != null) {
                    int x = dep.getX() - TAILLE_OBJETS/2;
                    int y = dep.getY() - TAILLE_OBJETS/2;
                    g.drawImage(img, x, y, TAILLE_OBJETS, TAILLE_OBJETS, this);
                }
            }
        }
        // Affichage du crapaud
        model.Crapaud crapaud = terrain.getCrapaud();
        if (crapaud != null) {
            Graphics2D g2d = (Graphics2D) g;
            int diametre = 2 * crapaud.getVisionRange();
            g2d.setColor(new Color(255, 0, 0, 50));
            g2d.fillArc(crapaud.getX() - crapaud.getVisionRange(), crapaud.getY() - crapaud.getVisionRange(), diametre, diametre, crapaud.getDirectionAngle() - 45, 90); 

            java.awt.image.BufferedImage frame = crapaud.getCurrentFrame();
            int x = crapaud.getX();
            int y = crapaud.getY();
            g.drawImage(frame, x - TAILLE_CRAPAUD/2, y - TAILLE_CRAPAUD/2, TAILLE_CRAPAUD, TAILLE_CRAPAUD, this);
            //temporairement pour se repérer
            g.drawOval(x - 3, y - 3, 6, 6);
        }

    }
}
