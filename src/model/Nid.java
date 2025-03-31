package model;

import java.awt.*;
import javax.swing.*;

public class Nid extends ObjetFixe {
    private int score;
    private Image imageNid; // L'image représentant le nid

    public Nid(int nbFourmis, int x, int y) {
        super(x, y, nbFourmis);
        this.score = 0;
        this.imageNid = new ImageIcon(getClass().getResource("/resources/Not/nid.png")).getImage();
    }

    @Override
    public Image getImage() {
        return imageNid;
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.incrEnergie();
        }
    }

    public void majScore() {

    }

    public int getScore() {
        return score;
    }

    public void incrScore(int valeur) {
        score += valeur;
    }

    public void ajouterFourmi() {
        fourmis.add(new Fourmi(getX(), getY()));
    }
}
