package view;

import controller.GestionScore;
import controller.TerrainController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private ConteneurPanneauDeControle conteneurPanneau;
    private TerrainController terrainControleur;
    private GestionScore gestionScore;
    
    // Variable pour suivre l'état courant (jour/nuit)
    private boolean nightMode = false;

    public JeuFrame(Terrain t, TerrainPanel panel, GestionScore gestionScore, Score score) {
        setTitle("Jeu Fourmis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.gestionScore = gestionScore;
        
        terrain = t;
        terrainPanel = panel;
        terrainPanel.setEcouteurPanneauDeControle(this);
        
        conteneurPanneau = new ConteneurPanneauDeControle(score);
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);
        
        add(terrainPanel, BorderLayout.CENTER);
        
        terrainControleur = new TerrainController(terrain, terrainPanel, this, gestionScore);
        terrainPanel.addMouseListener(terrainControleur);
        
        addKeyListener(terrainControleur);
        setFocusable(true);
        requestFocusInWindow();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Timer pour basculer le mode nuit toutes les 2 minutes (120000 ms)
        // On garde à 12 secondes pour le test, rajouter un 0 pour le vrai jeu
        Timer nightTimer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nightMode = !nightMode;  // On bascule le mode
                terrainPanel.setNightMode(nightMode);
            }
        });
        nightTimer.start();
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
