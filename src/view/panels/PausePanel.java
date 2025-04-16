package view.panels;

import java.awt.*;
import javax.swing.*;
import view.components.BoutonImage;

public class PausePanel extends JPanel {
    private JButton btnResume;

   public PausePanel() {
    // Utilisation d'un BoxLayout vertical
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setOpaque(true);

    // Fond beige naturel
    setBackground(new Color(245, 245, 220)); // Beige
    // Bordure verte pour un accent naturel
    setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 5));

    // Label "Pause" en vert foncé
    JLabel lblPause = new JLabel("Pause");
    lblPause.setFont(new Font("Arial", Font.BOLD, 32));
    lblPause.setForeground(new Color(34, 139, 34)); // ForestGreen
    lblPause.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblPause.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    add(lblPause);

    // Bouton de reprise utilisant l'image "continue.png" et son état rollover "continue_hover.png"
    btnResume = new BoutonImage("/resources/Menu/continue.png", "/resources/Menu/continue_hover.png");
    btnResume.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(btnResume);

    // Espace autour
    setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
}

public JButton getResumeButton() {
    return btnResume;
}
}
