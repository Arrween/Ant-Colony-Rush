package controller;

import model.InterruptibleThread;
import model.Nid;
import model.Ressource;
import model.Score;

public class GestionScore extends InterruptibleThread {
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
        if (!ress.isMoving()) { // Vérifiez si la ressource est encore en déplacement
            return; // Si la ressource n'est plus en déplacement, ne rien faire
        }
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
            if (!isRunning) {
                try {
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            
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