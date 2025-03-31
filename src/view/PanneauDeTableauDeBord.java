package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Score;

public class PanneauDeTableauDeBord extends JPanel {
    private JLabel lblChrono;
    private JLabel lblScore;
    private Timer timer; // Swing Timer pour le chrono
    private int secondesEcoulees; // Compteur en secondes


    public PanneauDeTableauDeBord() {
        setLayout(new BorderLayout());

        lblChrono = new JLabel("Temps : 00:00", SwingConstants.CENTER);
        lblChrono.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblChrono, BorderLayout.NORTH);

        Score scoreInstance = new Score();
        int score = scoreInstance.getScore();
        lblScore = new JLabel("Score : " + score, SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblScore, BorderLayout.CENTER);

        JLabel lblStats = new JLabel("Statistiques : --", SwingConstants.CENTER);
        add(lblStats, BorderLayout.SOUTH);

        // Initialisation du timer : mise à jour toutes les 1000 ms (1 seconde)
        secondesEcoulees = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondesEcoulees++;
                mettreAJourChrono(secondesEcoulees);
            }
        });
        timer.start();
    }

    public void mettreAJourChrono(String temps) {
        lblChrono.setText("Temps : " + temps);
    }

    // Méthode d'aide pour mettre à jour le label chrono avec un format mm:ss
    public void mettreAJourChrono(int secondes) {
        int minutes = secondes / 60;
        int sec = secondes % 60;
        String tempsFormatte = String.format("%02d:%02d", minutes, sec);
        mettreAJourChrono(tempsFormatte);
    }

    public void mettreAJourScore(int score) {
        lblScore.setText("Score : " + score);
    }
}
