package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import view.CrapaudIdleAnimation;

public class Crapaud {
    private Terrain terrain;

    private int x,y;
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
        if (newX < 0 || newX + 50  > Terrain.LARGEUR) {
            dx = -dx;
            newX = x + dx;
        }
        if (newY < 0 || newY + 50 > Terrain.HAUTEUR) {
            dy = -dy;
            newY = y + dy;
        }

        ArrayList<ObjetFixe> objets = Terrain.getObjetsFixes();

        // Interaction avec les objets fixes
        for (ObjetFixe obj : objets) {
            if (obj.hitBoxCliquee(newX, newY)) {
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

        // Consommation des fourmis situées dans les ressources visibles dans le cône de
        // vision de 90°
        // on récupère les ressources
        ArrayList<Ressource> rsrc = objets.stream()
                .filter(Ressource.class::isInstance)
                .map(Ressource.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));

        if (satiete <= SEUIL_FAIM) {
            // le crapaud regarde en priorité les ressources
            for (Ressource r : rsrc) {
                // on vérifie si la ressource est visible par le crapaud
                double distance = Math.hypot(r.getX() - x, r.getY() - y);
                if (distance <= visionRange && isInVisionCone(r.getX(), r.getY())) {
                    // la ressource est visible
                    terrain.removeRessource(r.getId());
                    satiete += r.fourmis.size() + r.getValeurNutritive();
                    // on verifie s'il a maintenant dépassé le seuil de satiete
                    if (satiete >= MAX_SATIETE) {
                        fallAsleep();
                    }
                    return; // on ne mange qu'une chose par appel
                }
            }
        } else {
            // le crapaud regarde en priorité les fourmis en déplacement, et ensuite les
            // fourmis dans les ressoruces
            for (Deplacement depl : terrain.expeditions) {
                double distance = Math.hypot(depl.getX() - x, depl.getY() - y);
                if (distance <= visionRange && isInVisionCone(depl.getX(), depl.getY())) {
                    terrain.expeditions.remove(depl);
                    satiete++;
                    if (satiete >= MAX_SATIETE) {
                        fallAsleep();
                    }
                    return; // on ne mange qu'une chose par appel
                }
            }

            for (Ressource r : rsrc) {
                // on vérifie si la ressource est visible par le crapaud
                double distance = Math.hypot(r.getX() - x, r.getY() - y);
                if (distance <= visionRange && isInVisionCone(r.getX(), r.getY())) {
                    // la ressource est visible
                    if (r.fourmis.size() > 0) {
                        r.fourmis.remove(0);
                        satiete++;
                        if (satiete >= MAX_SATIETE) {
                            fallAsleep();
                        }
                        return; // on ne mange qu'une chose par appel
                    }
                }
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
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    public double getSatiete() {
        return satiete / (double) MAX_SATIETE;
    }

    public void decrSatiete() {
        satiete--;
    }

    public int getDirectionAngle() {
        double angleRad = Math.atan2(-dy, dx);
        int angleDeg = (int) Math.toDegrees(angleRad);
        if (angleDeg < 0) {
            angleDeg += 360;
        }
        return angleDeg;
    }
}
