package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Terrain {
    public static final int LARGEUR = 800;
    public static final int HAUTEUR = 800;
    public static final Random RANDOM = new Random();


    public static final Image BACKGROUND = new ImageIcon("/ressources/Grass/Grass_01-128x128.png").getImage();

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
        ajouterAbris(20); // Je fixe le max à 40
        ajouterRessources(50); // Je fixe le max à 200
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