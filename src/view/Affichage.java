package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import model.ObjetFixe;
import model.Terrain;

public class Affichage extends JPanel {
    public static final double RATIO_X = 1.0;
    public static final double RATIO_Y = 1.0;
    public static final Dimension PANELSIZE = new Dimension((int) (Terrain.LARGEUR * RATIO_X),
            (int) (Terrain.HAUTEUR * RATIO_Y));
    private Terrain t;

    public Affichage(Terrain ter) {
        this.setPreferredSize(PANELSIZE);
        this.t = ter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // afficher le fond
        g.drawImage(Terrain.BACKGROUND, 0, 0, (int) PANELSIZE.getWidth(), (int) PANELSIZE.getHeight(), this);
        // Afficher chacun des objets fixes du terrain
        for (ObjetFixe obj : Terrain.GetObjetsFixes()) {
            Image img = obj.getImage();
            if (img != null) {
                // Centrer l'image autour des coordonnées (x, y)
                g.drawImage(img, obj.getX() - ObjetFixe.HALF_SIZE, obj.getY() - ObjetFixe.HALF_SIZE,
                        2 * ObjetFixe.HALF_SIZE, 2 * ObjetFixe.HALF_SIZE, this);
            }
        }
    }
}
