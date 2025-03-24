package tests;

import javax.swing.JFrame;
import model.Terrain;
import view.Affichage;

public class terrainTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test affichage du Terrain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du terrain avec les objets de base
        Terrain terrain = new Terrain();
        // Création du panneau d'affichage pour le terrain
        Affichage affichage = new Affichage(terrain);

        frame.add(affichage);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
