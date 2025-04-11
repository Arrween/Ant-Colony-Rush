package model;

import controller.GestionScore;
import java.awt.*;
import javax.swing.*;

public class Ressource extends ObjetFixe {
    private int poids;
    private int valeurNutritive;
    private Image imageRessource; // L'image représentant la ressource
    private boolean isMoving = false; // Indique si la ressource est en déplacement

    public Ressource(int poids, int vn, int x, int y) {
        super(x, y, 0);
        this.poids = poids;
        this.valeurNutritive = vn;
        this.imageRessource = new ImageIcon(getClass().getResource("/resources/Resources/ressource" + poids + ".png"))
                .getImage();
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    @Override
    public Image getImage() {
        return imageRessource;
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

    public boolean isReadyToGo() {
        return fourmis.size() >= poids;
    }

    public void isDropped(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacerVersNid(Terrain terrain, Nid nid, GestionScore gestionScore) {
        if (isReadyToGo() && !isMoving) {
            isMoving = true; // Marquer la ressource comme en déplacement
            DeplacementRessource deplacement = new DeplacementRessource(terrain, this, nid, gestionScore);
            terrain.ajouterDeplacement(deplacement);
            terrain.removeRessource(this.numero);
        }
    }
}
