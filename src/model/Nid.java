package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Nid extends ObjetFixe {
    private int score;
    private Image imageNid; // L'image représentant le nid

    public Nid(int nbFourmis, int x, int y) {
        super(x, y, nbFourmis);
        this.score = 0;
        this.imageNid = new ImageIcon("../ressources/images/nid.png").getImage();
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.incrEnergie();
        }
    }
}
