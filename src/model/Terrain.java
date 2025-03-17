package model;

import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 800;
    public static final Random RANDOM = new Random();

    // Liste des objets fixes présents sur le terrain (Nid, abris, ressources)
    static ArrayList<ObjetFixe> elts;
    ArrayList<Fourmi> fourmisEnExpe;

    public Terrain() {
        // Initialiser la liste avant toute utilisation
        elts = new ArrayList<ObjetFixe>();
        fourmisEnExpe = new ArrayList<Fourmi>();

        Random rand = new Random();
        int x = rand.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
        int y = rand.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

        // Création du Nid avec un nombre initial de fourmis (ici, 10)
        Nid nid = new Nid(20, x, y);
        elts.add(nid);

        // Ajout des abris et ressources de base
        ajouterAbris(155); // Exemple : ajout de 3 abris
        ajouterRessources(0); // Exemple : ajout de 5 ressources
    }

    public static ArrayList<ObjetFixe> GetObjetsFixes() {
        return elts;
    }

    public void ajouterRessources(int nombre) {
        for (int i = 0; i < nombre; i++) {
            Ressource ress = GenerationRessource.genererRessource();
            elts.add(ress);
        }
    }

    public void ajouterAbris(int nombre) {
        for (int i = 0; i < nombre; i++) {
            Abri abris = GenerationAbri.genererAbri();
            elts.add(abris);
        }
    }

    public ObjetFixe getEltClic(int x, int y) {
        for (ObjetFixe elt : elts) {
            if (elt.hitBoxCliquee(x, y)) {
                return elt;
            }
        }
        return null;
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmisEnExpe) {
            fourmi.decrEnergie();
        }
        for (ObjetFixe elt : elts) {
            elt.majEnergieFourmis();
        }
    }
}
