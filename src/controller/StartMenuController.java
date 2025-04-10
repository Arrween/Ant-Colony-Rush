package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.GestionnaireCrapaud;
import model.GestionnaireDeplacement;
import model.GestionnaireEnergie;
import model.GestionnaireRessources;
import model.GestionnaireFourmisMortes;
import model.GestionnaireSelection;
import model.MusicPlayer;
import model.Score;
import model.Terrain;
import view.DifficultePanel;
import view.JeuFrame;
import view.MenuDemarrage;
import view.RedessinJeuFrame;
import view.TerrainPanel;

/**
 * Contrôleur pour le menu de démarrage (StartMenu).
 * Gère les actions sur les boutons Start et Difficulty.
 */
public class StartMenuController {

    private MenuDemarrage startMenuFrame;
    private DifficultePanel difficultePanel;

    /**
     * Constructeur qui reçoit la fenêtre du menu de démarrage
     * et récupère le panneau de difficulté.
     */
    public StartMenuController(MenuDemarrage frame) {
        this.startMenuFrame = frame;
        this.difficultePanel = frame.getDifficultePanel();
        initController();
    }

    /**
     * Méthode d'initialisation des écouteurs sur les boutons
     * Start et Difficulty.
     */
    private void initController() {
        // Écouteur sur le bouton "Start"
        difficultePanel.setStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });

        // Écouteur sur le bouton "Difficulté"
        difficultePanel.setDifficulteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDifficultyDialog();
            }
        });
    }

    /**
     * Lance la partie. Ici, nous créons le Terrain, les gestionnaires
     * (énergie, déplacement), la fenêtre de jeu, etc.
     */
    private void launchGame() {
        // Récupération de la difficulté sélectionnée si besoin :
        // String difficulty = difficultePanel.getDifficulteSelectionnee();

        // Création du modèle principal
        Terrain terrain = new Terrain(1); //pour l'instant difficulté à 1 par défaut
        TerrainPanel terrainPanel = new TerrainPanel(terrain);

        MusicPlayer.stopMusicMenu();
        MusicPlayer.playMusic(); // Lecture de la musique de jeu
        MusicPlayer.playDaySound(); // Lecture du son de jour

        // Création des gestionnaires (Threads)
        GestionnaireEnergie gestionnaireEnergie = new GestionnaireEnergie(terrain);
        GestionnaireDeplacement gestionnaireDeplacement = new GestionnaireDeplacement(terrain, terrainPanel);
        GestionnaireCrapaud gestionnaireCrapaud = new GestionnaireCrapaud(terrain);
        GestionnaireRessources gestionnaireRessources = new GestionnaireRessources(terrain);
        GestionnaireFourmisMortes gestionnaireFourmisMortes = new GestionnaireFourmisMortes(terrain);
        GestionnaireSelection gestionnaireSelection = new GestionnaireSelection(terrain);

        Score score = new Score(terrain.getNid());

        // thread de gestion du score
        GestionScore gestionScore = new GestionScore(score, terrain.getNid());
        gestionScore.start();

        // Création de la fenêtre de jeu
        JeuFrame jeuFrame = new JeuFrame(terrain, terrainPanel, gestionScore, score);

        // Thread de redessin régulier
        RedessinJeuFrame redessinJeuFrame = new RedessinJeuFrame(jeuFrame);

        // Démarrer les threads
        gestionnaireEnergie.start();
        gestionnaireDeplacement.start();
        gestionnaireCrapaud.start();
        gestionnaireRessources.start();
        gestionnaireFourmisMortes.start();
        gestionnaireSelection.start();

        redessinJeuFrame.start();

        // Fermer la fenêtre du menu de démarrage
        startMenuFrame.dispose();
    }

    /**
     * Affiche une boîte de dialogue pour sélectionner la difficulté.
     */
    private void showDifficultyDialog() {
        String[] options = { "Facile", "Moyen", "Difficile" };
        String current = difficultePanel.getDifficulteSelectionnee();

        String chosen = (String) JOptionPane.showInputDialog(
                startMenuFrame,
                "Choisissez la difficulté :",
                "Sélection de difficulté",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                current);

        // Mise à jour de la difficulté dans le panneau si un choix est validé
        if (chosen != null) {
            difficultePanel.setSelectedDifficulty(chosen);
        }
    }
}
