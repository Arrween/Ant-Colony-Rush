package model;

import java.util.ArrayList;
import java.util.List;
import view.TerrainPanel;

public class GestionnaireFourmisMortes extends Thread {
    private Terrain t;
    public final int DELAY = 50;
    private TerrainPanel terrainPanel;

    public GestionnaireFourmisMortes(Terrain t, TerrainPanel terrainPanel) {
        this.t = t;
        this.terrainPanel = terrainPanel;
    }

    @Override
    public void run() {
        while (true) {
            // Affichage message de mort our les fourmis en expédition
            for (Deplacement dep : t.getDeplacements()) {
                if (dep instanceof DeplacementSimple) {
                    DeplacementSimple deplSimple = (DeplacementSimple) dep;
                    Fourmi fourmi = deplSimple.getFourmi();
                    if (fourmi.isDead()) {
                        // Ajouter un message de mort à l'emplacement de la fourmi
                        terrainPanel.addDeathMessage(dep.getX(), dep.getY());
                    }
                }
            }

            // Affichage message de mort pour les fourmis dans les objets fixes
            for (ObjetFixe obj : Terrain.getObjetsFixes()) {
                List<Fourmi> fourmisInitiales = new ArrayList<>(obj.getFourmis());
                for (Fourmi fourmi : fourmisInitiales) {
                    if (fourmi.isDead()) {
                        // Ajouter un message de mort à l'emplacement de l'objet fixe
                        terrainPanel.addDeathMessage(obj.getX(), obj.getY());
                    }
                }
            }

            t.elimineFourmisMortesEnExpedition();
            t.elimineFourmisMortesEnAttente();
            try {
                Thread.sleep(DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}