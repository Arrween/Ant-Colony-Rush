package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Crapaud {
    private int x;
    private int y;
    private int visionRange;
    private int dx;
    private int dy;
    private Image imageCrapaud;
    private static final Random random = new Random();

    public Crapaud(int startX, int startY, int visionRange) {
        this.x = startX;
        this.y = startY;
        this.visionRange = visionRange;
        randomizeDirection();
        imageCrapaud = new ImageIcon(getClass().getResource("/ressources/Frog/Idle.png")).getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVisionRange() {
        return visionRange;
    }

    public Image getImage() {
        return imageCrapaud;
    }

    public void update(Terrain terrain) {
        // Changer de direction de manière aléatoire avec une probabilité de 5 %
        if (random.nextDouble() < 0.05) {
            randomizeDirection();
        }

        int newX = x + dx;
        int newY = y + dy;

        // Vérifier les limites du terrain
        if (newX < 0 || newX > Terrain.LARGEUR) {
            dx = -dx;
            newX = x + dx;
        }
        if (newY < 0 || newY > Terrain.HAUTEUR) {
            dy = -dy;
            newY = y + dy;
        }

        // Gérer les interactions avec les objets fixes
        ArrayList<ObjetFixe> objets = Terrain.GetObjetsFixes();
        for (ObjetFixe obj : objets) {
            if (obj.hitBoxCliquee(newX, newY)) {
                // S'il rencontre une ressource, changer de direction
                if (obj instanceof Ressource) {
                    randomizeDirection();
                }
                // S'il rencontre un abri ou le nid, "sauter" par-dessus (double déplacement)
                if (obj instanceof Abri || obj instanceof Nid) {
                    newX = x + 2 * dx;
                    newY = y + 2 * dy;
                }
            }
        }
        x = newX;
        y = newY;

        // "Manger" les fourmis situées dans le champ de vision dans les objets fixes
        for (ObjetFixe obj : objets) {
            ArrayList<Fourmi> toRemove = new ArrayList<>();
            for (Fourmi ant : obj.fourmis) {
                double distance = Math.sqrt(Math.pow(ant.getX() - x, 2) + Math.pow(ant.getY() - y, 2));
                if (distance <= visionRange) {
                    toRemove.add(ant);
                }
            }
            obj.fourmis.removeAll(toRemove);
        }

        /*
         * // Vérifier également les fourmis en expédition
         * ArrayList<Fourmi> toRemoveExp = new ArrayList<>();
         * for (Fourmi ant : terrain.fourmisEnExpe) {
         * double distance = Math.sqrt(Math.pow(ant.getX() - x, 2) + Math.pow(ant.getY()
         * - y, 2));
         * if(distance <= visionRange) {
         * toRemoveExp.add(ant);
         * }
         * }
         * terrain.fourmisEnExpe.removeAll(toRemoveExp);
         */

        // Vérifier également les fourmis en expédition
        ArrayList<Deplacement> toRemoveDepl = new ArrayList<>();
        for (Deplacement depl : terrain.expeditions) {
            double distance = Math.sqrt(Math.pow(depl.getX() - x, 2) + Math.pow(depl.getY() - y, 2));
            if (distance <= visionRange) {
                toRemoveDepl.add(depl);
            }
        }
        terrain.expeditions.removeAll(toRemoveDepl);

    }

    private void randomizeDirection() {
        dx = random.nextInt(3) - 1; // Valeur entre -1 et 1
        dy = random.nextInt(3) - 1;
        if (dx == 0 && dy == 0) {
            dx = 1; // Assurer un mouvement minimal
        }
    }
}
