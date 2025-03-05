package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SpriteAnimation extends JPanel implements ActionListener {
    // Animations pour chaque direction
    private BufferedImage[] imagesHaut;
    private BufferedImage[] imagesBas;
    private BufferedImage[] imagesGauche;
    private BufferedImage[] imagesDroite;

    // Animation actuellement utilisée
    private BufferedImage[] imagesCourantes;
    
    private int cadreActuel = 0;
    private Timer chronometre;
    
    // Nombre total de cadres dans chaque animation (ici 62)
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
        pointA_x = a_x;
        pointA_y = a_y;
        pointB_x = b_x;
        pointB_y = b_y;
        
        try {
            // Charger les 4 animations à partir de leurs fichiers respectifs
            imagesHaut = chargerAnimation("ressources/ant.png");
            imagesBas = chargerAnimation("ressources/ant_bas.png");
            imagesGauche = chargerAnimation("ressources/ant_gauche.png");
            imagesDroite = chargerAnimation("ressources/ant_droite.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Initialiser la position à partir du point de départ
        posX = pointA_x;
        posY = pointA_y;
        
        // Choisir l'animation de départ en fonction de la direction initiale
        if (pointB_x != posX) {
            imagesCourantes = (pointB_x > posX) ? imagesDroite : imagesGauche;
        } else if (pointB_y != posY) {
            imagesCourantes = (pointB_y > posY) ? imagesBas : imagesHaut;
        }
        
        // Récupérer la taille d'un cadre à partir de l'animation "haut"
        if (imagesHaut != null && imagesHaut[0] != null) {
            largeurCadre = imagesHaut[0].getWidth();
            hauteurCadre = imagesHaut[0].getHeight();
        }
        
        // Initialiser le chronomètre pour rafraîchir l'animation toutes les 50 ms
        chronometre = new Timer(50, this);
        chronometre.start();
    }
    
    // Méthode utilitaire pour charger un spritesheet et découper ses cadres
    private BufferedImage[] chargerAnimation(String chemin) throws IOException {
        BufferedImage feuille = ImageIO.read(new File(chemin));
        int largeurCadreTemp = feuille.getWidth() / colonnes;
        int hauteurCadreTemp = feuille.getHeight() / lignes;
        BufferedImage[] frames = new BufferedImage[nombreTotalCadres];
        int indice = 0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (indice < nombreTotalCadres) {
                    frames[indice] = feuille.getSubimage(j * largeurCadreTemp, i * hauteurCadreTemp, largeurCadreTemp, hauteurCadreTemp);
                    indice++;
                }
            }
        }
        return frames;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagesCourantes != null && imagesCourantes[cadreActuel] != null) {
            g.drawImage(imagesCourantes[cadreActuel], (int) posX, (int) posY, 10, 20,  this);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mise à jour de l'animation (on passe au cadre suivant)
        cadreActuel = (cadreActuel + 1) % nombreTotalCadres;
        
        // Déplacement logique en 2 étapes : d'abord horizontalement, puis verticalement
        if (posX != pointB_x) {
            // Déplacement sur l'axe X
            if (Math.abs(pointB_x - posX) > vitesse) {
                if (pointB_x > posX) {
                    posX += vitesse;
                    imagesCourantes = imagesDroite;
                } else {
                    posX -= vitesse;
                    imagesCourantes = imagesGauche;
                }
            } else {
                posX = pointB_x;
            }
        } else if (posY != pointB_y) {
            // Déplacement sur l'axe Y
            if (Math.abs(pointB_y - posY) > vitesse) {
                if (pointB_y > posY) {
                    posY += vitesse;
                    imagesCourantes = imagesBas;
                } else {
                    posY -= vitesse;
                    imagesCourantes = imagesHaut;
                }
            } else {
                posY = pointB_y;
            }
        }
        
        // Arrêter l'animation si la destination est atteinte
        if (posX == pointB_x && posY == pointB_y) {
            chronometre.stop();
        }
        
        repaint();
    }
    
    public static void main(String[] args) {
        // Exemple de coordonnées de départ et d'arrivée
        int departX = 100;
        int departY = 400;
        int arriveeX = 200; // destinatio,
        int arriveeY = 100;
        
        JFrame fenetre = new JFrame("Animation de la Fourmi");
        SpriteAnimation panneau = new SpriteAnimation(departX, departY, arriveeX, arriveeY);
        fenetre.add(panneau);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }
}
