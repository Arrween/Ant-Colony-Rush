package model;
import java.util.*;

public class GenerationRessource {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;


    public static Ressource[] genererRessources(int nombreDeRessources) {
        int[] poids = {1,2,3,4};
        int[] valeurNutritive = {10,30,60,100};

        Ressource[] ressources = new Ressource[nombreDeRessources];
        for (int i = 0; i < nombreDeRessources; i++) {
            int RandomIndex = aleatoire.nextInt(4);
            int p = poids[RandomIndex];
            int vn = valeurNutritive[RandomIndex];

            int x = aleatoire.nextInt(LARGEUR); 
            int y = aleatoire.nextInt(HAUTEUR);
            
            //TODO : éviter des objets fixes sur la meme case
            //Les étapes à suivre : génération du nid -> génération des ressources -> génération des abris
            ressources[i] = new Ressource(p, vn, x, y);
        }

        return ressources;
    }
}
