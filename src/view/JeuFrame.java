package view;

import controller.TerrainController;
import java.awt.*;
import javax.swing.*;
import model.Nid;
import model.Terrain;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private JPanel controlPanelContainer; // Panneau qui accueillera le PanneauDeControle
    private TerrainController compositeController;

    public JeuFrame(Terrain t) {
        setTitle("Jeu Fourmis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du terrain et du TerrainPanel
        terrain = t;
        terrainPanel = new TerrainPanel(terrain);
        terrainPanel.setControlPanelListener(this);
        add(terrainPanel, BorderLayout.CENTER);

        // Panneau de contrôle (vide au départ) sur le côté droit
        controlPanelContainer = new JPanel(new BorderLayout());
        controlPanelContainer.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(controlPanelContainer, BorderLayout.EAST);

        // Création du contrôleur composite et ajout au TerrainPanel
        compositeController = new TerrainController(terrain, terrainPanel, this);
        terrainPanel.addMouseListener(compositeController);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Méthode appelée lorsque le Nid est cliqué dans le TerrainPanel
    @Override
    public void nidClicked(Nid nid) {
        // Met à jour le panneau de contrôle avec les informations du Nid
        controlPanelContainer.removeAll();
        PanneauDeControle panneau = new PanneauDeControle(nid, compositeController.getDestSelector());
        controlPanelContainer.add(panneau, BorderLayout.CENTER);
        controlPanelContainer.revalidate();
        controlPanelContainer.repaint();
    }
}
