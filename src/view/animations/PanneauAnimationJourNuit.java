package view.animations;

import java.awt.*;
import javax.swing.*;

public class PanneauAnimationJourNuit extends JPanel {
    private JLabel lblSoleil;
    private JLabel lblLune;
    private static final Color BEIGE = new Color(245, 245, 220);

    public PanneauAnimationJourNuit() {
        setLayout(null);
        setBackground(BEIGE);

        // Soleil
        ImageIcon soleilIcon = new ImageIcon(getClass().getResource("/resources/weather/jour.png"));
        Image soleilImage = soleilIcon.getImage().getScaledInstance(250, 310, Image.SCALE_SMOOTH); // Redimensionner
        lblSoleil = new JLabel(new ImageIcon(soleilImage));
        lblSoleil.setBounds(40, 50, 250, 310); // Position initiale
        add(lblSoleil);

        // Lune
        ImageIcon luneIcon = new ImageIcon(getClass().getResource("/resources/weather/nuit.png"));
        Image luneImage = luneIcon.getImage().getScaledInstance(250, 310, Image.SCALE_SMOOTH); // Redimensionner
        lblLune = new JLabel(new ImageIcon(luneImage));
        lblLune.setBounds(-350, 50, 250, 310); // Position initiale hors de l'écran
        add(lblLune);
    }

    public void animerJourNuit(boolean isNight) {
        new Thread(() -> {
            try {
                if (isNight) {
                    // Animation pour passer à la nuit
                    for (int x = 50; x <= 400; x += 5) {
                        lblSoleil.setBounds(x, 50, 250, 310); // Soleil glisse vers la droite
                        lblLune.setBounds(-350 + x, 50, 250, 310); // Lune glisse depuis la gauche
                        Thread.sleep(50); // Pause pour lisser l'animation
                        repaint();
                    }
                    // Une fois la lune au centre, repositionner le soleil à gauche
                    lblSoleil.setBounds(-350, 50, 250, 310); // Soleil hors du panneau à gauche
                } else {
                    // Animation pour passer au jour
                    for (int x = 50; x <= 400; x += 5) {
                        lblLune.setBounds(x, 50, 250, 310); // Lune glisse vers la droite
                        lblSoleil.setBounds(-350 + x, 50, 250, 310); // Soleil glisse depuis la gauche
                        Thread.sleep(50); // Pause pour lisser l'animation
                        repaint();
                    }
                    // Une fois le soleil au centre, repositionner la lune à gauche
                    lblLune.setBounds(-350, 50, 250, 310); // Lune hors du panneau à gauche
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}