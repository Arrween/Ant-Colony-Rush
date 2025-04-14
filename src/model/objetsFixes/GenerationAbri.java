package model.objetsFixes;

import java.util.ArrayList;
import java.util.Random;

import model.Terrain;

public class GenerationAbri {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;

    public static Abri genererAbri(int difficulte) {
        int[] capacité = { 1, 2, 3 };
        Abri abri = null;
        int RandomIndex = aleatoire.nextInt(3);

        int i = 0;
        while (i < 1) {
            boolean caseLibre = true;

            int c = capacité[RandomIndex];
            ArrayList<ObjetFixe> elts = Terrain.getObjetsFixes();

            int x = aleatoire.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
            int y = aleatoire.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

            for (ObjetFixe elt : elts) {
                if (elt.hitBoxAbri(x, y)) {
                    caseLibre = false;
                    break;
                }
            }
            if (caseLibre) {
                abri = new Abri(c, x, y, difficulte);
                i++;
            }
        }
        return abri;
    }
}