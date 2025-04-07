package model;

import java.util.ArrayList;

public class Transport extends Deplacement {
    private Ressource ressource;

    public Transport(Terrain t, Ressource r, ObjetFixe depart) {
        super(t, t.getNid(), depart);
        this.ressource = r;
    }

    public void decrEnergieFourmi() {
        ressource.majEnergieFourmis();
    }

    public void avancer() {
        // algo de déplacmenent////
        // Mise à jour de la vitesse, position, direction et animation de la fourmi
        updatePosition();
        updateDirection();
        animationFourmi.updateFrame();

        // si déplacment fini, incrémenter score du nid, ajouter les fourmis au nid et
        // marquer comme fini
        if (!isDone && currentX == destX && currentY == destY) {
            ((Nid) dest).incrScore(ressource.getValeurNutritive());
            for (Fourmi f : ressource.getFourmis()) {
                dest.ajouterFourmi(f);
            }
            isDone = true;
        }
    }

    public void eliminerFourmisMortes() {
        ressource.eliminerFourmisMortes();
        if (!ressource.isReadyToGo()) {
            ressource.isDropped(currentX, currentY);
            t.ajouterRessource(ressource);
            t.expeditions.removeIf(d -> d.getId() == this.getId());
        }
    }
}