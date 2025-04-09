package model;

public class GestionnaireFourmisMortes extends Thread {
    private Terrain t;
    public final int DELAY = 50;

    public GestionnaireFourmisMortes(Terrain t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            t.elimineFourmisMortesEnExpedition();
            t.elimineFourmisMortesEnAttente();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}