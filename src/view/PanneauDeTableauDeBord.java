package view;

import java.awt.*;
import javax.swing.*;
import model.Score;

public class PanneauDeTableauDeBord extends JPanel {
    private JLabel lblChrono;
    private JLabel lblScore;
    private JPanel pnlCrapaudInfos; // Informations sur le crapaud
    private Timer timer; // Swing Timer pour le chrono
    private int secondesEcoulees; // Compteur en secondes

    public PanneauDeTableauDeBord(Score scoreInstance) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Chrono
        lblChrono = new JLabel("Temps : 00:00", SwingConstants.CENTER);
        lblChrono.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblChrono);

        // Score
        lblScore = new JLabel("Score : 0", SwingConstants.CENTER);
        int score = scoreInstance.getScore();
         lblScore = new JLabel("Score : " + score, SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblScore);

        // Infos sur le crapaud
        pnlCrapaudInfos = new JPanel();
        pnlCrapaudInfos.setPreferredSize(new Dimension(300, 50));
        pnlCrapaudInfos.setBorder(BorderFactory.createTitledBorder("Infos Crapaud"));
        add(pnlCrapaudInfos);

        // Initialisation du timer
        secondesEcoulees = 0;
        timer = new Timer(1000, e -> {
            secondesEcoulees++;
            mettreAJourChrono(secondesEcoulees);
            mettreAJourScore(score);
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
    }

    public JPanel getPnlCrapaudInfos() {
        return pnlCrapaudInfos;
    }
}