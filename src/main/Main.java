package main;

import controller.StartMenuController;
import view.MenuDemarrage;

public class Main {
    public static void main(String[] args) {
        // Création et affichage de la fenêtre du menu de démarrage
        MenuDemarrage startMenuFrame = new MenuDemarrage();
        startMenuFrame.setVisible(true);

        // Création du contrôleur du menu de démarrage
        new StartMenuController(startMenuFrame);
    }
}
