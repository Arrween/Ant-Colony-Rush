package model;

public class GestionnaireSelection extends InterruptibleThread {
    private Terrain t;
    public final int DELAY = 200;

    public GestionnaireSelection(Terrain t) {
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

            for (ObjetFixe obj : Terrain.getObjetsFixes()) {
                if (obj.isSelected()) {
                    obj.setSelected(!obj.isSelected()); // Alterner l'état
                }
            }

            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}