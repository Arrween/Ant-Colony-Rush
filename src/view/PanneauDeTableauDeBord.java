package view;

import controller.GestionScore;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import model.Crapaud;
import model.Score;

public class PanneauDeTableauDeBord extends JPanel {
    private JLabel lblChrono;
    private JLabel lblScore;
    private JPanel pnlCrapaudInfos; // Informations sur le crapaud
    private JProgressBar barreSatiete; // Barre de satiété
    private JLabel lblEtatCrapaud; // Image représentant l'état du crapaud
    private Timer timer; // Swing Timer pour le chrono
    private int secondesEcoulees; // Compteur en secondes
    private Score score;
    private GestionScore scoreGestionnaire; // Gestionnaire de score
    private Crapaud crapaud;
    private Timer satieteAnimationTimer;
    private JLabel lblMeilleurScore;

    public PanneauDeTableauDeBord(Score scoreInstance, Crapaud crapaud, GestionScore scoreGestionnaire) {
        this.scoreGestionnaire = scoreGestionnaire;
        this.score = scoreInstance;
        this.crapaud = crapaud;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setPreferredSize(new Dimension(300, 80));

        // Score et meilleur score

        JPanel pnlScore = new JPanel();
        pnlScore.setLayout(new BoxLayout(pnlScore, BoxLayout.Y_AXIS));
        pnlScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Score
        lblScore = new JLabel("Score : 0", SwingConstants.LEFT);
        lblScore.setFont(new Font("Arial", Font.BOLD, 16));
        pnlScore.add(lblScore);

        // Meilleur score
        lblMeilleurScore = new JLabel("Record : " + score.getMeilleurScore(), SwingConstants.CENTER);
        lblMeilleurScore.setFont(new Font("Arial", Font.BOLD, 16));
        pnlScore.add(lblMeilleurScore);

        pnlHeader.add(pnlScore, BorderLayout.SOUTH);

        JPanel pnlChronoPause = new JPanel(new BorderLayout());
        pnlChronoPause.setPreferredSize(new Dimension(300, 40));

        // Chrono
        lblChrono = new JLabel("Temps : 00:00", SwingConstants.CENTER);
        lblChrono.setFont(new Font("Arial", Font.BOLD, 16));
        pnlChronoPause.add(lblChrono, BorderLayout.WEST);

        // Pause
        JButton btnPause = new JButton("Pause");
        btnPause.setFont(new Font("Arial", Font.BOLD, 14));
        pnlChronoPause.add(btnPause, BorderLayout.EAST);

        pnlHeader.add(pnlChronoPause, BorderLayout.NORTH);

        add(pnlHeader);

        // Infos sur le crapaud
        pnlCrapaudInfos = new JPanel();
        pnlCrapaudInfos.setLayout(new BoxLayout(pnlCrapaudInfos, BoxLayout.Y_AXIS));
        pnlCrapaudInfos.setPreferredSize(new Dimension(300, 250));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Infos Crapaud");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        pnlCrapaudInfos.setBorder(BorderFactory.createCompoundBorder(
                titledBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Barre de satiété
        JLabel lblSatiete = new JLabel("Satiété : ", SwingConstants.CENTER);
        lblSatiete.setFont(new Font("Arial", Font.BOLD, 14));
        lblSatiete.setPreferredSize(new Dimension(280, 30));
        lblSatiete.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCrapaudInfos.add(lblSatiete);
        barreSatiete = new JProgressBar(0, 100); // Valeur entre 0 et 100
        barreSatiete.setStringPainted(true);
        barreSatiete.setPreferredSize(new Dimension(280, 30));
        pnlCrapaudInfos.add(barreSatiete);

        // Image de l'état du crapaud
        lblEtatCrapaud = new JLabel("", SwingConstants.CENTER);
        lblEtatCrapaud.setPreferredSize(new Dimension(150, 200));
        // Je veux centrer l'image dans le JPanel
        lblEtatCrapaud.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlCrapaudInfos.add(lblEtatCrapaud);

        add(pnlCrapaudInfos);

        // Initialisation du timer
        secondesEcoulees = 0;
        timer = new Timer(1000, e -> {
            secondesEcoulees++;
            mettreAJourChrono(secondesEcoulees);
            mettreAJourScore(score.getScore());
            mettreAJourCrapaudInfos(); // Mettre à jour les infos du crapaud

            // Vérifier les conditions de fin de jeu
            if (secondesEcoulees >= 300 || scoreGestionnaire.isGameOver()) { // 300 secondes = 5 minutes
                timer.stop(); // Arrêter le timer
                afficherFenetreFinDeJeu();
            }
        });
        timer.start(); // Démarrer le timer
    }

    public void mettreAJourChrono(String temps) {
        lblChrono.setText("Temps : " + temps);
    }

    public void mettreAJourChrono(int secondes) {
        int minutes = secondes / 60;
        int sec = secondes % 60;
        String tempsFormatte = String.format("%02d:%02d", minutes, sec);
        mettreAJourChrono(tempsFormatte);
    }

    public void mettreAJourScore(int score) {
        lblScore.setText("Score : " + score);
        lblMeilleurScore.setText("Record : " + this.score.getMeilleurScore());
    }

    private void mettreAJourCrapaudInfos() {
        // Calculer la nouvelle valeur cible de la satiété
        int satietePourcentage = (int) (crapaud.getSatiete() * 100);

        // Si un timer d'animation est déjà en cours, l'arrêter
        if (satieteAnimationTimer != null && satieteAnimationTimer.isRunning()) {
            satieteAnimationTimer.stop();
        }

        // Créer le timer d'animation pour une transition fluide
        satieteAnimationTimer = new Timer(20, e -> {
            int currentValue = barreSatiete.getValue();
            if (currentValue < satietePourcentage) {
                barreSatiete.setValue(currentValue + 1); // Augmenter progressivement
            } else if (currentValue > satietePourcentage) {
                barreSatiete.setValue(currentValue - 1); // Diminuer progressivement
            } else {
                ((Timer) e.getSource()).stop(); // Arrêter l'animation une fois la valeur atteinte
            }
        });
        satieteAnimationTimer.start();

        // Mise à jour de l'image de l'état du crapaud (inchangée)
        String etat = crapaud.getEtat();
        String imagePath;
        int width = 0;
        int height = 0;
        switch (etat) {
            case "faim":
                imagePath = "/resources/Frog/FROG_faim.png";
                width = 130;
                height = 200;
                break;
            case "sommeil":
                imagePath = "/resources/Frog/FROG_dormir.png";
                width = 120;
                height = 200;
                break;
            default:
                imagePath = "/resources/Frog/FROG_normal.png";
                width = 100;
                height = 200;
                break;
        }

        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        lblEtatCrapaud.setIcon(new ImageIcon(scaledImage));
    }

    public JPanel getPnlCrapaudInfos() {
        return pnlCrapaudInfos;
    }

    private void afficherFenetreFinDeJeu() {
        String message = (secondesEcoulees >= 300)
                ? "Temps écoulé ! Fin du jeu."
                : "Plus de fourmis et score insuffisant ! Fin du jeu.";

        JFrame ancienneFenetre = (JFrame) SwingUtilities.getWindowAncestor(this);

        FenetreFinDeJeu fenetreFinDeJeu = new FenetreFinDeJeu(
                message,
                ancienneFenetre, // Passer la référence de l'ancienne fenêtre
                e -> {
                    // Rejouer : relancer le jeu
                    SwingUtilities.invokeLater(() -> {
                        new MenuDemarrage().setVisible(true);
                    });
                },
                e -> {
                    // Quitter : fermer l'application
                    System.exit(0);
                });
        fenetreFinDeJeu.setVisible(true);
    }
}