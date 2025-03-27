package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import view.CrapaudAnimation;

public class Crapaud {
    private int x;
    private int y;
    private int visionRange;
    private int dx;
    private int dy;

    private CrapaudAnimation animation;

    private Image imageCrapaud;
    private static final Random random = new Random();

    public Crapaud(int startX, int startY, int visionRange) {
        this.x = startX;
        this.y = startY;
        this.visionRange = visionRange;
        randomizeDirection();
        animation = new CrapaudAnimation();
    }

    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public BufferedImage getCurrentFrame() {
        return animation.getCurrentFrame();
    }

    public int getFrameWidth() {
        return animation.getWidth();
    }

    public int getFrameHeight() {
        return animation.getHeight();
    }

    public int getVisionRange() {
        return visionRange; 
        }

    public Image getImage() { 
        return imageCrapaud;
    }

    public void update(Terrain terrain) {
        // Modification aléatoire de la direction (1 % de chance)
        if(random.nextDouble() < 0.01) {  
            randomizeDirection();
        }

        int newX = x + dx;
        int newY = y + dy;

        // Vérification des limites du terrain
        if(newX < 0 || newX > Terrain.LARGEUR) {
            dx = -dx;
            newX = x + dx;
        }
        if(newY < 0 || newY > Terrain.HAUTEUR) {
            dy = -dy;
            newY = y + dy;
        }

        // Interaction avec les objets fixes
        ArrayList<ObjetFixe> objets = Terrain.getObjetsFixes();
        for (ObjetFixe obj : objets) {
            if(obj.hitBoxCliquee(newX, newY)) {
                // En cas de ressource, changer de direction
                if (obj instanceof Ressource) {
                    randomizeDirection();
                }
                // En cas d'abri ou de nid, effectuer un saut (double déplacement)
                if (obj instanceof Abri || obj instanceof Nid) {
                    newX = x + 2 * dx;
                    newY = y + 2 * dy;
                }
            }
        }
        x = newX;
        y = newY;

        // Consommation des fourmis visibles dans le cône de vision de 90°
        for (ObjetFixe obj : objets) {
            ArrayList<Fourmi> toRemove = new ArrayList<>();
            for (Fourmi ant : obj.fourmis) {
                double distance = Math.hypot(ant.getX() - x, ant.getY() - y);
                if(distance <= visionRange && isInVisionCone(ant.getX(), ant.getY())) {
                    toRemove.add(ant);
                }
            }
            obj.fourmis.removeAll(toRemove);
        }

        // Traitement similaire pour les fourmis en expédition
        ArrayList<Deplacement> toRemoveDepl = new ArrayList<>();
        for (Deplacement depl : terrain.expeditions) {
            double distance = Math.hypot(depl.getX() - x, depl.getY() - y);
            if(distance <= visionRange && isInVisionCone(depl.getX(), depl.getY())) {
                toRemoveDepl.add(depl);
            }
        }
        terrain.expeditions.removeAll(toRemoveDepl);
    }

    // Vérifie si une cible se trouve dans le cône de vision à 90° par rapport à la direction de déplacement
    private boolean isInVisionCone(int targetX, int targetY) {
        double angleToTarget = Math.atan2(targetY - y, targetX - x);
        double directionAngle = Math.atan2(dy, dx);
        double diff = Math.abs(angleToTarget - directionAngle);
        if (diff > Math.PI) {
            diff = 2 * Math.PI - diff;
        }
        // 45° en radians correspond à la moitié du cône de 90°
        return diff <= Math.PI / 4;
    }

    private void randomizeDirection() {
        dx = random.nextInt(3) - 1; // Valeur entre -1 et 1
        dy = random.nextInt(3) - 1;
        if(dx == 0 && dy == 0) {
            dx = 1; // Assurer un mouvement minimal
        }
    }


}
