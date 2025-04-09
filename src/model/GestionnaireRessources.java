package model;


public class GestionnaireRessources extends Thread {
    private Terrain terrain;
    private final int DELAY = 2000; // Vérification toutes les 2 secondes

    public GestionnaireRessources(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (terrain) {
                if (terrain.nb_ressources < Terrain.NB_RESSOURCES_MAX) {
                    int ressourcesManquantes = Terrain.NB_RESSOURCES_MAX - terrain.nb_ressources;
                    if (ressourcesManquantes > 0) {
                        terrain.ajouterRessources(1);
                    }
                }
            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}