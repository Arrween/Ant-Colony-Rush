package model.objetsFixes;

import java.util.*;

import model.Terrain;

public class GenerationRessource {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;

    public static Ressource genererRessource() {
        int[] poids = { 1, 2, 3, 4 };
        int[] valeurNutritive = { 10, 30, 60, 100 };
        Ressource Ress = null;
        int RandomIndex = aleatoire.nextInt(4);

        int i = 0;
        while (i < 1) {
            boolean caseLibre = true;

            int p = poids[RandomIndex];
            int vn = valeurNutritive[RandomIndex];
            ArrayList<ObjetFixe> elts = Terrain.getObjetsFixes();

            int x = aleatoire.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
            int y = aleatoire.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

            for (ObjetFixe elt : elts) {
                if (elt.hitBoxRess(x, y)) {
                    caseLibre = false;
                    break;
                }
            }
            if (caseLibre) {
                Ress = new Ressource(p, vn, x, y);
                i++;
            }
        }

        return Ress;
    }
}
