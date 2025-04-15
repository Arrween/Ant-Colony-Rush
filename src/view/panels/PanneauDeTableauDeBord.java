package view.panels;

import controller.GestionScore;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import model.Crapaud;
import model.Score;
import view.components.BoutonImage;
import view.frames.FenetreFinDeJeu;
import view.frames.MenuDemarrage;

public class PanneauDeTableauDeBord extends JPanel {
    private JLabel lblChrono;
    private JLabel lblScore;
    private JPanel pnlCrapaudInfos;
    private JProgressBar barreSatiete;
    private JLabel lblEtatCrapaud;
    private Timer timer;
    private int secondesEcoulees;
    private Score score;
    private GestionScore scoreGestionnaire;
    private Crapaud crapaud;
    private Timer satieteAnimationTimer;
    private JButton pauseBtn;
    private JLabel lblMeilleurScore;

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 16);

    public PanneauDeTableauDeBord(Score scoreInstance, Crapaud crapaud, GestionScore scoreGestionnaire) {
        this.scoreGestionnaire = scoreGestionnaire;
        this.score = scoreInstance;
        this.crapaud = crapaud;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(BEIGE);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setPreferredSize(new Dimension(300, 80));
        pnlHeader.setBackground(BEIGE);

        JPanel pnlScore = new JPanel();
        pnlScore.setLayout(new BoxLayout(pnlScore, BoxLayout.Y_AXIS));
        pnlScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlScore.setBackground(BEIGE);

        lblScore = new JLabel("Score : 0", SwingConstants.LEFT);
        lblScore.setFont(CONTROL_HEADER);
        lblScore.setForeground(DARK_BROWN);
        pnlScore.add(lblScore);

        lblMeilleurScore = new JLabel("Record : " + score.getMeilleurScore(), SwingConstants.CENTER);
        lblMeilleurScore.setFont(CONTROL_HEADER);
        lblMeilleurScore.setForeground(DARK_BROWN);
        pnlScore.add(lblMeilleurScore);

        pnlHeader.add(pnlScore, BorderLayout.SOUTH);

        JPanel pnlChronoPause = new JPanel(new BorderLayout());
        pnlChronoPause.setPreferredSize(new Dimension(300, 40));
        pnlChronoPause.setBackground(BEIGE);

        lblChrono = new JLabel("Temps : 00:00", SwingConstants.CENTER);
        lblChrono.setFont(CONTROL_HEADER);
        lblChrono.setForeground(DARK_BROWN);
        pnlChronoPause.add(lblChrono, BorderLayout.WEST);

        // Bouton pause redimensionné (60x30)
        pauseBtn = new BoutonImage("/resources/Menu/pause.png", "/resources/Menu/pause_hover.png");
        // pauseBtn.setPreferredSize(new Dimension(60, 30));
        pnlChronoPause.add(pauseBtn, BorderLayout.EAST);

        pnlHeader.add(pnlChronoPause, BorderLayout.NORTH);
        add(pnlHeader);

        pnlCrapaudInfos = new JPanel();
        pnlCrapaudInfos.setLayout(new BoxLayout(pnlCrapaudInfos, BoxLayout.Y_AXIS));
        pnlCrapaudInfos.setPreferredSize(new Dimension(300, 250));
        pnlCrapaudInfos.setBackground(BEIGE);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Infos Crapaud");
        titledBorder.setTitleFont(CONTROL_HEADER);
        titledBorder.setTitleColor(DARK_BROWN);
        pnlCrapaudInfos.setBorder(BorderFactory.createCompoundBorder(
                titledBorder,
                new EmptyBorder(10, 10, 10, 10)));

        JLabel lblSatiete = new JLabel("Satiété : ", SwingConstants.CENTER);
        lblSatiete.setFont(CONTROL_HEADER);
        lblSatiete.setPreferredSize(new Dimension(280, 30));
        lblSatiete.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSatiete.setForeground(DARK_BROWN);
        pnlCrapaudInfos.add(lblSatiete);

        barreSatiete = new JProgressBar(0, 100);
        barreSatiete.setStringPainted(true);
        barreSatiete.setPreferredSize(new Dimension(280, 30));
        pnlCrapaudInfos.add(barreSatiete);

        lblEtatCrapaud = new JLabel("", SwingConstants.CENTER);
        lblEtatCrapaud.setPreferredSize(new Dimension(150, 200));
        lblEtatCrapaud.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCrapaudInfos.add(lblEtatCrapaud);

        add(pnlCrapaudInfos);

        secondesEcoulees = 0;
        timer = new Timer(1000, e -> {
            secondesEcoulees++;
            mettreAJourChrono(secondesEcoulees);
            mettreAJourScore(score.getScore());
            mettreAJourCrapaudInfos();

            if (secondesEcoulees >= 5 || scoreGestionnaire.isGameOver()) {
                timer.stop();
                afficherFenetreFinDeJeu();
            }
        });
        timer.start();
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
        int satietePourcentage = (int) (crapaud.getSatiete() * 100);
        if (satieteAnimationTimer != null && satieteAnimationTimer.isRunning()) {
            satieteAnimationTimer.stop();
        }
        satieteAnimationTimer = new Timer(20, e -> {
            int currentValue = barreSatiete.getValue();
            if (currentValue < satietePourcentage) {
                barreSatiete.setValue(currentValue + 1);
            } else if (currentValue > satietePourcentage) {
                barreSatiete.setValue(currentValue - 1);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        satieteAnimationTimer.start();

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

    public JButton getPauseButton() {
        return pauseBtn;
    }

    private void afficherFenetreFinDeJeu() {
        String message = (secondesEcoulees >= 300)
                ? "Temps écoulé ! Fin du jeu."
                : "Plus de fourmis et score insuffisant ! Fin du jeu.";

        JFrame ancienneFenetre = (JFrame) SwingUtilities.getWindowAncestor(this);

        FenetreFinDeJeu fenetreFinDeJeu = new FenetreFinDeJeu(
                message,
                ancienneFenetre,
                e -> SwingUtilities.invokeLater(() -> new MenuDemarrage().setVisible(true)),
                e -> System.exit(0));
        fenetreFinDeJeu.setVisible(true);
    }
}
