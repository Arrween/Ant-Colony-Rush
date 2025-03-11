package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 800;

    ArrayList<ObjetFixe> elts;
    ArrayList<Fourmi> fourmisEnExpe;

    public Terrain() {
        Random rand = new Random();
        int x = rand.nextInt(LARGEUR);
        int y = rand.nextInt(HAUTEUR);
        elts.add(new Nid(0, 10, x, y));
        elts = new ArrayList<ObjetFixe>();
        fourmisEnExpe = new ArrayList<Fourmi>();
    }

    public ObjetFixe getEltClic(int x, int y){
        for (ObjetFixe elt : elts) {
            if (elt.hitBoxCliquee(x, y)) {
                return elt;
            }
        }
        return null;
    }

    public void majEnergieFourmis(){
        for (Fourmi fourmi : fourmisEnExpe) {
            fourmi.decrEnergie();
        }
        for(ObjetFixe elt : elts){
            elt.majEnergieFourmis();
        }
    }
}