package model;

import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    public static final int LARGEUR = 800;
    public static final int HAUTEUR = 800;
    public static final Random RANDOM = new Random();
    public static final int NB_RESSOURCES_MAX = 40;
    public int nb_ressources = 0;
    private Crapaud crapaud;
    private final int DIFFICULTE;

    // Liste des objets fixes présents sur le terrain (Nid, abris, ressources)
    static ArrayList<ObjetFixe> elts;
    ArrayList<Deplacement> expeditions;

    public Terrain(int difficulte) {
        DIFFICULTE = difficulte;

        // Initialiser la liste avant toute utilisation
        elts = new ArrayList<ObjetFixe>();
        this.expeditions = new ArrayList<Deplacement>();

        Random rand = new Random();
        int x = rand.nextInt(LARGEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;
        int y = rand.nextInt(HAUTEUR - 2 * ObjetFixe.HALF_SIZE) + ObjetFixe.HALF_SIZE;

        // Création du Nid avec un nombre initial de fourmis (ici, 10)
        Nid nid = new Nid(20, x, y);
        elts.add(nid);

        // Ajout des abris et ressources de base
        ajouterAbris(15); // Exemple : ajout de 3 abris
        ajouterRessources(NB_RESSOURCES_MAX); // Exemple : ajout de 5 ressources

        // Initialisation du crapaud
        int startX = RANDOM.nextInt(LARGEUR);
        int startY = RANDOM.nextInt(HAUTEUR);
        int visionRange = 100; // Valeur à ajuster selon vos besoins
        crapaud = new Crapaud(this, startX, startY, visionRange);
    }

    public static ArrayList<ObjetFixe> getObjetsFixes() {
        return elts;
    }

    public void ajouterRessources(int nombre) {
        for (int i = 0; i < nombre; i++) {
            Ressource ress = GenerationRessource.genererRessource();
            elts.add(ress);
        }
        nb_ressources += nombre;
    }

    public void ajouterAbris(int nombre) {
        for (int i = 0; i < nombre; i++) {
            Abri abris = GenerationAbri.genererAbri(DIFFICULTE);
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
        expeditions.add(new DeplacementSimple(this, f, arrivee, depart));
    }

    public void ramenerRessourceALaMaison(int idRessource, ObjetFixe depart) {
        // récuperer la ressource
        Ressource r = (Ressource) elts.stream().filter(elt -> elt.getId() == idRessource).findFirst().get();
        // retirer la ressource de la liste des objets fixes
        elts.removeIf(elt -> elt.getId() == idRessource);
        // créer une instance de transport avec la ressource et le nid
        expeditions.add(new Transport(this, r, depart));
    }

    public ArrayList<Deplacement> getDeplacements() {
        return expeditions;
    }

    public void updateDeplacements() {
        for (Deplacement dep : expeditions) {
            dep.avancer();
        }
    }

    public Crapaud getCrapaud() {
        return crapaud;
    }

    public void updateCrapaud() {
        crapaud.update(100); // valeur arbitraire pour l'instant
    }

    public Nid getNid() {
        return (Nid) elts.get(0);
    }

    public void removeRessource(int idRessource) {
        elts.removeIf(elt -> elt instanceof Ressource && elt.getId() == idRessource);
        nb_ressources--;
    }

    public void elimineFourmisMortesEnExpedition() {
        for (Deplacement d : expeditions) {
            d.eliminerFourmisMortes();
        }
    }

    public void elimineFourmisMortesEnAttente() {
        for (ObjetFixe elt : elts) {
            elt.eliminerFourmisMortes();
        }
    }

    public void ajouterRessource(Ressource r) {
        elts.add(r);
    }

    public void ajouterDeplacement(Deplacement deplacement) {
        expeditions.add(deplacement);
    }

}