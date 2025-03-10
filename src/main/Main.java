package main;
import javax.swing.JFrame;
import model.Terrain;
import view.Affichage;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Affichage du Terrain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création d'un terrain
        Terrain terrain = new Terrain();
        // Création du panneau d'affichage avec le terrain
        Affichage affichage = new Affichage(terrain);
        frame.add(affichage);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
