package view.frames;

import controller.StartMenuController;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FenetreFinDeJeu extends JFrame {

    public FenetreFinDeJeu(String message, JFrame ancienneFenetre, ActionListener rejouerListener,
            ActionListener quitterListener) {
        setTitle("Fin du Jeu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Message de fin
        JLabel lblMessage = new JLabel(message, SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblMessage, BorderLayout.CENTER);

        // Boutons
        JPanel pnlBoutons = new JPanel();
        JButton btnRejouer = new JButton("Rejouer");
        JButton btnQuitter = new JButton("Quitter");

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

        pnlBoutons.add(btnRejouer);
        pnlBoutons.add(btnQuitter);
        add(pnlBoutons, BorderLayout.SOUTH);
    }
}