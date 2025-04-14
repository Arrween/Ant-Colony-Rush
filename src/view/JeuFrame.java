package view;

import controller.FourmiController;
import controller.GestionScore;
import controller.TerrainController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Abri;
import model.MusicPlayer;
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
    private FourmiController fourmiController; // Ajout d'un contrôleur pour les fourmis

    // Variable pour suivre l'état courant (jour/nuit)
    private boolean nightMode = false;

    public JeuFrame(Terrain t, TerrainPanel panel, GestionScore gestionScore, Score score,
            FourmiController fourmiController) {
        setTitle("Ant Colony Rush");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.gestionScore = gestionScore;
        this.fourmiController = fourmiController;
        terrain = t;
        terrainPanel = panel;
        terrainPanel.setEcouteurPanneauDeControle(this);

        // Initialisation du panneau de contrôle sur le côté droit
        conteneurPanneau = new ConteneurPanneauDeControle(score, terrain.getCrapaud(), gestionScore);
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);

        add(terrainPanel, BorderLayout.CENTER);

        terrainControleur = new TerrainController(terrain, terrainPanel, this, gestionScore, fourmiController);
        terrainPanel.addMouseListener(terrainControleur);

        addKeyListener(terrainControleur);
        setFocusable(true);
        requestFocusInWindow();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Timer pour basculer le mode nuit toutes les 2 minutes (120000 ms)
        Timer nightTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Bascule du mode jour/nuit
                nightMode = !nightMode;
                terrainPanel.setNightMode(nightMode);

                // Jouer le son correspondant via MusicPlayer
                if (nightMode) {
                    MusicPlayer.stopDaySound();
                    MusicPlayer.playNightSound();
                } else {
                    MusicPlayer.stopNightSound();
                    MusicPlayer.playDaySound();
                }
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
