package view;

import java.awt.*;
import javax.swing.*;
import model.Crapaud;
import model.Score;

public class ConteneurPanneauDeControle extends JPanel {
    private PanneauDeTableauDeBord panneauFixe; // Partie fixe
    private JPanel panneauDynamique; // Partie dynamique

    public ConteneurPanneauDeControle(Score score, Crapaud crapaud) {
        setLayout(new BorderLayout());

        // Partie fixe
        panneauFixe = new PanneauDeTableauDeBord(score, crapaud);
        add(panneauFixe, BorderLayout.NORTH);

        // Partie dynamique
        panneauDynamique = new JPanel(new BorderLayout());
        panneauDynamique.setBorder(BorderFactory.createTitledBorder("Détails"));
        add(panneauDynamique, BorderLayout.CENTER);
    }

    public PanneauDeTableauDeBord getPanneauFixe() {
        return panneauFixe;
    }

    public void afficherPanneauDetail(JPanel detail) {
        panneauDynamique.removeAll();
        panneauDynamique.add(detail, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void afficherTableauDeBord() {
        panneauDynamique.removeAll(); // Supprimer le contenu de la partie dynamique
        revalidate();
        repaint();
    }

    public JButton getPauseButton() {
        return panneauFixe.getPauseButton();
    }
}