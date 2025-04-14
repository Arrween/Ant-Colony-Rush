package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.GestionnaireEnergie;
import model.ThreadSet;
import view.JeuFrame;
import view.PanneauDeTableauDeBord;
import view.PausePanel;

public class PauseController{

    private JeuFrame gameFrame;
    private ThreadSet threadSet;

    public PauseController(JeuFrame jf, ThreadSet threadSet) {
        this.gameFrame = jf;
        this.threadSet = threadSet;
        initListeners();
    }

    private void initListeners() {
        gameFrame.getPauseButton().addActionListener(e -> 
            {
                threadSet.playPauseAll();
                gameFrame.pause();
            });
        gameFrame.getResumeButton().addActionListener(e -> 
            {
                threadSet.playPauseAll();
                gameFrame.resume();
            });
    }
}
