package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.*;
import model.Terrain;

public class Affichage extends JPanel {
    private Image image;
    private int x;
    private int y;

    public static final double RATIO_X = 1.0;
    public static final double RATIO_Y = 1.0;
    public static final Dimension PANELSIZE = new Dimension((int) (Terrain.LARGEUR * RATIO_X),
                                                            (int) (Terrain.HAUTEUR * RATIO_Y));
    private Terrain t;
 
    public Affichage(Terrain ter) {
        this.setPreferredSize(PANELSIZE);
        this.t = ter;

        ImageIcon icon = new ImageIcon("ressources/nid.png");
        image = icon.getImage();

        this.x = Terrain.RANDOM.nextInt(Terrain.LARGEUR);
        this.y = Terrain.RANDOM.nextInt(Terrain.HAUTEUR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, 40, 40, this);
    }
}
