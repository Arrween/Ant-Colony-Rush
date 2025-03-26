package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.GestionnaireDeplacement;
import model.GestionnaireEnergie;
import model.Terrain;
import view.DifficultePanel;
import view.JeuFrame;
import view.MenuDemarrage;
import view.RedessinJeuFrame;

public class StartMenuController {
    private MenuDemarrage startMenuFrame;
    private DifficultePanel difficultePanel;

    public StartMenuController(MenuDemarrage frame) {
        this.startMenuFrame = frame;
        this.difficultePanel = frame.getDifficultePanel();
        initController();
    }

    private void initController() {
        // Enregistrer l'écouteur sur le bouton Start
        difficultePanel.setStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });

        // Enregistrer l'écouteur sur le bouton Difficulté
        difficultePanel.setDifficulteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDifficultyDialog();
            }
        });
    }

    // Méthode qui lance le jeu !!!!!

    private void launchGame() {
        // Ici, plus tard on pourra récupérer la difficulté sélectionnée
        // avec difficultePanel.getDifficulteSelectionnee()

        Terrain terrain = new Terrain();
        GestionnaireEnergie gestionnaireEnergie = new GestionnaireEnergie(terrain);
        GestionnaireDeplacement gestionnaireDeplacement = new GestionnaireDeplacement(terrain);

        JeuFrame jeuFrame = new JeuFrame(terrain);

        RedessinJeuFrame redessinJeuFrame = new RedessinJeuFrame(jeuFrame);
        gestionnaireEnergie.start();
        gestionnaireDeplacement.start();
        redessinJeuFrame.start();
        // Fermer la fenêtre du menu de démarrage
        startMenuFrame.dispose();
    }

    // Affiche une boîte de dialogue pour choisir la difficulté
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
        if (chosen != null) {
            difficultePanel.setSelectedDifficulty(chosen);
        }
    }
}
