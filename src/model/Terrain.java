package model;

import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 800;

    public static final Random RANDOM = new Random();

    static ArrayList<ObjetFixe> elts = new ArrayList<ObjetFixe>();
    ArrayList<Fourmi> fourmisEnExpe;

    public Terrain() {
        Random rand = new Random();

        int x = rand.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
        int y = rand.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
        // expliquer à la team
        elts = new ArrayList<ObjetFixe>();
        elts.add(new Nid(0, x, y));
        fourmisEnExpe = new ArrayList<Fourmi>();
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