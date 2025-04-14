package controller;

import model.threads.ThreadSet;
import view.frames.JeuFrame;

public class PauseController {

    private JeuFrame gameFrame;
    private ThreadSet threadSet;

    public PauseController(JeuFrame jf, ThreadSet threadSet) {
        this.gameFrame = jf;
        this.threadSet = threadSet;
        initListeners();
    }

    private void initListeners() {
        gameFrame.getPauseButton().addActionListener(e -> {
            threadSet.playPauseAll();
            gameFrame.pause();
        });
        gameFrame.getResumeButton().addActionListener(e -> {
            threadSet.playPauseAll();
            gameFrame.resume();
        });
    }
}
