package model;
import java.util.*;

public class GenerationRessource {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;


    public static Ressource genererRessources() {
        int[] poids = {1,2,3,4};
        int[] valeurNutritive = {10,30,60,100};
        Ressource Ress=null;

        int i = 0;
        while (i < 1) {
            boolean caseLibre = true;
            int RandomIndex = aleatoire.nextInt(4);
            int p = poids[RandomIndex];
            int vn = valeurNutritive[RandomIndex];
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
                Ress = new Ressource(p, vn, x, y);
                i++;
            }
        }

        return Ress;
    }
}
