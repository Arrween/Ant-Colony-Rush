package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Nid extends ObjetFixe {
    private int cptRessources;
    private Image imageNid; // L'image représentant le nid

    public Nid(int cptRessources, int nbFourmis, int x, int y) {
        super(x, y, nbFourmis);
        this.cptRessources = cptRessources;
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.incrEnergie();
        }
    }
}
