package model;

public class GestionnaireCrapaud extends Thread {
    private Crapaud c;
    public final int DELAY = 100;

    public GestionnaireCrapaud(Terrain t) {
        this.c = t.getCrapaud();
    }

    @Override
    public void run() {
        while (true) {
            c.update(DELAY);
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
