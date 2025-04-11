package view;

import controller.GestionScore;
import controller.TerrainController;
import java.awt.*;
import javax.swing.*;
import model.Abri;
import model.Nid;
import model.Ressource;
import model.Score;
import model.Terrain;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private ConteneurPanneauDeControle conteneurPanneau;
    private TerrainController terrainControleur;
    private GestionScore gestionScore;

    public JeuFrame(Terrain t, TerrainPanel panel, GestionScore gestionScore, Score score) {
        setTitle("Jeu Fourmis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.gestionScore = gestionScore;

        // Création du terrain et de sa vue
        terrain = t;
        terrainPanel = panel;
        terrainPanel.setEcouteurPanneauDeControle(this);

        // Initialisation du panneau de contrôle sur le côté droit
        conteneurPanneau = new ConteneurPanneauDeControle(score, terrain.getCrapaud());
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);

        // Ajouter le terrainPanel au centre
        add(terrainPanel, BorderLayout.CENTER);

        // Création du contrôleur pour gérer les clics sur le terrain
        terrainControleur = new TerrainController(terrain, terrainPanel, this, gestionScore);
        terrainPanel.addMouseListener(terrainControleur);

        // Enregistrer le contrôleur comme écouteur de clavier
        addKeyListener(terrainControleur);
        setFocusable(true);
        requestFocusInWindow();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void nidClicked(Nid nid) {
        PanneauDeControle panneauDetail = new PanneauDeControle(nid, terrainControleur.getDestSelector(), gestionScore);
        conteneurPanneau.afficherPanneauDetail(panneauDetail);
    }

    @Override
    public void ressourceClicked(Ressource ressource) {
        RessourcesDetails panneauDetail = new RessourcesDetails(ressource);
        conteneurPanneau.afficherPanneauDetail(panneauDetail);
    }

    @Override
    public void abriClicked(Abri abri) {
        AbriDetail panneauDetail = new AbriDetail(abri, terrainControleur.getDestSelector());
        conteneurPanneau.afficherPanneauDetail(panneauDetail);
    }
}