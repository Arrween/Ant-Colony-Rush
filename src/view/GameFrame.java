package view;

import java.awt.*;
import javax.swing.*;
import model.Nid;
import model.Terrain;

public class GameFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private JPanel controlPanelContainer; // Panneau qui accueillera le PanneauDeControle

    public GameFrame(Terrain t) {
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

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Méthode appelée lorsque le nid est cliqué dans le TerrainPanel
    @Override
    public void nidClicked(Nid nid) {
        // On met à jour le panneau de contrôle avec les informations du nid
        controlPanelContainer.removeAll();
        // Ici, nous utilisons le score du nid et la liste de ses fourmis
        PanneauDeControle panneau = new PanneauDeControle(nid.getScore(), nid.getFourmis());
        controlPanelContainer.add(panneau, BorderLayout.CENTER);
        controlPanelContainer.revalidate();
        controlPanelContainer.repaint();
    }

}
