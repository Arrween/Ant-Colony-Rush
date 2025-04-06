package controller;

import model.Nid;
import model.Score;

public class GestionScore extends Thread {
    private Score score;
    private Nid nid;
    private boolean ajouterFourmiEvent = false;
    public final int DELAY = 100;

    public GestionScore(Score score, Nid nid) {
        this.score = score;
        this.nid = nid;
    }

    // public void ressourceRamenee(int poids) {
    //     score.augmenterScore(poids);
    // }

    public void ajouterFourmi() {
        if (score.AjoutFourmiPossible()) {
            score.diminuerScore();
            ajouterFourmiEvent = true;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (ajouterFourmiEvent) {
                nid.ajouterFourmi();
                ajouterFourmiEvent = false;
            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}