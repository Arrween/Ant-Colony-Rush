package model;
import javax.swing.*;
import java.awt.*;

public class Ressource extends ObjetFixe {
    private int poids;
    private int valeurNutritive;
    private Image imageRessource; // L'image représentant la ressource

    public Ressource(int poids, int vn, int x, int y) {
        super(x, y, 0);
        this.poids = poids;
        this.valeurNutritive = vn;
        this.imageRessource = new ImageIcon("../ressources/images/ressource" + poids + ".png").getImage();
    }

    public int getPoids() {
        return poids;
    }

    public int getValeurNutritive() {
        return valeurNutritive;
    }

    public int getNbrFourmis() {
        return fourmis.size();
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.decrEnergie();
        }
    }
}
