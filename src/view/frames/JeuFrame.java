package view.frames;

import controller.FourmiController;
import controller.GestionScore;
import controller.PauseController;
import controller.TerrainController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.MusicPlayer;
import model.Score;
import model.Terrain; // Assurez-vous d'importer la classe MusicPlayer
import model.objetsFixes.Abri;
import model.objetsFixes.Nid;
import model.objetsFixes.Ressource;
import model.threads.ThreadSet;
import view.animations.PanneauAnimationJourNuit;
import view.panels.AbriDetail;
import view.panels.ConteneurPanneauDeControle;
import view.panels.PanneauDeControle;
import view.panels.PausePanel;
import view.panels.RessourcesDetails;
import view.panels.TerrainPanel;

public class JeuFrame extends JFrame implements TerrainPanel.ControlPanelListener {
    private Terrain terrain;
    private TerrainPanel terrainPanel;
    private ConteneurPanneauDeControle conteneurPanneau;
    private PausePanel pausePanel;
    private TerrainController terrainControleur;
    private PauseController pauseController;
    private GestionScore gestionScore;
    private FourmiController fourmiController; // Ajout d'un contrôleur pour les fourmis
    private boolean isRunning = true; // Variable pour play/pause
    private PanneauAnimationJourNuit panneauAnimationJourNuit; // Panneau pour l'animation jour/nuit

    // Variable pour suivre l'état courant (jour/nuit)
    private boolean nightMode = false;

    public JeuFrame(Terrain t, TerrainPanel panel, GestionScore gestionScore, Score score, ThreadSet ts,
            FourmiController fourmiController) {
        setTitle("Ant Colony Rush");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.gestionScore = gestionScore;
        this.fourmiController = fourmiController;
        terrain = t;
        terrainPanel = panel;
        pausePanel = new PausePanel();
        terrainPanel.setEcouteurPanneauDeControle(this);

        // Initialisation du panneau de contrôle sur le côté droit
        conteneurPanneau = new ConteneurPanneauDeControle(score, terrain.getCrapaud(), gestionScore);
        conteneurPanneau.setPreferredSize(new Dimension(350, terrainPanel.getPreferredSize().height));
        add(conteneurPanneau, BorderLayout.EAST);

        add(terrainPanel, BorderLayout.CENTER);

        terrainControleur = new TerrainController(terrain, terrainPanel, this, gestionScore, fourmiController);
        terrainPanel.addMouseListener(terrainControleur);

        pauseController = new PauseController(this, ts);

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

    public JButton getResumeButton() {
        return pausePanel.getResumeButton();
    }

    public JButton getPauseButton() {
        return conteneurPanneau.getPauseButton();
    }

    public void resume() {
        isRunning = true;
        updateDisplay();
    }

    public void pause() {
        isRunning = false;
        updateDisplay();
    }

    private void updateDisplay() {
        getContentPane().removeAll();
        if (isRunning) {
            add(conteneurPanneau, BorderLayout.EAST);
            add(terrainPanel, BorderLayout.CENTER);
        } else {
            add(pausePanel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }
}
