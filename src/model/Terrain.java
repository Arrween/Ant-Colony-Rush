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
    ArrayList<Deplacement> expeditions;

    public Terrain() {
        // Initialiser la liste avant toute utilisation
        elts = new ArrayList<ObjetFixe>();
        expeditions = new ArrayList<Deplacement>();

        Random rand = new Random();
        int x = rand.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
        int y = rand.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

        // Création du Nid avec un nombre initial de fourmis (ici, 10)
        Nid nid = new Nid(20, x, y);
        elts.add(nid);

        // Ajout des abris et ressources de base
        ajouterAbris(15); // Exemple : ajout de 3 abris
        ajouterRessources(20); // Exemple : ajout de 5 ressources
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
        for (Deplacement d : expeditions) {
            d.decrEnergieFourmi();
        }
        for (ObjetFixe elt : elts) {
            elt.majEnergieFourmis();
        }
    }

    /*
     * public void supprimerDeplacement(int idDeplacement) {
     * expeditions.removeIf(d -> d.getId() == idDeplacement);
     * }
     */

    public void supprimeDeplacementsFinis() {
        expeditions.removeIf(d -> d.isDone());
    }

    public void deplacerFourmi(int idFourmi, ObjetFixe depart, ObjetFixe arrivee) {
        // retirer la fourmi de l'objet de départ
        Fourmi f = depart.getFourmiAndRemove(idFourmi);
        // créer une instance de déplacement avec la fourmi et la destination
        expeditions.add(new Deplacement(this, f, arrivee, depart));
    }

    public void avancerDeplacements() {
        for (Deplacement d : expeditions) {
            d.avancer();
        }
    }

    public ArrayList<Deplacement> getDeplacements() {
        return expeditions;
    }
}
