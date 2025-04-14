package view.panels;

import java.awt.*;
import javax.swing.*;

public class PausePanel extends JPanel {
    private JButton btnResume;

    public PausePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        // Fond semi-transparent
        setBackground(new Color(0, 0, 0, 150));

        JLabel lblPause = new JLabel("Pause");
        lblPause.setFont(new Font("Arial", Font.BOLD, 32));
        lblPause.setForeground(Color.WHITE);
        lblPause.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPause.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblPause);

        btnResume = new JButton("Reprendre");
        btnResume.setFont(new Font("Arial", Font.BOLD, 18));
        btnResume.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(btnResume);

        // Ajoute un peu d'espace autour
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }

    public JButton getResumeButton() {
        return btnResume;
    }
}
