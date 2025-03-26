package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Nid;
import model.ObjetFixe;
import model.Terrain;
import view.TerrainPanel;

public class TerrainController extends MouseAdapter {

    private Terrain terrain;
    private TerrainPanel.ControlPanelListener controlListener;
    private DestitationSelectionnee destSelector;

    public TerrainController(Terrain terrain, TerrainPanel.ControlPanelListener controlListener) {
        this.terrain = terrain;
        this.controlListener = controlListener;
        this.destSelector = new DestitationSelectionnee(terrain);
    }

    public DestitationSelectionnee getDestSelector() {
        return destSelector;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Si la sélection de destination est active, traiter cet événement
        if (destSelector.isActive()) {
            destSelector.handleMouseClicked(e);
            return;
        }
        // Sinon, vérifier si le clic concerne un Nid
        ObjetFixe clickedObj = terrain.getEltClic(e.getX(), e.getY());
        if (clickedObj instanceof Nid) {
            Nid nid = (Nid) clickedObj;
            if (controlListener != null) {
                controlListener.nidClicked(nid);
            }
        }
    }
}
