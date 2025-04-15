package view.panels;

import controller.GestionScore;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import model.Crapaud;
import model.Score;
import view.animations.PanneauAnimationJourNuit;

public class ConteneurPanneauDeControle extends JPanel {
    private PanneauDeTableauDeBord panneauFixe;
    private JPanel panneauDynamique;
    private PanneauAnimationJourNuit panneauAnimationJourNuit;
    private Timer timerJourNuit;
    private boolean isNight = false; // Variable pour suivre l'état jour/nuit

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 16);

    public ConteneurPanneauDeControle(Score score, Crapaud crapaud, GestionScore scoreGestionnaire) {
        setLayout(new BorderLayout());
        setBackground(BEIGE);
        setBorder(BorderFactory.createLineBorder(GREEN, 2));

        // Partie fixe : le tableau de bord
        panneauFixe = new PanneauDeTableauDeBord(score, crapaud, scoreGestionnaire);
        add(panneauFixe, BorderLayout.NORTH);

        // Partie dynamique : affichage des panneaux de détails
        panneauDynamique = new JPanel(new BorderLayout());
        panneauDynamique.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(GREEN, 1),
                "Détails",
                TitledBorder.CENTER, TitledBorder.TOP, CONTROL_HEADER, DARK_BROWN));
        panneauDynamique.setBackground(BEIGE);
        add(panneauDynamique, BorderLayout.CENTER);

        // Initialiser le panneau d'animation jour/nuit
        panneauAnimationJourNuit = new PanneauAnimationJourNuit();
        panneauDynamique.add(panneauAnimationJourNuit, BorderLayout.CENTER);

        initTimerJourNuit();
    }

    public PanneauDeTableauDeBord getPanneauFixe() {
        return panneauFixe;
    }

    private void initTimerJourNuit() {
        timerJourNuit = new Timer(10000, e -> { // Change toutes les 10 secondes
            isNight = !isNight;
            panneauAnimationJourNuit.animerJourNuit(isNight);
        });
        timerJourNuit.start();
    }

    public void afficherPanneauDetail(JPanel detail) {
        panneauDynamique.removeAll();
        panneauDynamique.add(detail, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void afficherTableauDeBord() {
        panneauDynamique.removeAll();
        panneauDynamique.add(panneauAnimationJourNuit, BorderLayout.CENTER); // Revenir au panneau par défaut
        revalidate();
        repaint();
    }

    public JButton getPauseButton() {
        return panneauFixe.getPauseButton();
    }
}
