package controller;

import model.Nid;
import model.Ressource;
import model.Score;

public class GestionScore extends Thread {
    private Score score;
    private Nid nid;
    private boolean ajouterFourmiEvent = false;
    public final int DELAY = 100;
    private GestionScore gestionScore;

    public GestionScore(Score score, Nid nid) {
        this.score = score;
        this.nid = nid;
    }

    public Score getScore() {
        return score;
    }

    public void ressourceRamenee(Ressource ress) {
        int vn = ress.getValeurNutritive();
        score.augmenterScore(vn);
    }

    public void setGestionScore(GestionScore gestionScore) {
        this.gestionScore = gestionScore;
    }

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