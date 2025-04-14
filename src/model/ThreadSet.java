package model;

import java.util.ArrayList;

import controller.GestionScore;
import view.JeuFrame;
import view.RedessinJeuFrame;
import view.TerrainPanel;

public class ThreadSet {
    private GestionnaireEnergie gestionnaireEnergie;
    private GestionnaireDeplacement gestionnaireDeplacement;
    private GestionnaireCrapaud gestionnaireCrapaud;
    private GestionnaireRessources gestionnaireRessources;
    private GestionnaireFourmisMortes gestionnaireFourmisMortes;
    private GestionnaireSelection gestionnaireSelection;
    private GestionScore gestionScore;

    public ThreadSet(Terrain t, TerrainPanel tp, Score s, Nid n) {
        this.gestionnaireEnergie = new GestionnaireEnergie(t);
        this.gestionnaireDeplacement = new GestionnaireDeplacement(t, tp);
        this.gestionnaireCrapaud = new GestionnaireCrapaud(t);
        this.gestionnaireRessources = new GestionnaireRessources(t);
        this.gestionnaireFourmisMortes = new GestionnaireFourmisMortes(t, tp);
        this.gestionnaireSelection = new GestionnaireSelection(t);
        this.gestionScore = new GestionScore(t, s, n);
    }

    public void startAll() {
        gestionnaireEnergie.start();
        gestionnaireDeplacement.start();
        gestionnaireCrapaud.start();
        gestionnaireRessources.start();
        gestionnaireFourmisMortes.start();
        gestionnaireSelection.start();
        gestionScore.start();
    }

    private ArrayList<InterruptibleThread> getAllThreads() {
        ArrayList<InterruptibleThread> threads = new ArrayList<>();
        threads.add(gestionnaireEnergie);
        threads.add(gestionnaireDeplacement);
        threads.add(gestionnaireCrapaud);
        threads.add(gestionnaireRessources);
        threads.add(gestionnaireFourmisMortes);
        threads.add(gestionnaireSelection);
        threads.add(gestionScore);
        return threads;
    }

    public void playPauseAll() {
        for (InterruptibleThread thread : getAllThreads()) {
            if (thread.isRunning()) {
                thread.pause();
            } else {
                thread.play();
            }
        }
    }

    public GestionScore getGestionScore() {
        return gestionScore;
    }
    
}
