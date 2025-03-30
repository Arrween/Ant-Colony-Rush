package model;

import view.SpriteAnimation;

public abstract class Deplacement {
    private Terrain t;
    protected int currentX, currentY, destX, destY, depX, depY;
    protected ObjetFixe dest;
    protected boolean isDone = false;

    private static int compteur = 0;
    private int numero;

    protected SpriteAnimation animationFourmi;
    protected double vitesse; // Vitesse de déplacement de la fourmi

    public Deplacement(Terrain t, ObjetFixe dest, ObjetFixe depart) {
        this.t = t;
        this.currentX = depart.getX();
        this.currentY = depart.getY();
        this.dest = dest;
        this.destX = dest.getX();
        this.destY = dest.getY();
        numero = ++compteur;
        depX = depart.getX();
        depY = depart.getY();
        vitesse = 1.0; // Vitesse de déplacement par défaut

        // Initialiser l'animation
        animationFourmi = new SpriteAnimation();
        updateDirection(); // Choix initial de la direction
    }

    public abstract void decrEnergieFourmi() ;

    public int getId() {
        return numero;
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public int getDepX() {
        return depX;
    }

    public int getDepY() {
        return depY;
    }

    // Méthode appelée périodiquement (par le Timer du contrôleur)
    public abstract void avancer();

    protected void updatePosition() {
        // algo de déplacmenent////
        if (currentX < destX) {
            currentX += vitesse;
            if (currentX > destX) {
                currentX = destX;
            }
        } else if (currentX > destX) {
            currentX -= vitesse;
            if (currentX < destX) {
                currentX = destX;
            }
        } else if (currentY < destY) {
            currentY += vitesse;
            if (currentY > destY) {
                currentY = destY;
            }
        } else if (currentY > destY) {
            currentY -= vitesse;
            if (currentY < destY) {
                currentY = destY;
            }
        }
    }

    // Choix de la direction de l'animation
    protected void updateDirection(){
        if (Math.abs(destX - currentX) > 0.0001) {
            if (destX > currentX) {
                animationFourmi.setDirectionDroite();
            } else {
                animationFourmi.setDirectionGauche();
            }
        }
        // Sinon, on regarde la différence en Y
        else if (Math.abs(destY - currentY) > 0.0001) {
            if (destY > currentY) {
                animationFourmi.setDirectionBas();
            } else {
                animationFourmi.setDirectionHaut();
            }
        }
    }

    protected abstract void updateVitesse();

    public boolean isDone() {
        return isDone;
    }

    // Accès à l'animation, pour dessiner la frame courante
    public SpriteAnimation getSpriteAnim() {
        return animationFourmi;
    }
}
