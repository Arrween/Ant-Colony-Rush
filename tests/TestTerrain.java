package tests;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import model.*;

public class TestTerrain extends JPanel {
    private Terrain terrain;

    public TestTerrain(Terrain terrain) {
        this.terrain = terrain;
        setPreferredSize(new Dimension(Terrain.LARGEUR, Terrain.HAUTEUR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ObjetFixe obj : Terrain.GetObjetsFixes()) {
            Image img = obj.getImage();
            if (img != null) {
                g.drawImage(img, obj.getX(), obj.getY(), this);
            } else {
                g.setColor(Color.RED);
                g.fillOval(obj.getX(), obj.getY(), 10, 10); // Dessiner un cercle rouge si l'image n'est pas trouvée
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Affichage du Terrain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création d'un terrain
        Terrain terrain = new Terrain();

        // Ajouter des ressources et des abris
        terrain.ajouterRessources(8);
        terrain.ajouterAbris(5);

        // Création du panneau d'affichage avec le terrain
        TestTerrain affichage = new TestTerrain(terrain);
        frame.add(affichage);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}