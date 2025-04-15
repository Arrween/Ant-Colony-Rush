package view.animations;

import javax.swing.*;
import java.awt.*;

public class PanneauAnimationJourNuit extends JPanel {
    private JLabel lblSoleil;
    private JLabel lblLune;

    public PanneauAnimationJourNuit() {
        setLayout(null); // Utiliser un layout null pour positionner les labels manuellement

        // Soleil
        ImageIcon soleilIcon = new ImageIcon(getClass().getResource("/resources/weather/jour.png"));
        Image soleilImage = soleilIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionner
        lblSoleil = new JLabel(new ImageIcon(soleilImage));
        lblSoleil.setBounds(50, 80, 100, 100); // Position initiale
        add(lblSoleil);

        // Lune
        ImageIcon luneIcon = new ImageIcon(getClass().getResource("/resources/weather/nuit.png"));
        Image luneImage = luneIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionner
        lblLune = new JLabel(new ImageIcon(luneImage));
        lblLune.setBounds(-150, 80, 100, 100); // Position initiale hors de l'écran
        add(lblLune);
    }

    public void animerJourNuit(boolean isNight) {
        new Thread(() -> {
            try {
                if (isNight) {
                    // Animation pour passer à la nuit
                    for (int x = 50; x <= 400; x += 5) {
                        lblSoleil.setBounds(x, 80, 100, 100); // Soleil glisse vers la droite
                        lblLune.setBounds(-150 + x, 80, 100, 100); // Lune glisse depuis la gauche
                        Thread.sleep(50); // Pause pour lisser l'animation
                        repaint();
                    }
                    // Une fois la lune au centre, repositionner le soleil à gauche
                    lblSoleil.setBounds(-150, 80, 100, 100); // Soleil hors du panneau à gauche
                } else {
                    // Animation pour passer au jour
                    for (int x = 50; x <= 400; x += 5) {
                        lblLune.setBounds(x, 80, 100, 100); // Lune glisse vers la droite
                        lblSoleil.setBounds(-150 + x, 80, 100, 100); // Soleil glisse depuis la gauche
                        Thread.sleep(50); // Pause pour lisser l'animation
                        repaint();
                    }
                    // Une fois le soleil au centre, repositionner la lune à gauche
                    lblLune.setBounds(-150, 80, 100, 100); // Lune hors du panneau à gauche
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}