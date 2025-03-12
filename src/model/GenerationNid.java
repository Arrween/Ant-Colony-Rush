package model;
import java.util.*;


public class GenerationNid {

    private static final Random aleatoire = new Random();
    private static final int HAUTEUR = Terrain.HAUTEUR;
    private static final int LARGEUR = Terrain.LARGEUR;


    public static Nid generNid() {
        int x = aleatoire.nextInt(LARGEUR); 
        int y = aleatoire.nextInt(HAUTEUR);
        return new Nid(0, x, y);
    }

}
