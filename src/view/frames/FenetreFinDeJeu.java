package view.frames;

import controller.StartMenuController;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.components.BoutonImage;

public class FenetreFinDeJeu extends JFrame {

    private static final Color BEIGE = new Color(245, 245, 220);

    public FenetreFinDeJeu(String message, JFrame ancienneFenetre, ActionListener rejouerListener,
            ActionListener quitterListener) {
        setTitle("Fin du Jeu");
        setSize(800, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(BEIGE);

        // Message de fin
        JLabel lblMessage = new JLabel(message, SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
        lblMessage.setBackground(BEIGE);
        add(lblMessage, BorderLayout.CENTER);

        // Boutons
        JPanel pnlBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlBoutons.setBackground(BEIGE);
        BoutonImage btnRejouer = new BoutonImage("/resources/Menu/restart.png", "/resources/Menu/restart_hover.png");
        BoutonImage btnQuitter = new BoutonImage("/resources/Menu/quit.png", "/resources/Menu/quit_hover.png");

        btnRejouer.addActionListener(e -> {
            // Fermer l'ancienne fenêtre
            if (ancienneFenetre != null) {
                ancienneFenetre.dispose();
            }

            // Rejouer : relancer le jeu
            SwingUtilities.invokeLater(() -> {
                MenuDemarrage menuDemarrage = new MenuDemarrage();
                new StartMenuController(menuDemarrage); // Associer un nouveau contrôleur
                menuDemarrage.setVisible(true);
            });
            dispose(); // Fermer la fenêtre de fin de jeu
        });

        btnQuitter.addActionListener(quitterListener);

        pnlBoutons.add(btnRejouer, BorderLayout.WEST); // Bouton "Rejouer" à gauche
        pnlBoutons.add(btnQuitter, BorderLayout.EAST); // Bouton "Quitter" à droite

        add(pnlBoutons, BorderLayout.SOUTH); // Ajouter le panneau des boutons en bas de la fenêtre
    }
}