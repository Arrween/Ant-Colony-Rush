package view;

public class RepaintGameFrame extends Thread {
    private GameFrame gameFrame;
    public final int DELAY = 100;

    public RepaintGameFrame(GameFrame gameFrame) {
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
