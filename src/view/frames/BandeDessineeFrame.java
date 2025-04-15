package view.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BandeDessineeFrame extends JFrame {
    private int currentPage = 0; // Index de la page actuelle
    private final String[] pages = {
        "src/resources/BD/1.png",
        "src/resources/BD/2.png",
        "src/resources/BD/3.png",
        "src/resources/BD/4.png",
        "src/resources/BD/5.png"
    };
    private JLabel imageLabel;

    public BandeDessineeFrame() {
        setTitle("Bande Dessinée");
        setSize(800, 600); // Taille de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Label pour afficher l'image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        updatePage(); // Affiche la première page
        add(imageLabel, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("Précédent");
        JButton nextButton = new JButton("Suivant");
        JButton skipButton = new JButton("Skip");

        // Action pour le bouton "Précédent"
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updatePage();
                }
            }
        });

        // Action pour le bouton "Suivant"
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage < pages.length - 1) {
                    currentPage++;
                    updatePage();
                } else {
                    // Fermer la bande dessinée et lancer le jeu
                    dispose(); // Ferme la fenêtre actuelle
                }
            }
        });

        // Action pour le bouton "Skip"
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer la bande dessinée et lancer le jeu immédiatement
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(skipButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Méthode pour mettre à jour l'image affichée
    private void updatePage() {
        ImageIcon icon = new ImageIcon(pages[currentPage]);
        Image scaledImage = icon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BandeDessineeFrame frame = new BandeDessineeFrame();
            frame.setVisible(true);
        });
    }
}