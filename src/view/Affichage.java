package view;

import java.awt.*;
import model.Position;
import javax.swing.*;

public class Affichage extends JPanel {

    // Dimensions du JPanel
    public static final int LARGEUR = 400;
    public static final int HAUTEUR = 300;

    // On y stocke la Position pour pouvoir l'utiliser dans paint()
    private Position maPosition;

    // Constructeur qui prend la Position en paramètre
    public Affichage(Position pos) {
        this.maPosition = pos;
        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
    }

    /**
     * Méthode pour dessiner dans le JPanel.
     * Remarque : On redéfinit paint, mais on peut aussi redéfinir paintComponent.
     */
    @Override
    public void paint(Graphics g) {
        // Nettoyage du fond (important pour éviter la superposition)
        super.paint(g);
        /**
         * Pour l'ovale, on utilise la hauteur stockée dans la Position.
         * Ici, par exemple, on part de (100, 100) et on « déplace » verticalement 
         * l’ovale vers le haut ou le bas en fonction de la valeur de maPosition.get().
         */
        g.drawOval(100, 100 - maPosition.get(), 100, 100);
    }
}
