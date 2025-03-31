package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class SpriteAnimation {
    // Animations pour chaque direction
    private BufferedImage[] imagesHaut;
    private BufferedImage[] imagesBas;
    private BufferedImage[] imagesGauche;
    private BufferedImage[] imagesDroite;

    private BufferedImage[] imagesCourantes;
    private int cadreActuel = 0;

    private final int nombreTotalCadres = 62;
    private final int colonnes = 8;
    private final int lignes = 8;

    private int largeurVertical, hauteurVertical;
    private int largeurHorizontal, hauteurHorizontal;
    private int largeurCadreCourant, hauteurCadreCourant;

    // Position (posX, posY) si besoin de stocker la position globale
    private double posX, posY; 

    public SpriteAnimation() {
        // On charge ici toutes les spritesheets nécessaires
        BufferedImage feuilleHaut   = loadImage("/resources/Ant/ant.png");
        BufferedImage feuilleBas    = loadImage("/resources/Ant/ant_bas.png");
        BufferedImage feuilleGauche = loadImage("/resources/Ant/ant_gauche.png");
        BufferedImage feuilleDroite = loadImage("/resources/Ant/ant_droite.png");

        // Découpage de chaque spritesheet
        imagesHaut   = decouperSprite(feuilleHaut,   colonnes, lignes, nombreTotalCadres);
        imagesBas    = decouperSprite(feuilleBas,    colonnes, lignes, nombreTotalCadres);
        imagesGauche = decouperSprite(feuilleGauche, colonnes, lignes, nombreTotalCadres);
        imagesDroite = decouperSprite(feuilleDroite, colonnes, lignes, nombreTotalCadres);

        // Définition des tailles “verticales” et “horizontales”
        largeurVertical   = feuilleHaut.getWidth() / colonnes;
        hauteurVertical   = feuilleHaut.getHeight() / lignes;
        largeurHorizontal = feuilleGauche.getWidth() / colonnes;
        hauteurHorizontal = feuilleGauche.getHeight() / lignes;

        // On choisit par défaut l’animation Droite (ou Bas) comme images courantes
        imagesCourantes = imagesDroite;
        largeurCadreCourant  = largeurHorizontal;
        hauteurCadreCourant  = hauteurHorizontal;
    }

    /**
     * Vérifie que la ressource existe et l’ouvre. 
     * Lève une RuntimeException si la ressource est introuvable ou illisible.
     */
    private BufferedImage loadImage(String resourcePath) {
        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            throw new RuntimeException("Ressource introuvable : " + resourcePath);
        }
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger la ressource : " + resourcePath, e);
        }
    }

    private BufferedImage[] decouperSprite(BufferedImage feuille, int colonnes, int lignes, int nbTotal) {
        BufferedImage[] frames = new BufferedImage[nbTotal];
        int indice = 0;
        int largeur = feuille.getWidth() / colonnes;
        int hauteur = feuille.getHeight() / lignes;

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (indice < nbTotal) {
                    frames[indice] = feuille.getSubimage(j * largeur, i * hauteur, largeur, hauteur);
                    indice++;
                }
            }
        }
        return frames;
    }

    public void updateFrame() {
        cadreActuel = (cadreActuel + 1) % nombreTotalCadres;
    }

    // --- Changement de direction ---
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
    public void setPos(double x, double y) { posX = x; posY = y; }
}
