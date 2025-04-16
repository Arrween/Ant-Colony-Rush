package model.objetsFixes;

import java.awt.*;
import javax.swing.*;
import model.Difficulte;
import model.Fourmi;

public class Abri extends ObjetFixe {
    private int capacite;
    private Image imageAbri; // L'image représentant l'abri
    private Difficulte difficulte;

    public Abri(int capacite, int x, int y, Difficulte d) {
        super(x, y, 0);
        this.capacite = capacite;
        this.imageAbri = new ImageIcon(getClass().getResource("/resources/Shelters/abri" + capacite + ".png"))
                .getImage();

        this.difficulte = d;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean isPlein() {
        return getNbFourmis() >= capacite;
    }

    @Override
    public Image getImage() {
        return imageAbri;
    }

    public void majEnergieFourmis() {
        if (difficulte == Difficulte.FACILE) {
            for (Fourmi fourmi : fourmis) {
                fourmi.incrEnergie();
            }
        } else if (difficulte == Difficulte.DIFFICILE) {
            for (Fourmi fourmi : fourmis) {
                fourmi.decrEnergie();
            }
        }
        // si difficulte == Difficule.NORMAL, on ne fait rien
    }
}
