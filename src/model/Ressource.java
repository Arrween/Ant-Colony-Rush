package model;

import java.awt.*;
import javax.swing.*;
import controller.GestionScore;
import java.awt.image.BufferedImage;


public class Ressource extends ObjetFixe {
    private int poids;
    private int valeurNutritive;
    private BufferedImage[] imageRessource; // L'image représentant la ressource
    private boolean isMoving = false; // Indique si la ressource est en déplacement

    public Ressource(int poids, int vn, int x, int y) {
        super(x, y, 0);
        this.poids = poids;
        this.valeurNutritive = vn;
        try {
            if (poids == 1){
                this.imageRessource = ImageSplitter.getSixImages(1);
            }
            else if (poids == 2){
                this.imageRessource = ImageSplitter.getSixImages(3); 
            }
            else if (poids == 3){
                this.imageRessource = ImageSplitter.getSixImages(0); 
            }
            else if (poids == 4){
                this.imageRessource = ImageSplitter.getSixImages(15); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.imageRessource = new BufferedImage[6]; // Valeur par défaut en cas d'erreur
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    @Override
    public Image getImage() {
        return imageRessource[5];
    }
    public BufferedImage[] getAnnimRessource() {
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
