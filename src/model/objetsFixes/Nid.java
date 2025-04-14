package model.objetsFixes;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import model.Fourmi;

public class Nid extends ObjetFixe {
    private int score;
    private Image imageNid; // L'image représentant le nid

    public Nid(int nbFourmis, int x, int y) {
        super(x, y, nbFourmis);
        this.score = 0;
        this.imageNid = new ImageIcon(getClass().getResource("/resources/Not/nid2.png")).getImage();
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

    public void incrScore(int valeur) {
        score += valeur; // Incrémenter le score local
    }

    public int getScore() {
        return score;
    }

    public void decrScore(int valeur) {
        score -= valeur; // Décrémenter le score local
    }

    public Fourmi ajouterFourmi() {
        Fourmi nouvelleFourmi = new Fourmi(getX(), getY());
        fourmis.add(nouvelleFourmi);
        return nouvelleFourmi;
    }

    // Je surcharge la méthode ajouterFoumi avec un paramètre de liste de Fourmis
    // pour les fourmis qui y retournent après une expédition
    // pour ne pas créer de nouvelles fourmis à chaque fois
    public void ajouterFourmis(ArrayList<Fourmi> fourmisRetournees) {
        // Ajouter toutes les fourmis retournées au nid
        fourmis.addAll(fourmisRetournees);
    }

}
