package view.panels;

import controller.DestinationSelectionnee;
import controller.FourmiController;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Fourmi;
import model.objetsFixes.Abri;
import model.objetsFixes.Nid;
import model.objetsFixes.ObjetFixe;

public class PanneauCartesFourmis extends JPanel {
    private List<CardFourmis> cartesFourmis;
    private JPanel pnlCartes;
    private FourmiController controller;

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 14);

    // Constructeur pour un nid
    public PanneauCartesFourmis(Nid nid, DestinationSelectionnee ds) {
        this(nid.getFourmis(), ds, nid);
    }

    // Constructeur pour un abri
    public PanneauCartesFourmis(Abri abri, DestinationSelectionnee ds) {
        this(abri.getFourmis(), ds, abri);
    }

    // Constructeur générique
    private PanneauCartesFourmis(List<Fourmi> fourmis, DestinationSelectionnee ds, ObjetFixe objF) {
        setLayout(new BorderLayout());
        setBackground(BEIGE);

        cartesFourmis = new ArrayList<>();
        controller = new FourmiController(this);

        // Titre
        JLabel lblTitre = new JLabel("Détails des Fourmis", SwingConstants.CENTER);
        lblTitre.setFont(CONTROL_HEADER);
        lblTitre.setForeground(DARK_BROWN);
        lblTitre.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(lblTitre, BorderLayout.NORTH);

        // Panel où seront empilées toutes les CardFourmis
        pnlCartes = new JPanel();
        pnlCartes.setLayout(new BoxLayout(pnlCartes, BoxLayout.Y_AXIS));
        pnlCartes.setBackground(BEIGE);

        // On enrobe dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(pnlCartes);
        scrollPane.setBorder(null); // pas de bordure visible
        add(scrollPane, BorderLayout.CENTER);

        for (Fourmi fourmi : fourmis) {
            ajouterCarte(fourmi, ds, objF);
        }

        afficherCartesTriees();
    }

    public void afficherCartesTriees() {
        cartesFourmis.sort(Comparator.comparingInt((CardFourmis c) -> c.getFourmi().getEnergie()).reversed());
        pnlCartes.removeAll();
        for (CardFourmis card : cartesFourmis) {
            pnlCartes.add(card);
            // Petit espace entre chaque carte
            pnlCartes.add(Box.createVerticalStrut(5));
        }
        pnlCartes.revalidate();
        pnlCartes.repaint();
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
    }

    public void supprimerCarte(Fourmi fourmi) {
        cartesFourmis.removeIf(card -> card.getFourmi().getId() == fourmi.getId());
        afficherCartesTriees();
    }
}
