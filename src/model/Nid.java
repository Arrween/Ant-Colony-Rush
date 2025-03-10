package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Nid extends ObjetFixe {
    private int cptRessources;
    private ArrayList<Fourmi> fourmis;
    private int x;
    private int y;
    private Image imageNid; // L'image représentant le nid

    public Nid(int cptRessources, Fourmi[] fourmis, int x, int y) {
        super(x, y);
        this.cptRessources = cptRessources;
        this.fourmis = new ArrayList<Fourmi>();
        this.x = x;
        this.y = y;
        
        ImageIcon icon = new ImageIcon("ressources/nid.png");
        imageNid = icon.getImage();

    }

    public void dessiner(Graphics g) {
        g.drawImage(imageNid, x, y,40, 40, null);
    }
    
    // Méthode main pour tester et afficher l'image
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test de l'image du nid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création d'une instance de Nid
        Nid nid = new Nid(0, null, 50, 50);

        // Création d'un JPanel personnalisé qui dessine le nid
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                nid.dessiner(g);
            }
        };

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);
    }
}
