package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.deplacements.Deplacement;
import model.objetsFixes.ObjetFixe;
import model.objetsFixes.Ressource;
import view.animations.CrapaudIdleAnimation;

public class Crapaud {
    private Terrain terrain;

    private int x, y;
    private int dx, dy;
    private int visionRange;

    private final static int MAX_SATIETE = 10; // à partir de là, il s'endort
    private final static int SATIETE_AU_REVEIL = 5; // il se réveille à ce niveau de satiété
    private final static int SEUIL_FAIM = 3; // en dessous de ça, il mange aussi les ressources
    private int satiete;

    private final static int DUREE_SIESTE = 10000;
    private int remainingSleepTime;
    private boolean isAsleep;

    private CrapaudIdleAnimation idleAnimation;
    public final static Image zzzIcon = new ImageIcon(Crapaud.class.getResource("/resources/Frog/zZz.png")).getImage();

    private static final Random random = new Random();

    public Crapaud(Terrain t, int startX, int startY, int visionRange) {
        terrain = t;
        this.x = startX;
        this.y = startY;
        this.visionRange = visionRange;
        randomizeDirection();
        idleAnimation = new CrapaudIdleAnimation();
        satiete = SATIETE_AU_REVEIL;
        remainingSleepTime = 0;
        isAsleep = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getCurrentFrame() {
        return idleAnimation.getCurrentFrame();
    }

    public int getFrameWidth() {
        return idleAnimation.getWidth();
    }

    public int getFrameHeight() {
        return idleAnimation.getHeight();
    }

    public int getVisionRange() {
        return visionRange;
    }

    public void update(int timeSinceLastUpdate) {
        // s'il dort, ne rien faire
        if (isAsleep) {
            sleep(timeSinceLastUpdate);
            return;
        }

        // Modification aléatoire de la direction (1 % de chance)
        if (random.nextDouble() < 0.01) {
            randomizeDirection();
        }

        int newX = x + dx;
        int newY = y + dy;

        // Vérification des limites du terrain
        if (newX < 50 || newX + 50 > Terrain.LARGEUR) {
            dx = -dx;
            newX = x + dx;
        }
        if (newY < 50 || newY + 50 > Terrain.HAUTEUR) {
            dy = -dy;
            newY = y + dy;
        }

        ArrayList<ObjetFixe> objets = Terrain.getObjetsFixes();
        
        x = newX;
        y = newY;

        // Consommation des fourmis situées dans les ressources visibles dans le cône de
        // vision de 90°
        // on récupère les ressources
        ArrayList<Ressource> rsrc = objets.stream()
                .filter(Ressource.class::isInstance)
                .map(Ressource.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));

        // on cherche la nourriture dans le champs de vision du crapaud
        // en priorité les fourmis en déplacement
        for (Deplacement depl : terrain.expeditions) {
            double distance = Math.hypot(depl.getX() - x, depl.getY() - y);
            if (distance <= visionRange && isInVisionCone(depl.getX(), depl.getY())) {
                terrain.expeditions.remove(depl);
                satiete = 10; 
                fallAsleep();
                idleAnimation.updateFrame();
                return; // on ne mange qu'une chose par appel
            }
        }
        // ensuite les fourmis dans les ressources
        for (Ressource r : rsrc) {
            // on vérifie si la ressource est visible par le crapaud
            double distance = Math.hypot(r.getX() - x, r.getY() - y);
            if (distance <= visionRange && isInVisionCone(r.getX(), r.getY())) {
                // la ressource est visible
                if (r.getNbrFourmis() > 0) {
                    // la ressource contient au moins une fourmi
                    r.removeOneAnt();
                    MusicPlayer.playZinou2();
                    satiete = 10;
                    fallAsleep();
                    idleAnimation.updateFrame();
                    return; // on ne mange qu'une chose par appel
                }
            }
        }
        // en dernier recours, le crapaud mange les ressources qu'il voit s'il a faim
        if (satiete > SEUIL_FAIM) {
            idleAnimation.updateFrame();
            return;
        }
        for (Ressource r : rsrc) {
            double distance = Math.hypot(r.getX() - x, r.getY() - y);
            if (distance <= visionRange && isInVisionCone(r.getX(), r.getY())) {
                // la ressource est visible, le crapaud la mange, les fourmis à l'intérieur
                // disparaissent
                terrain.removeRessource(r.getId());
                MusicPlayer.playZinou2();

                satiete += 2; // chaque ressource donne 2 points au crapaud indépendamment de sa valeur
                              // nutritive
                // on verifie s'il a maintenant dépassé le seuil de satiete
                if (satiete >= MAX_SATIETE) {
                    fallAsleep();
                }
                idleAnimation.updateFrame();
                return; // on ne mange qu'une chose par appel
            }
        }

        idleAnimation.updateFrame();
    }

    // Vérifie si une cible se trouve dans le cône de vision à 90° par rapport à la
    // direction de déplacement
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
        if (dx == 0 && dy == 0) {
            dx = 1; // Assurer un mouvement minimal
        }
    }

    private void sleep(int time) {
        remainingSleepTime -= time;
        if (remainingSleepTime <= 0) {
            isAsleep = false;
            satiete = SATIETE_AU_REVEIL;
        }
    }

    private void fallAsleep() {
        isAsleep = true;
        remainingSleepTime = DUREE_SIESTE;
        MusicPlayer.playZinou();
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    public double getSatiete() {
        return satiete / (double) MAX_SATIETE;
    }

    public void decrSatiete() {
        if (satiete > 0) {
            satiete--;
        }
    }

    public int getDirectionAngle() {
            double angleRad = Math.atan2(-dy, dx);
            int angleDeg = (int) Math.toDegrees(angleRad);
            if (angleDeg < 0) {
                angleDeg += 360;
            }
            return angleDeg;
        
    }

    // Je veux récuperer l'etat du crapaud pour l'afficher dans le panel
    public String getEtat() {
        if (isAsleep) {
            return "sommeil";
        } else if (satiete <= SEUIL_FAIM) {
            return "faim";
        } else {
            return "normal";
        }
    }
}
