package model;

import java.util.Random;

public class GestionnaireRessources extends InterruptibleThread {
    private Terrain terrain;
    private Random rdm;
    private final int DELAY = 1000; // Vérification toutes les 2 secondes

    public GestionnaireRessources(Terrain terrain) {
        this.terrain = terrain;
        rdm = new Random();
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
            
            synchronized (terrain) {
                if (terrain.nb_ressources < Terrain.NB_RESSOURCES_MAX) {
                    int ressourcesManquantes = Terrain.NB_RESSOURCES_MAX - terrain.nb_ressources;
                    if (ressourcesManquantes > 0) {
                        if (rdm.nextInt(5) == 0) {
                            // Créer une ressource temporaire
                            terrain.ajouterRessourceTemporaire();
                        } else {
                             // Créer une ressource normale
                             terrain.ajouterRessources(1);
                        }
                        
                    }
                    /*
                     * ajouter proba de 20% que ce soit terrain.ajouteRessourceTemporaire()
                     */
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