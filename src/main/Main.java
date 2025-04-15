package main;

import controller.StartMenuController;
import view.frames.BandeDessineeFrame;
import view.frames.MenuDemarrage;

public class Main {
    public static void main(String[] args) {
//         // Afficher la bande dessinée
//         BandeDessineeFrame bandeDessineeFrame = new BandeDessineeFrame();
//         bandeDessineeFrame.setVisible(true);

//         // Création et affichage de la fenêtre du menu de démarrage
//         MenuDemarrage startMenuFrame = new MenuDemarrage();
//         startMenuFrame.setVisible(true);

//         // Création du contrôleur du menu de démarrage
//         new StartMenuController(startMenuFrame);
//     }
// }

    // Afficher la bande dessinée
    BandeDessineeFrame bandeDessineeFrame = new BandeDessineeFrame();
    bandeDessineeFrame.setDefaultCloseOperation(BandeDessineeFrame.DISPOSE_ON_CLOSE);
    bandeDessineeFrame.setVisible(true);

    // Attendre que la bande dessinée soit fermée
    bandeDessineeFrame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            // Une fois la bande dessinée fermée, continuer avec le menu de démarrage
            MenuDemarrage startMenuFrame = new MenuDemarrage();
            startMenuFrame.setVisible(true);

            // Création du contrôleur du menu de démarrage
            new StartMenuController(startMenuFrame);
        }
    });
}
}