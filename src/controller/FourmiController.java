package controller;

import model.Fourmi;
import model.objetsFixes.Abri;
import model.objetsFixes.ObjetFixe;
import view.panels.PanneauCartesFourmis;

public class FourmiController {
    private PanneauCartesFourmis panneauCartes;

    public FourmiController(PanneauCartesFourmis panneauCartes) {
        this.panneauCartes = panneauCartes;
    }

    public void envoyerEnExpedition(Fourmi fourmi, ObjetFixe depart, DestinationSelectionnee ds) {
        ds.setActive(depart, fourmi.getId(), () -> {
            panneauCartes.supprimerCarte(fourmi);
        });
    }

    // Vérifie si la destination est valide pour la fourmi
    public boolean isDestinationValide(ObjetFixe destination) {
        if (destination instanceof Abri) {
            return !((Abri) destination).isPlein();
        }
        return true;
    }
}