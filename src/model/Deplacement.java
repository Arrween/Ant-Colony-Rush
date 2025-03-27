package model;

import view.SpriteAnimation;

public class Deplacement {
    private Terrain t;
    private Fourmi f;
    private int currentX, currentY, destX, destY, depX, depY;
    private ObjetFixe dest;
    private boolean isDone = false;

    private static int compteur = 0;

    private int numero;

    private SpriteAnimation animationFourmi;
    private double vitesse = 1.0 ;

    public Deplacement(Terrain t, Fourmi f, ObjetFixe dest, ObjetFixe depart) {
        this.t = t;
        this.f = f;
        this.currentX = f.getX();
        this.currentY = f.getY();
        this.dest = dest;
        this.destX = dest.getX();
        this.destY = dest.getY();
        numero = ++compteur;
        depX = depart.getX();
        depY = depart.getY();

        // Initialiser l'animation
        animationFourmi = new SpriteAnimation();
        updateDirection(); // Choix initial de la direction
    }

    public void decrEnergieFourmi() {
        f.decrEnergie();
    }

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
    public void avancer() {
        // System.out.println("Fourmi " + f.getId() + " en déplacement");
        // Mise à jour de la position, direction et animation de la fourmi
        updatePosition();
        updateDirection();
        animationFourmi.updateFrame();
    }

    private void updatePosition() {
        // algo de déplacmenent////
        if (currentX < destX) {
            currentX += 10;
            if (currentX > destX) {
                currentX = destX;
            }
        } else if (currentX > destX) {
            currentX -= 10;
            if (currentX < destX) {
                currentX = destX;
            }
        } else if (currentY < destY) {
            currentY += 10;
            if (currentY > destY) {
                currentY = destY;
            }
        } else if (currentY > destY) {
            currentY -= 10;
            if (currentY < destY) {
                currentY = destY;
            }
        }

        // si déplacment fini ajouter la fourmi à l'objet destination et supprimer le
        // déplacement de la liste de déplacements du terrain
        if (!isDone && currentX == destX && currentY == destY) {
            dest.ajouterFourmi(f);
            isDone = true;
        }
    }

    // Choix de la direction de l'animation
    private void updateDirection(){
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

    public boolean isDone() {
        return isDone;
    }

    // Accès à l'animation, pour dessiner la frame courante
    public SpriteAnimation getSpriteAnim() {
        return animationFourmi;
    }
}
