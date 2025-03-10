package main;
import javax.swing.JFrame;
import model.Terrain;
import view.Affichage;


public class Main {
    public static void main(String[] args) {
        // Création d'un terrain
        Terrain terrain = new Terrain();
        // Vous pouvez initialiser le tableau grid de terrain ici si besoin,
        // par exemple : terrain.grid = new ObjetFixe[10][10];
        
        // Création du panneau d'affichage avec le terrain
        Affichage affichage = new Affichage(terrain);

        
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Affichage du Terrain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(affichage);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
