package model;

import java.awt.*;
import javax.swing.*;

public class Abri extends ObjetFixe {
    private int capacite;
    private Image imageAbri; // L'image représentant l'abri

    public Abri(int capacite, int x, int y) {
        super(x, y, 0);
        this.capacite = capacite;
        this.imageAbri = new ImageIcon(getClass().getResource("/ressources/Shelters/abri" + capacite + ".png"))
                .getImage();
    }

    @Override
    public Image getImage() {
        return imageAbri;
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.decrEnergie();
        }
    }
}
