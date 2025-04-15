package view.panels;

import controller.DestinationSelectionnee;
import controller.GestionScore;
import java.awt.*;
import javax.swing.*;
import model.Fourmi;
import model.objetsFixes.Nid;
import view.components.BoutonImage;

public class PanneauDeControle extends JPanel {
    private PanneauCartesFourmis panneauCartes;
    private GestionScore gestionScore;
    private BoutonImage btnAjouterFourmis;

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 16);

    public PanneauDeControle(Nid nid, DestinationSelectionnee ds, GestionScore gestionScore) {
        this.gestionScore = gestionScore;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 500));
        setBackground(BEIGE);

        // En-tête : bouton Retour redimensionné (60x30)
        JPanel panneauEntete = new JPanel(new BorderLayout());
        panneauEntete.setBackground(BEIGE);
        BoutonImage btnRetour = new BoutonImage("/resources/Menu/back.png", "/resources/Menu/back_hover.png");
        btnRetour.addActionListener(e -> {
            Container parent = PanneauDeControle.this.getParent();
            while (parent != null && !(parent instanceof ConteneurPanneauDeControle)) {
                parent = parent.getParent();
            }
            if (parent instanceof ConteneurPanneauDeControle conteneur) {
                conteneur.afficherTableauDeBord();
            }
        });
        panneauEntete.add(btnRetour, BorderLayout.EAST);
        panneauEntete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panneauEntete, BorderLayout.NORTH);

        // Centre : affichage des cartes des fourmis
        panneauCartes = new PanneauCartesFourmis(nid, ds);
        add(panneauCartes, BorderLayout.CENTER);

        // Bas : bouton "Ajouter des fourmis" redimensionné (250x30)
        btnAjouterFourmis = new BoutonImage("/resources/Menu/add.png", "/resources/Menu/add_hover.png");
        btnAjouterFourmis.addActionListener(e -> {
            if (gestionScore.getScore().AjoutFourmiPossible()) {
                Fourmi nouvelleFourmi = nid.ajouterFourmi();
                gestionScore.ajouterFourmi();
                panneauCartes.ajouterCarte(nouvelleFourmi, ds, nid);
                mettreAJourEtatBouton();
            }
        });
        add(btnAjouterFourmis, BorderLayout.SOUTH);

        mettreAJourEtatBouton();
    }

    private void mettreAJourEtatBouton() {
        btnAjouterFourmis.setEnabled(gestionScore.getScore().AjoutFourmiPossible());
    }

    public void mettreAJourCartes() {
        panneauCartes.mettreAJourCartes();
    }
}
