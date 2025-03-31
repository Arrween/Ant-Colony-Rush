package view;

import controller.TerrainController;
import java.awt.*;
import javax.swing.*;
import model.Nid;
import model.Ressource;
import model.Terrain;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private ConteneurPanneauDeControle conteneurPanneau;
    private TerrainController terrainControleur;

    public JeuFrame(Terrain t, TerrainPanel panel) {
        setTitle("Jeu Fourmis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du terrain et de sa vue
        terrain = t;
        terrainPanel = panel;
        terrainPanel.setEcouteurPanneauDeControle(this);
        add(terrainPanel, BorderLayout.CENTER);

        // Initialisation du panneau de contrôle sur le côté droit
        conteneurPanneau = new ConteneurPanneauDeControle();
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);

        // Création du contrôleur pour gérer les clics sur le terrain
        terrainControleur = new TerrainController(terrain, terrainPanel, this);
        terrainPanel.addMouseListener(terrainControleur);

        // Enregistrer le contrôleur comme écouteur de clavier
        addKeyListener(terrainControleur);
        setFocusable(true);
        requestFocusInWindow();

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

    @Override
    public void ressourceClicked(Ressource ressource) {
        RessourcesDetails panneauDetail = new RessourcesDetails(ressource);
        conteneurPanneau.afficherPanneauDetail(panneauDetail);
    }
}
