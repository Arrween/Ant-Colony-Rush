package view;

public class RedessinJeuFrame extends Thread {
    private JeuFrame gameFrame;
    public final int DELAY = 150;

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
