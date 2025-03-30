package view;

import controller.TerrainController;
import java.awt.*;
import javax.swing.*;
import model.Nid;
import model.Terrain;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private ConteneurPanneauDeControle conteneurPanneau;
    private TerrainController terrainControleur;

    public JeuFrame(Terrain t) {
        setTitle("Jeu des Fourmis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du terrain et de sa vue
        terrain = t;
        terrainPanel = new TerrainPanel(terrain);
        terrainPanel.setEcouteurPanneauDeControle(this);
        add(terrainPanel, BorderLayout.CENTER);

        // Initialisation du panneau de contrôle sur le côté droit
        conteneurPanneau = new ConteneurPanneauDeControle();
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);

        // Création du contrôleur pour gérer les clics sur le terrain
        terrainControleur = new TerrainController(terrain, terrainPanel, this);
        terrainPanel.addMouseListener(terrainControleur);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Méthode appelée lorsque le Nid est cliqué dans le TerrainPanel.
     * Affiche le panneau de détail associé au Nid dans le conteneur.
     */
    @Override
    public void nidClicked(Nid nid) {
        PanneauDeControle panneauDetail = new PanneauDeControle(nid, terrainControleur.getDestSelector());
        conteneurPanneau.afficherPanneauDetail(panneauDetail);
    }
}
