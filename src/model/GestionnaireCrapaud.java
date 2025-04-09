package model;

public class GestionnaireCrapaud extends Thread {
    private Crapaud c;
    public final int DELAY_ACTION = 200;
    public final int DELAY_DECR_SATIETE = 30; //en nombre de fois le delay d'action

    public GestionnaireCrapaud(Terrain t) {
        this.c = t.getCrapaud();
    }

    @Override
    public void run() {
        while (true) {

            c.decrSatiete();

            for (int i = 0; i<DELAY_DECR_SATIETE; i++) {
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
