package view.panels;

import controller.DestinationSelectionnee;
import controller.GestionScore;

import java.awt.*;
import javax.swing.*;
import model.Fourmi;
import model.objetsFixes.Nid;

public class PanneauDeControle extends JPanel {
    private PanneauCartesFourmis panneauCartes;
    private GestionScore gestionScore;
    private JButton btnAjouterFourmis;

    public PanneauDeControle(Nid nid, DestinationSelectionnee ds, GestionScore gestionScore) {
        this.gestionScore = gestionScore;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // En-tête : Score et bouton retour
        JPanel panneauEntete = new JPanel(new BorderLayout());

        JButton btnRetour = new JButton("Retour");
        btnRetour.setPreferredSize(new Dimension(80, 40));
        btnRetour.addActionListener(e -> {
            Container parent = PanneauDeControle.this.getParent();
            if (parent instanceof ConteneurPanneauDeControle) {
                ((ConteneurPanneauDeControle) parent).afficherTableauDeBord();
            }
        });
        panneauEntete.add(btnRetour, BorderLayout.EAST);
        panneauEntete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panneauEntete, BorderLayout.NORTH);

        // Centre : Panneau des cartes
        panneauCartes = new PanneauCartesFourmis(nid, ds);
        add(panneauCartes, BorderLayout.CENTER);

        // Bas : Bouton "Ajouter des fourmis"
        btnAjouterFourmis = new JButton("Ajouter des fourmis");
        btnAjouterFourmis.setPreferredSize(new Dimension(300, 40));
        btnAjouterFourmis.addActionListener(e -> {
            if (gestionScore.getScore().AjoutFourmiPossible()) { // Vérifier si le score est suffisant
                Fourmi nouvelleFourmi = nid.ajouterFourmi(); // Ajouter une fourmi au nid
                gestionScore.ajouterFourmi();
                panneauCartes.ajouterCarte(nouvelleFourmi, ds, nid); // Ajouter une carte pour la nouvelle fourmi
                mettreAJourEtatBouton(); // Mettre à jour l'état du bouton
            }
        });
        add(btnAjouterFourmis, BorderLayout.SOUTH);

        // Initialiser l'état du bouton
        mettreAJourEtatBouton();
    }

    private void mettreAJourEtatBouton() {
        btnAjouterFourmis.setEnabled(gestionScore.getScore().AjoutFourmiPossible());
    }

    public void mettreAJourCartes() {
        panneauCartes.mettreAJourCartes();
    }
}