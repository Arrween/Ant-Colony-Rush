package view.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DifficultePanel extends JPanel {
    private JButton startButton;
    private JButton difficulteButton;
    private String difficulteSelectionnee = "Facile"; // Difficulté par défaut

    public DifficultePanel() {
        setLayout(new BorderLayout());

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/resources/Ant/imageStart.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        // Création d'un panel pour les boutons, transparent pour laisser voir le fond
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setOpaque(false);

        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));

        difficulteButton = new JButton("Difficulté : " + difficulteSelectionnee);
        difficulteButton.setFont(new Font("Arial", Font.BOLD, 20));

        buttonPanel.add(startButton);
        buttonPanel.add(difficulteButton);

        // Ajout du panel de boutons au centre du fond
        backgroundLabel.add(buttonPanel, new GridBagConstraints());
    }

    // Méthodes d'enregistrement des écouteurs
    public void setStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void setDifficulteButtonListener(ActionListener listener) {
        difficulteButton.addActionListener(listener);
    }

    // Accesseurs pour la difficulté sélectionnée
    public void setSelectedDifficulty(String difficulty) {
        this.difficulteSelectionnee = difficulty;
        difficulteButton.setText("Difficulté : " + difficulty);
    }

    public String getDifficulteSelectionnee() {
        return difficulteSelectionnee;
    }
}
