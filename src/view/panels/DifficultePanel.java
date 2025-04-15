package view.panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DifficultePanel extends JPanel {
    private JButton startButton;
    private JButton difficulteButton;
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

        // Boutons avec hover
        startButton = createImageButton("/resources/Menu/start.png", "/resources/Menu/start_hover.png");
        difficulteButton = createImageButton("/resources/Menu/difficulty.png", "/resources/Menu/difficulty_hover.png");

        buttonPanel.add(startButton);
        buttonPanel.add(difficulteButton);

        backgroundLabel.add(buttonPanel, new GridBagConstraints());
    }

    // Création d'un bouton image avec rollover
    private JButton createImageButton(String normalPath, String hoverPath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(normalPath));
        ImageIcon rolloverIcon = new ImageIcon(getClass().getResource(hoverPath));

        JButton button = new JButton(icon);
        button.setRolloverIcon(rolloverIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Listeners
    public void setStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void setDifficulteButtonListener(ActionListener listener) {
        difficulteButton.addActionListener(listener);
    }

    // Difficulté
    public void setSelectedDifficulty(String difficulty) {
        this.difficulteSelectionnee = difficulty;
        // Tu pourrais changer l'image ici selon la difficulté
    }

    public String getDifficulteSelectionnee() {
        return difficulteSelectionnee;
    }
}
