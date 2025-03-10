package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import model.Terrain;

public class Affichage extends JPanel {
    public static final double RATIO_X = 1.0;
    public static final double RATIO_Y = 1.0;
    public static final Dimension PANELSIZE = new Dimension((int) (Terrain.LARGEUR * RATIO_X),
            (int) (Terrain.HAUTEUR * RATIO_Y));
    private Terrain t;

    public Affichage(Terrain ter) {
        super();
        this.setPreferredSize(PANELSIZE);
        this.t = ter;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

}

