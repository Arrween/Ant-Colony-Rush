package model;

import javax.swing.SwingUtilities;
import view.TerrainPanel;

/**
 * Ce thread gère la boucle de mise à jour du modèle :
 *  - Déplacements des fourmis
 *  - Gestion des déplacements terminés
 *  - Mise à jour du crapaud
 *  - Redessin de la vue via SwingUtilities.invokeLater()
 */


public class GestionnaireDeplacement extends Thread {
    private Terrain t;
    private TerrainPanel terrainPanel; // Référence au panneau pour le repaint
    public final int DELAY = 100;

    public GestionnaireDeplacement(Terrain t, TerrainPanel panel) {
        this.t = t;
        this.terrainPanel = panel;
    }

    @Override
    public void run() {
        while (true) {
            t.updateDeplacements();
            t.supprimeDeplacementsFinis();
            t.updateCrapaud();

            // Programmation d'un repaint()
            SwingUtilities.invokeLater(() -> {
                terrainPanel.repaint();
            });

            // Pause entre deux itérations
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
