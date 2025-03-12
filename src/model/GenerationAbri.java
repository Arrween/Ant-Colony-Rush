package model;

import java.util.ArrayList;
import java.util.Random;

public class GenerationAbri {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;

    public static Abri genererAbri() {
        int[] capacité = { 1, 2, 3, 4, 5 };
        Abri abri = null;
        int RandomIndex = aleatoire.nextInt(5);

        int i = 0;
        while (i < 1) {
            boolean caseLibre = true;

            int c = capacité[RandomIndex];
            ArrayList<ObjetFixe> elts = Terrain.GetObjetsFixes();

            int x = aleatoire.nextInt(LARGEUR);
            int y = aleatoire.nextInt(HAUTEUR);

            for (ObjetFixe elt : elts) {
                if (elt.hitBoxCliquee(x, y)) {
                    caseLibre = false;
                    break;
                }
            }
            if (caseLibre) {
                abri = new Abri(c, x, y);
                i++;
            }
        }
        return abri;
    }
}