package model.deplacements;

import controller.GestionScore;
import model.Fourmi;
import model.Terrain;
import model.objetsFixes.Nid;
import model.objetsFixes.Ressource;

public class DeplacementRessource extends Deplacement {
    private Ressource ressource;
    private GestionScore gestionScore;

    public DeplacementRessource(Terrain t, Ressource ressource, Nid nid, GestionScore gestionScore) {
        super(t, nid, ressource);
        this.ressource = ressource;
        this.gestionScore = gestionScore;
    }

    public Ressource getRessource() {
        return ressource;
    }

    @Override
    public void decrEnergieFourmi() {
        for (Fourmi fourmi : ressource.getFourmis()) {
            fourmi.decrEnergie();
        }
    }

    @Override
    public void avancer() {
        if (isDone) {
            return; // Si le déplacement est déjà terminé, ne rien faire
        }

        // Mise à jour de la position
        updatePosition();
        updateDirection();

        // Si le déplacement est terminé
        if (currentX == destX && currentY == destY) {
            // Ajouter la ressource au nid
            ((Nid) dest).incrScore(ressource.getValeurNutritive());
            ((Nid) dest).ajouterFourmis(ressource.getFourmis());

            // Supprimer la ressource du terrain
            t.removeRessource(ressource.getId());

            // Réinitialiser l'état de la ressource
            ressource.setMoving(false);

            // Marquer le déplacement comme terminé
            isDone = true;

            // Mettre à jour le score
            gestionScore.ressourceRamenee(ressource);

        }
    }

    @Override
    public void eliminerFourmisMortes() {
        // Éliminer les fourmis mortes de la ressource
        ressource.eliminerFourmisMortes();

        // Si la ressource n'a plus assez de fourmis pour être transportée
        if (!ressource.isReadyToGo()) {
            // Déposer la ressource à la position actuelle
            ressource.isDropped(currentX, currentY);

            // Réinitialiser l'état de la ressource
            ressource.setMoving(false);

            // Ajouter la ressource au terrain à sa nouvelle position
            t.ajouterRessource(ressource);

            // Supprimer ce déplacement
            t.removeDeplacement(this.getId());
            ;
        }
    }
}