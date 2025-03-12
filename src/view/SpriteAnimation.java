package view;

import controler.ReactionClic;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Terrain;

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
    // Organisation en grille (pour découper les spritesheets)
    private final int colonnes = 8;
    private final int lignes = 8;
    
    // Dimensions pour les animations verticales (haut et bas)
    private int largeurVertical, hauteurVertical;
    // Dimensions pour les animations horizontales (gauche et droite)
    private int largeurHorizontal, hauteurHorizontal;
    // Dimensions du cadre actuellement utilisé (selon l'animation active)
    private int largeurCadreCourant, hauteurCadreCourant;
    
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
            // Charger la feuille pour le haut
            BufferedImage feuilleHaut = ImageIO.read(getClass().getResource("/ressources/ant.png"));
            imagesHaut = decouperSprite(feuilleHaut, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour le bas
            BufferedImage feuilleBas = ImageIO.read(getClass().getResource("/ressources/ant_bas.png"));
            imagesBas = decouperSprite(feuilleBas, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour la gauche
            BufferedImage feuilleGauche = ImageIO.read(getClass().getResource("/ressources/ant_gauche.png"));
            imagesGauche = decouperSprite(feuilleGauche, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour la droite
            BufferedImage feuilleDroite = ImageIO.read(getClass().getResource("/ressources/ant_droite.png"));   
            imagesDroite = decouperSprite(feuilleDroite, colonnes, lignes, nombreTotalCadres);
            
            // Dimensions pour les animations verticales (haut et bas)
            largeurVertical = feuilleHaut.getWidth() / colonnes;
            hauteurVertical = feuilleHaut.getHeight() / lignes;
            // Dimensions pour les animations horizontales (gauche et droite)
            largeurHorizontal = feuilleGauche.getWidth() / colonnes;
            hauteurHorizontal = feuilleGauche.getHeight() / lignes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Initialiser la position de la fourmi au point de départ
        posX = pointA_x;
        posY = pointA_y;
        
        // Choisir l'animation initiale en fonction du déplacement horizontal ou vertical
        if (pointB_x != posX) {
            if (pointB_x > posX) {
                imagesCourantes = imagesDroite;
            } else {
                imagesCourantes = imagesGauche;
            }
            largeurCadreCourant = largeurHorizontal;
            hauteurCadreCourant = hauteurHorizontal;
        } else if (pointB_y != posY) {
            if (pointB_y > posY) {
                imagesCourantes = imagesBas;
            } else {
                imagesCourantes = imagesHaut;
            }
            largeurCadreCourant = largeurVertical;
            hauteurCadreCourant = hauteurVertical;
        }
        
        // Initialiser le chronomètre pour rafraîchir l'animation toutes les 50ms
        chronometre = new Timer(20, this);
        chronometre.start();
    }
    
    // Méthode pour découper une spritesheet en un tableau d'images
    private BufferedImage[] decouperSprite(BufferedImage feuille, int colonnes, int lignes, int nombreTotalCadres) {
        BufferedImage[] frames = new BufferedImage[nombreTotalCadres];
        int indice = 0;
        int largeur = feuille.getWidth() / colonnes;
        int hauteur = feuille.getHeight() / lignes;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (indice < nombreTotalCadres) {
                    frames[indice] = feuille.getSubimage(j * largeur, i * hauteur, largeur, hauteur);
                    indice++;
                }
            }
        }
        return frames;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Affichage de l'animation de la fourmi
        if (imagesCourantes != null && imagesCourantes[cadreActuel] != null) {
            // Ici, l'image est dessinée en redimensionnant avec un facteur (exemple /10)
            g.drawImage(imagesCourantes[cadreActuel], (int) posX, (int) posY, largeurCadreCourant/10, hauteurCadreCourant/10, this);
        }
        
        // Dessiner une croix aux coordonnées de destination
        int tailleCroix = 10;
        g.drawLine(pointB_x - tailleCroix, pointB_y, pointB_x + tailleCroix, pointB_y);
        g.drawLine(pointB_x, pointB_y - tailleCroix, pointB_x, pointB_y + tailleCroix);
        
        // Dessiner un cercle dont le centre est aux coordonnées de départ
        int rayon = 35;
        g.drawOval(pointA_x - rayon, pointA_y - rayon, 2 * rayon, 2 * rayon);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mise à jour de l'animation (passer au cadre suivant)
        cadreActuel = (cadreActuel + 1) % nombreTotalCadres;
        
        // Déplacement en deux étapes : d'abord horizontal, puis vertical
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
            // Pour un déplacement horizontal, utiliser les dimensions horizontales
            largeurCadreCourant = largeurHorizontal;
            hauteurCadreCourant = hauteurHorizontal;
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
            // Pour un déplacement vertical, utiliser les dimensions verticales
            largeurCadreCourant = largeurVertical;
            hauteurCadreCourant = hauteurVertical;
        }
        
        // Arrêter l'animation lorsque la destination est atteinte
        if (posX == pointB_x && posY == pointB_y) {
            chronometre.stop();
        }
        
        repaint();
    }

    //////////// pour les tests ////////////
    public void setCoordDest(int x, int y) {
        pointB_x = x;
        pointB_y = y;
    }
    ////////////////////////////////////////
    
    public static void main(String[] args) {
        // Exemple de coordonnées de départ et d'arrivée
        int departX = 100;
        int departY = 400;
        Terrain t = new Terrain();
        Point p = new Point(0, 0);
        ReactionClic rc = new ReactionClic(t, p);
        
        JFrame fenetre = new JFrame("Animation de la Fourmi");
        fenetre.addMouseListener(rc);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);

        try {fenetre.revalidate();
            fenetre.repaint();
            
            Thread.sleep((5000)); // Attendre 2 secondes (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SpriteAnimation panneau = new SpriteAnimation(departX, departY, (int)p.getX(), (int)p.getY());
        fenetre.add(panneau);
        fenetre.revalidate();
        fenetre.repaint();

    }
}
