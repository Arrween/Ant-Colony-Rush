package view;

import controller.DestinationSelectionnee;
import controller.FourmiController;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import model.Abri;
import model.Fourmi;
import model.Nid;
import model.ObjetFixe;

public class PanneauCartesFourmis extends JPanel {
    private List<CardFourmis> cartesFourmis; // Liste des cartes
    private JPanel pnlCartes; // Panneau contenant les cartes
    private FourmiController controller; // Contrôleur pour gérer les actions (optionnel)

    // Constructeur pour un nid (avec gestion des destinations)
    public PanneauCartesFourmis(Nid nid, DestinationSelectionnee ds) {
        this(nid.getFourmis(), ds, nid, true);
    }

    // Constructeur pour un abri (avec gestion des destinations)
    public PanneauCartesFourmis(Abri abri, DestinationSelectionnee ds) {
        this(abri.getFourmis(), ds, abri, true);
    }

    // Constructeur générique
    private PanneauCartesFourmis(List<Fourmi> fourmis, DestinationSelectionnee ds, ObjetFixe objF,
            boolean avecControleur) {
        setLayout(new BorderLayout());
        cartesFourmis = new ArrayList<>();

        // Initialiser le contrôleur uniquement si nécessaire
        if (avecControleur) {
            controller = new FourmiController(this);
        }

        // Titre du panneau
        JLabel lblTitre = new JLabel("Détails des Fourmis", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitre, BorderLayout.NORTH);

        // Panneau pour afficher les cartes
        pnlCartes = new JPanel();
        pnlCartes.setLayout(new BoxLayout(pnlCartes, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(pnlCartes);
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter les cartes pour chaque fourmi
        for (Fourmi fourmi : fourmis) {
            CardFourmis card = new CardFourmis(fourmi, ds, objF, controller);
            cartesFourmis.add(card);
        }

        // Afficher les cartes triées par énergie décroissante
        afficherCartesTriees();
    }

    public void afficherCartesTriees() {
        cartesFourmis.sort(Comparator.comparingInt((CardFourmis c) -> c.getFourmi().getEnergie()).reversed());
        pnlCartes.removeAll();
        for (CardFourmis card : cartesFourmis) {
            pnlCartes.add(card);
        }
        revalidate();
        repaint();
    }

    public void mettreAJourCartes() {
        for (CardFourmis card : cartesFourmis) {
            card.mettreAJourEnergie();
        }
        afficherCartesTriees();
    }

    public void ajouterCarte(Fourmi fourmi, DestinationSelectionnee ds, ObjetFixe objF) {
        CardFourmis nouvelleCarte = new CardFourmis(fourmi, ds, objF, controller);
        cartesFourmis.add(nouvelleCarte);
        afficherCartesTriees(); // Réorganiser et afficher les cartes
    }

    public void supprimerCarte(Fourmi fourmi) {
        cartesFourmis.removeIf(card -> card.getFourmi().getId() == fourmi.getId());
        afficherCartesTriees(); // Réorganiser et afficher les cartes restantes
    }
}