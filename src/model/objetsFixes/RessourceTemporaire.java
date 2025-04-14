package model.objetsFixes;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import model.Terrain;

public class RessourceTemporaire extends Ressource {
    private int tempsRestant;

    // valeurs possibles
    private static final int[] POIDS = { 1, 2 };
    private static final int[] VALEURS_NUTRITIVES = { 200, 400 };
    private static final int[] DUREES = { 15000, 22000 };
    private Image imageRessource;

    public RessourceTemporaire(int p, int vn, int x, int y, int duree) {
        super(p, vn, x, y);
        this.tempsRestant = duree;
        imageRessource = new ImageIcon(getClass().getResource("/resources/Resources/ressourceTemp" + p + ".png"))
                .getImage();
    }

    @Override
    public Image getImage() {
        return imageRessource;
    }

    @Override
    public boolean canBeCollected() {
        return true;
    }

    public boolean aDisparu() {
        return tempsRestant <= 0;
    }

    public void decrTempsRestant(int time) { // avec time en ms
        tempsRestant -= time;
    }

    public int getTempsRestant() {
        return tempsRestant;
    }

    public String getTempsRestantString() {
        int secondes = tempsRestant / 1000;
        int minutes = secondes / 60;
        secondes = secondes % 60;
        return String.format("%02d:%02d", minutes, secondes);
    }

    public static RessourceTemporaire genererRessourceTemporaire() {
        Random aleatoire = new Random();
        int i = aleatoire.nextInt(2);

        int p = POIDS[i];
        int vn = VALEURS_NUTRITIVES[i];
        int d = DUREES[i];

        ArrayList<ObjetFixe> elts = Terrain.getObjetsFixes();

        int x, y;

        boolean caseLibre;

        while (true) {
            x = aleatoire.nextInt(Terrain.LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
            y = aleatoire.nextInt(Terrain.HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

            caseLibre = true;

            for (ObjetFixe elt : elts) {
                if (elt.hitBoxRess(x, y)) {
                    caseLibre = false;
                    break;
                }
            }

            if (caseLibre) {
                return new RessourceTemporaire(p, vn, x, y, d);
            }
        }

    }
}