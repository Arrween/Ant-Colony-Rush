package tests;

import controler.DestSelector;
import java.awt.Point;
import javax.swing.JFrame;
import model.Terrain;
import view.SpriteAnimation;

public class TestAnimationFourmi {
    public static void main(String[] args) {
        // Exemple de coordonnées de départ et d'arrivée
        int departX = 100;
        int departY = 400;
        Terrain t = new Terrain();
        Point p = new Point(0, 0);
        DestSelector rc = new DestSelector(t);

        JFrame fenetre = new JFrame("Animation de la Fourmi");
        fenetre.addMouseListener(rc);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);

        try {
            fenetre.revalidate();
            fenetre.repaint();

            Thread.sleep((5000)); // Attendre 2 secondes (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SpriteAnimation panneau = new SpriteAnimation(departX, departY, (int) p.getX(), (int) p.getY());
        fenetre.add(panneau);
        fenetre.revalidate();
        fenetre.repaint();

    }
}
