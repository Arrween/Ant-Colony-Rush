package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteAnimation {
    // Animations pour chaque direction
    private BufferedImage[] imagesHaut;
    private BufferedImage[] imagesBas;
    private BufferedImage[] imagesGauche;
    private BufferedImage[] imagesDroite;

    // Animation actuellement utilisée
    private BufferedImage[] imagesCourantes;

    private int cadreActuel = 0;

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

    // Position et destination
    private double posX, posY; 
    private double destX, destY;
    // Vitesse
    private double vitesse = 5.0;

    // Constructeur avec paramètres pour les coordonnées de départ et d'arrivée
    public SpriteAnimation() {

        try {
            // Charger la feuille pour le haut
            BufferedImage feuilleHaut = ImageIO.read(getClass().getResource("/ressources/Ant/ant.png"));
            imagesHaut = decouperSprite(feuilleHaut, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour le bas
            BufferedImage feuilleBas = ImageIO.read(getClass().getResource("/ressources/Ant/ant_bas.png"));
            imagesBas = decouperSprite(feuilleBas, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour la gauche
            BufferedImage feuilleGauche = ImageIO.read(getClass().getResource("/ressources/Ant/ant_gauche.png"));
            imagesGauche = decouperSprite(feuilleGauche, colonnes, lignes, nombreTotalCadres);
            // Charger la feuille pour la droite
            BufferedImage feuilleDroite = ImageIO.read(getClass().getResource("/ressources/Ant/ant_droite.png"));
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

        // Par défaut, on peut choisir Bas, Haut, etc.
        imagesCourantes = imagesDroite;
        largeurCadreCourant = largeurHorizontal;
        hauteurCadreCourant = hauteurHorizontal;
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

    public void updateFrame() {
        // Avance à la frame suivante
        cadreActuel = (cadreActuel + 1) % nombreTotalCadres;
    }

    // --- Sélecteurs de direction ---
    public void setDirectionHaut() {
        imagesCourantes = imagesHaut;
        largeurCadreCourant = largeurVertical;
        hauteurCadreCourant = hauteurVertical;
    }

    public void setDirectionBas() {
        imagesCourantes = imagesBas;
        largeurCadreCourant = largeurVertical;
        hauteurCadreCourant = hauteurVertical;
    }

    public void setDirectionGauche() {
        imagesCourantes = imagesGauche;
        largeurCadreCourant = largeurHorizontal;
        hauteurCadreCourant = hauteurHorizontal;
    }

    public void setDirectionDroite() {
        imagesCourantes = imagesDroite;
        largeurCadreCourant = largeurHorizontal;
        hauteurCadreCourant = hauteurHorizontal;
    }

    // --- Accesseurs ---
    public BufferedImage getCurrentSprite() {
        return imagesCourantes[cadreActuel];
    }

    public int getLargeurCadreCourant() { return largeurCadreCourant; }
    public int getHauteurCadreCourant() { return hauteurCadreCourant; }

    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public void setPos(double x, double y) { this.posX = x; this.posY = y; }

    public void setDestination(double x, double y) {
        this.destX = x;
        this.destY = y;
    }

    public void setVitesse(double v) {
        this.vitesse = v;
    }
}
