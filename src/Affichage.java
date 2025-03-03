package src;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.Parcours;
import model.Position;

public class Affichage extends JPanel {
    public static final double RATIO_X = 1.0;
    public static final double RATIO_Y = 1.0;
    public static final Dimension PANELSIZE = new Dimension((int)(Terrain.LARGEUR * RATIO_X), (int)(Terrain.HAUTEUR * RATIO_Y));
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