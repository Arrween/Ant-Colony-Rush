package controller;

import model.Score;
import model.Terrain;
import model.deplacements.DeplacementRessource;
import model.deplacements.DeplacementSimple;
import model.objetsFixes.Nid;
import model.objetsFixes.Ressource;
import model.threads.InterruptibleThread;
import model.threads.ThreadSet;

public class GestionScore extends InterruptibleThread {
    private Score score;
    private Nid nid;
    private boolean ajouterFourmiEvent = false;
    public final int DELAY = 100;
    private GestionScore gestionScore;
    private Terrain terrain;
    private ThreadSet threadSet;

    public GestionScore(Terrain terrain, Score score, Nid nid) {
        this.score = score;
        this.nid = nid;
        this.terrain = terrain;
    }

    public void setThreadSet(ThreadSet threadSet) {
        this.threadSet = threadSet;
    }

    public ThreadSet getThreadSet() {
        return threadSet;
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

    public boolean isGameOver() {
        // Vérifier s'il n'y a plus de fourmis dans le nid
        boolean plusDeFourmisDansNid = nid.getFourmis().isEmpty();

        // Vérifier s'il n'y a plus de fourmis en déplacement
        boolean plusDeFourmisEnDeplacement = terrain.getDeplacements().stream()
                .noneMatch(dep -> dep instanceof DeplacementSimple || dep instanceof DeplacementRessource);

        // Vérifier s'il n'y a plus de fourmis dans les autres objets fixes
        boolean plusDeFourmisDansObjetsFixes = Terrain.getObjetsFixes().stream()
                .allMatch(obj -> obj.getFourmis().isEmpty());

        // Le jeu se termine si toutes les conditions sont remplies
        return plusDeFourmisDansNid && plusDeFourmisEnDeplacement && plusDeFourmisDansObjetsFixes;
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