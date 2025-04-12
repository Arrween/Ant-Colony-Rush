package controller;

import model.Fourmi;
import model.ObjetFixe;
import view.PanneauCartesFourmis;

public class FourmiController {
    private PanneauCartesFourmis panneauCartes;

    public FourmiController(PanneauCartesFourmis panneauCartes) {
        this.panneauCartes = panneauCartes;
    }

    public void envoyerEnExpedition(Fourmi fourmi, ObjetFixe depart, DestinationSelectionnee ds) {
        // Activer la sélection de destination pour la fourmi
        ds.setActive(depart, fourmi.getId(), () -> {
            // Cette lambda sera appelée uniquement quand une destination valide est sélectionnée
            panneauCartes.supprimerCarte(fourmi);
        });
    }
}