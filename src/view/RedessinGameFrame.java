package view;

public class RedessinGameFrame extends Thread {
    private JeuFrame gameFrame;
    public final int DELAY = 100;

    public RedessinGameFrame(JeuFrame gameFrame) {
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
