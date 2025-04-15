package view.components;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoutonImage extends JButton {

    /**
     * Constructeur pour créer un bouton avec image et effet rollover.
     *
     * @param normalPath Chemin vers l'image par défaut
     * @param hoverPath  Chemin vers l'image lors du survol
     */
    public BoutonImage(String normalPath, String hoverPath) {
        // Récupération des icônes
        ImageIcon icon = new ImageIcon(getClass().getResource(normalPath));
        ImageIcon rolloverIcon = new ImageIcon(getClass().getResource(hoverPath));

        // Configuration du bouton
        setIcon(icon);
        setRolloverIcon(rolloverIcon);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
