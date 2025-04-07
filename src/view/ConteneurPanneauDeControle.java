package view;

import java.awt.*;
import javax.swing.*;
import model.Score;

public class ConteneurPanneauDeControle extends JPanel {
    private PanneauDeTableauDeBord panneauFixe; // Partie fixe
    private JPanel panneauDynamique; // Partie dynamique

    public ConteneurPanneauDeControle(Score score) {
        setLayout(new BorderLayout());

        // Partie fixe
        panneauFixe = new PanneauDeTableauDeBord(score);
        add(panneauFixe, BorderLayout.NORTH);

        // Partie dynamique
        panneauDynamique = new JPanel(new BorderLayout());
        panneauDynamique.setBorder(BorderFactory.createTitledBorder("Détails"));
        add(panneauDynamique, BorderLayout.CENTER);
    }

    public PanneauDeTableauDeBord getPanneauFixe() {
        return panneauFixe;
    }

    /**
     * Affiche un panneau de détail (par exemple, le panneau de contrôle d'un nid).
     */
    public void afficherPanneauDetail(JPanel detail) {
        panneauDynamique.removeAll();
        panneauDynamique.add(detail, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Revient à l'affichage par défaut (tableau de bord).
     */
    public void afficherTableauDeBord() {
        panneauDynamique.removeAll(); // Supprimer le contenu de la partie dynamique
        revalidate();
        repaint();
    }
}