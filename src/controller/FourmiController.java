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
        ds.setActive(depart, fourmi.getId());

        // Supprimer la carte de la fourmi du panneau
        panneauCartes.supprimerCarte(fourmi);
    }
}