package model;

import java.util.Random;

public class GestionnaireRessources extends Thread {
    private Terrain terrain;
    private Random rdm;
    private final int DELAY = 2000; // Vérification toutes les 2 secondes

    public GestionnaireRessources(Terrain terrain) {
        this.terrain = terrain;
        rdm = new Random();
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

            // pour les ressources Temporaires
            for (ObjetFixe o : Terrain.getObjetsFixes()) {
                if (o instanceof RessourceTemporaire) {
                    RessourceTemporaire ressourceTemp = (RessourceTemporaire) o;
                    // décrémenter temps restant
                    ressourceTemp.decrTempsRestant(DELAY);
                    // supprimer les ressources temporaires à échéance
                    // les fourmis dedans disparaissent avec
                    if (ressourceTemp.aDisparu()) {
                        terrain.removeRessource(ressourceTemp.getId());
                        ;
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