package model;

public class GestionnaireDeplacement extends Thread {
    private Terrain t;
    public final int DELAY = 100;

    public GestionnaireDeplacement(Terrain t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            t.updateDeplacements();
            t.supprimeDeplacementsFinis();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
