package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Nid;
import model.ObjetFixe;
import model.Terrain;
import view.TerrainPanel;

public class TerrainController extends MouseAdapter {

    private Terrain terrain;
    private TerrainPanel terrainPanel;                  // Pour pouvoir faire un repaint()
    private TerrainPanel.ControlPanelListener controlListener;
    private DestinationSelectionnee destSelector;

    /**
     * Constructeur du contrôleur, recevant :
     *  - le modèle (Terrain)
     *  - la vue (TerrainPanel) pour pouvoir redessiner
     *  - le controlListener (pour la gestion du clic sur les Nids).
     */
    public TerrainController(Terrain terrain, TerrainPanel terrainPanel, 
                             TerrainPanel.ControlPanelListener controlListener) {
        this.terrain = terrain;
        this.terrainPanel = terrainPanel;
        this.controlListener = controlListener;

        // On initialise la sélection de destination
        this.destSelector = new DestinationSelectionnee(terrain); 
    }

    public DestinationSelectionnee getDestSelector() {
        return destSelector;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Si la sélection de destination est active, on délègue le clic
        if (destSelector.isActive()) {
            destSelector.handleMouseClicked(e);
            return;
        }
        // Sinon, on vérifie si le clic est sur un Nid
        ObjetFixe clickedObj = terrain.getEltClic(e.getX(), e.getY());
        if (clickedObj instanceof Nid) {
            Nid nid = (Nid) clickedObj;
            if (controlListener != null) {
                controlListener.nidClicked(nid);
            }
        }
    }
}
