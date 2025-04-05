package view;

import controller.DestinationSelectionnee;
import java.awt.*;
import javax.swing.*;
import model.Fourmi;
import model.Nid;

public class PanneauDeControle extends JPanel {
    private PanneauCartesFourmis panneauCartes;

    public PanneauDeControle(Nid nid, DestinationSelectionnee ds) {
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
        JButton btnAjouterFourmis = new JButton("Ajouter des fourmis");
        btnAjouterFourmis.setPreferredSize(new Dimension(300, 40));
        btnAjouterFourmis.addActionListener(e -> {
            Fourmi nouvelleFourmi = nid.ajouterFourmi(); // Ajouter une fourmi au nid
            panneauCartes.ajouterCarte(nouvelleFourmi, ds, nid); // Ajouter une carte pour la nouvelle fourmi
        });
        add(btnAjouterFourmis, BorderLayout.SOUTH);
    }

    public void mettreAJourCartes() {
        panneauCartes.mettreAJourCartes();
    }
}