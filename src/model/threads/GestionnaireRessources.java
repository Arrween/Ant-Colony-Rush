package model.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Terrain;
import model.objetsFixes.ObjetFixe;
import model.objetsFixes.Ressource;
import model.objetsFixes.RessourceTemporaire;

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
                        //10% de chance d'ajouter une ressource évènementielle
                        if (rdm.nextInt(10) == 0) {
                            terrain.ajouterRessourceTemporaire();
                        } else {
                            terrain.ajouterRessources(1);
                        }
                    }
                }
            }

            // pour les ressources Temporaires
            List<Ressource> aSupprimer = new ArrayList<>();
            for (ObjetFixe o : Terrain.getObjetsFixes()) {
                if (o instanceof RessourceTemporaire) {
                    RessourceTemporaire ressourceTemp = (RessourceTemporaire) o;
                    // décrémenter temps restant
                    ressourceTemp.decrTempsRestant(DELAY);
                    // supprimer les ressources temporaires à échéance
                    // les fourmis dedans disparaissent avec
                    if (ressourceTemp.aDisparu()) {
                        aSupprimer.add(ressourceTemp);
                    }
                }
            }
            for (Ressource r : aSupprimer) {
                terrain.removeRessource(r.getId());
            }

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}