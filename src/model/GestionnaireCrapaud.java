package model;

public class GestionnaireCrapaud extends InterruptibleThread {
    private Crapaud c;
    public final int DELAY_ACTION = 50;
    public final int DELAY_DECR_SATIETE = 120; // en nombre de fois le delay d'action

    public GestionnaireCrapaud(Terrain t) {
        this.c = t.getCrapaud();
    }

    @Override
    public void run() {
        while (true) {
            if (!isRunning) {
                try {
                    Thread.sleep(DELAY_ACTION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            
            c.decrSatiete();
            for (int i = 0; i < DELAY_DECR_SATIETE; i++) {
                c.update(DELAY_ACTION);
                try {
                    Thread.sleep(DELAY_ACTION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
