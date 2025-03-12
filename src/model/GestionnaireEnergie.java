package model;

public class GestionnaireEnergie extends Thread {
    private Terrain t;
    public final int DELAY = 100;

    public GestionnaireEnergie(Terrain t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            t.majEnergieFourmis();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
