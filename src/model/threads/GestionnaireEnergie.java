package model.threads;

import model.Terrain;

public class GestionnaireEnergie extends InterruptibleThread {
    private Terrain t;
    public final int DELAY = 200;

    public GestionnaireEnergie(Terrain t) {
        this.t = t;
    }

    @Override
    public void run() {
        while (true) {
            if (!isRunning) {
                try {
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }

            t.majEnergieFourmis();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
