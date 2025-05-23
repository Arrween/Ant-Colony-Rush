package view;

import view.frames.JeuFrame;

public class RedessinJeuFrame extends Thread {
    private JeuFrame gameFrame;
    public final int DELAY = 50;

    public RedessinJeuFrame(JeuFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        while (true) {
            gameFrame.repaint();
            gameFrame.revalidate();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
