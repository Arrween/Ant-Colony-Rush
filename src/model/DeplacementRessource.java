package model;

import controller.GestionScore;

public class DeplacementRessource extends Deplacement {
    private Ressource ressource;
    private GestionScore gestionScore;

    public DeplacementRessource(Terrain t, Ressource ressource, Nid nid,GestionScore gestionScore) {
        super(t, nid, ressource);
        this.ressource = ressource;
        this.gestionScore = gestionScore;
    }

    public Ressource getRessource() {
        return ressource;
    }

    @Override
    public void decrEnergieFourmi() {
        // Pas d'énergie à décrémenter pour une ressource
    }

    @Override
    public void avancer() {
        // Mise à jour de la position
        updatePosition();
        updateDirection();

        // Si le déplacement est terminé
        if (!isDone && currentX == destX && currentY == destY) {
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
        // Pas applicable pour une ressource
    }
}