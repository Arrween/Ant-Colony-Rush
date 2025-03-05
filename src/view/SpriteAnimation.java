package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SpriteAnimation extends JPanel implements ActionListener {
    private BufferedImage feuilleSprite;
    private BufferedImage[] imagesCadres;
    private int cadreActuel = 0;
    private Timer chronometre;
    
    // Nombre total de cadres à utiliser dans l'animation (ici 62)
    private final int nombreTotalCadres = 62;
    // Organisation de la feuille de sprite en grille 8x8
    private final int colonnes = 8;
    private final int lignes = 8;
    private int largeurCadre, hauteurCadre;
    
    // Coordonnées de départ et d'arrivée
    private int pointA_x, pointA_y, pointB_x, pointB_y;
    // Position actuelle de la fourmi
    private double posX, posY;
    // Vitesse de déplacement (en pixels par tic)
    private double vitesse = 2.0;
    
    // Constructeur avec paramètres pour les coordonnées de départ et d'arrivée
    public SpriteAnimation(int a_x, int a_y, int b_x, int b_y) {
        // Affecter les coordonnées de départ (A) et d'arrivée (B)
        pointA_x = a_x;
        pointA_y = a_y;
        pointB_x = b_x;
        pointB_y = b_y;
        
        try {
            // Charger la feuille de sprite depuis le fichier
            feuilleSprite = ImageIO.read(new File("ressources/ant.png"));
            
            // Calculer la taille de chaque cadre dans la grille
            largeurCadre = feuilleSprite.getWidth() / colonnes;
            hauteurCadre = feuilleSprite.getHeight() / lignes;
            
            // Créer un tableau pour stocker les cadres de l'animation
            imagesCadres = new BufferedImage[nombreTotalCadres];
            int indiceCadre = 0;
            
            // Parcourir la grille pour extraire les cadres
            for (int ligne = 0; ligne < lignes; ligne++) {
                for (int colonne = 0; colonne < colonnes; colonne++) {
                    if (indiceCadre < nombreTotalCadres) { // Ne pas dépasser les 62 images
                        imagesCadres[indiceCadre] = feuilleSprite.getSubimage(
                            colonne * largeurCadre, 
                            ligne * hauteurCadre, 
                            largeurCadre, 
                            hauteurCadre
                        );
                        indiceCadre++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Initialiser la position de la fourmi au point de départ (A)
        posX = pointA_x;
        posY = pointA_y;
        
        // Initialiser un chronomètre qui rafraîchit l'affichage toutes les 50ms
        chronometre = new Timer(50, this);
        chronometre.start();
    }

    // Redéfinition de paintComponent pour dessiner le cadre actuel à la position actuelle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagesCadres != null && imagesCadres[cadreActuel] != null) {
            g.drawImage(imagesCadres[cadreActuel], (int) posX, (int) posY,10, 20, this);
        }
    }

    // Mise à jour de l'animation et du déplacement de la fourmi à chaque tic du chronomètre
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mise à jour de l'animation du sprite
        cadreActuel = (cadreActuel + 1) % nombreTotalCadres;
        
        // Déplacement logique : d'abord sur l'axe X, puis sur l'axe Y
        if (posX != pointB_x) {
            // Avancer sur l'axe X jusqu'à atteindre la colonne de destination
            if (Math.abs(pointB_x - posX) > vitesse) {
                posX += (pointB_x > posX) ? vitesse : -vitesse;
            } else {
                posX = pointB_x;
            }
        } else if (posY != pointB_y) {
            // Une fois la colonne atteinte, avancer sur l'axe Y jusqu'à atteindre la ligne de destination
            if (Math.abs(pointB_y - posY) > vitesse) {
                posY += (pointB_y > posY) ? vitesse : -vitesse;
            } else {
                posY = pointB_y;
            }
        }
        
        // Arrêter l'animation lorsque la destination est atteinte
        if (posX == pointB_x && posY == pointB_y) {
            chronometre.stop();
        }
        
        repaint();
    }

    public static void main(String[] args) {
        // Définir ici les coordonnées de départ et d'arrivée
        int departX = 100;
        int departY = 400;
        int arriveeX = 200; // Destination dans une colonne différente
        int arriveeY = 100;
        
        JFrame fenetre = new JFrame("Animation de la Fourmi");
        SpriteAnimation panneau = new SpriteAnimation(departX, departY, arriveeX, arriveeY);
        fenetre.add(panneau);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ajuster la taille de la fenêtre en fonction des besoins
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
}
