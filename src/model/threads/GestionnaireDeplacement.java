package model.threads;

import model.Terrain;
import view.panels.TerrainPanel;

/**
 * Ce thread gère la boucle de mise à jour du modèle :
 * - Déplacements des fourmis
 * - Gestion des déplacements terminés
 * - Mise à jour du crapaud
 * - Redessin de la vue via SwingUtilities.invokeLater()
 */

public class GestionnaireDeplacement extends InterruptibleThread {
    private Terrain t;
    private TerrainPanel terrainPanel; // Référence au panneau pour le repaint
    public final int DELAY = 50;

    public GestionnaireDeplacement(Terrain t, TerrainPanel panel) {
        this.t = t;
        this.terrainPanel = panel;
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

            // Mise à jour du modèle
            t.updateDeplacements();
            t.supprimeDeplacementsFinis();

            // Pause entre deux itérations
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
