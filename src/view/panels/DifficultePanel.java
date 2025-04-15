package view.panels;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.components.BoutonImage;

public class DifficultePanel extends JPanel {
    private BoutonImage startButton;
    private BoutonImage difficulteButton;
    private String difficulteSelectionnee = "Facile";

    public DifficultePanel() {
        setLayout(new BorderLayout());

        // Image de fond
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/resources/Menu/bg.png"));
        Image image = rawIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon backgroundIcon = new ImageIcon(image);

        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
        add(backgroundLabel, BorderLayout.CENTER);

        // Panel de boutons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        buttonPanel.setOpaque(false);

        // Création des boutons avec la nouvelle classe BoutonImage
        startButton = new BoutonImage("/resources/Menu/start.png", "/resources/Menu/start_hover.png");
        difficulteButton = new BoutonImage("/resources/Menu/difficulty.png", "/resources/Menu/difficulty_hover.png");

        buttonPanel.add(startButton);
        buttonPanel.add(difficulteButton);

        backgroundLabel.add(buttonPanel, new GridBagConstraints());
    }

    // Méthodes pour l'enregistrement des écouteurs
    public void setStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void setDifficulteButtonListener(ActionListener listener) {
        difficulteButton.addActionListener(listener);
    }

    // Setter pour la difficulté
    public void setSelectedDifficulty(String difficulty) {
        this.difficulteSelectionnee = difficulty;
    }

    public String getDifficulteSelectionnee() {
        return difficulteSelectionnee;
    }
}
