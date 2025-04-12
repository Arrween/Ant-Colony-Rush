package controller;

import java.awt.event.MouseEvent;
import model.ObjetFixe;
import model.Terrain;

public class DestinationSelectionnee {

    private Terrain terrain;
    private boolean active;
    private ObjetFixe depart;
    private Integer idFourmi;
    private Runnable onDestinationValidee;

    public DestinationSelectionnee(Terrain terrain) {
        this.terrain = terrain;
        this.active = false;
        this.depart = null;
        this.idFourmi = null;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(ObjetFixe dep, Integer id, Runnable callback) {
        this.active = true;
        this.depart = dep;
        this.idFourmi = id;
        this.onDestinationValidee = callback;
        System.out.println("Destination selection active.");
    }

    // Méthode à appeler lors d'un clic pour traiter la sélection de destination
    public void handleMouseClicked(MouseEvent e) {
        ObjetFixe dest = terrain.getEltClic(e.getX(), e.getY());
        if (dest != null) {
            terrain.deplacerFourmi(idFourmi, depart, dest);
            if (onDestinationValidee != null) {
                onDestinationValidee.run(); // Supprimer la carte uniquement après une destination valide
            }
            active = false;
            depart = null;
            idFourmi = null;
            onDestinationValidee = null;
        }
    }
}
