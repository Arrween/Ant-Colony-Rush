package view.panels;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import model.*;
import model.deplacements.DeplacementRessource;
import model.objetsFixes.Abri;
import model.objetsFixes.Nid;
import model.objetsFixes.ObjetFixe;
import model.objetsFixes.Ressource;
import model.objetsFixes.RessourceTemporaire;
import view.BackgroundGrid;
import view.animations.DeathMessage;
import view.animations.SpriteAnimation;

public class TerrainPanel extends JPanel {
    private Terrain terrain;
    private ControlPanelListener controlListener;
    public static final int TAILLE_CRAPAUD = 160;
    public static final int TAILLE_FOURMIS = 40;
    public static final int TAILLE_OBJETS = (int) (2 * ObjetFixe.HALF_SIZE * 1);
    private BackgroundGrid backgroundGrid;
    private List<DeathMessage> deathMessages = new ArrayList<>();

    // Champs pour la transition smooth du mode nuit
    private int currentAlpha = 0; // Opacité actuelle de la surcouche (0 = jour, jusqu'à 150 = nuit complète)
    private int targetAlpha = 0; // Opacité cible : 0 pour le jour, DESIRED_ALPHA pour la nuit
    private final int DESIRED_ALPHA = 170; // Valeur d'opacité souhaitée en mode nuit
    private final int alphaStep = 5; // Pas d'incrémentation à chaque tick

    // Timer pour la transition progressif de l'alpha
    private Timer alphaTransitionTimer;

    public interface ControlPanelListener {
        void nidClicked(Nid nid);

        void ressourceClicked(Ressource ressource);

        void abriClicked(Abri abri);
    }

    public TerrainPanel(Terrain terrain) {
        this.terrain = terrain;
        setPreferredSize(new Dimension(Terrain.LARGEUR, Terrain.HAUTEUR));
        backgroundGrid = new BackgroundGrid(Terrain.LARGEUR, Terrain.HAUTEUR);

        // Timer qui met à jour currentAlpha toutes les 50 ms pour une transition fluide
        alphaTransitionTimer = new Timer(50, e -> {
            if (currentAlpha < targetAlpha) {
                currentAlpha = Math.min(currentAlpha + alphaStep, targetAlpha);
                repaint();
            } else if (currentAlpha > targetAlpha) {
                currentAlpha = Math.max(currentAlpha - alphaStep, targetAlpha);
                repaint();
            }
        });
        alphaTransitionTimer.start();
    }

    public void addDeathMessage(int x, int y) {
        deathMessages.add(new DeathMessage(x, y));
    }

    // La méthode setNightMode ne change plus directement l'état binaire,
    // elle fixe la valeur cible de l'opacité
    public void setNightMode(boolean nightMode) {
        if (nightMode) {
            targetAlpha = DESIRED_ALPHA;
        } else {
            targetAlpha = 0;
        }
    }

    // Getter optionnel : on considère que le mode nuit est activé quand targetAlpha
    // atteint DESIRED_ALPHA
    public boolean isNightMode() {
        return targetAlpha == DESIRED_ALPHA;
    }

    public void setEcouteurPanneauDeControle(ControlPanelListener ecouteur) {
        this.controlListener = ecouteur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Affichage de l'arrière-plan
        backgroundGrid.draw(g);

        // Affichage des objets fixes
        for (ObjetFixe obj : Terrain.getObjetsFixes()) {
            Image img = obj.getImage();
            if (img != null) {
                int x = obj.getX();
                int y = obj.getY();
                g.drawImage(img, x - TAILLE_OBJETS / 2, y - TAILLE_OBJETS / 2, TAILLE_OBJETS, TAILLE_OBJETS, this);

                // Afficher un indicateur si l'abri est plein
                if (obj instanceof Abri && ((Abri) obj).isPlein()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(new Color(255, 0, 0, 100));
                    g2d.fillRect(x - TAILLE_OBJETS / 2, y - TAILLE_OBJETS / 2, TAILLE_OBJETS, TAILLE_OBJETS);
                }

                // Afficher un indicateur si la ressource est pleine
                if (obj instanceof Ressource && ((Ressource) obj).isReadyToGo()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(new Color(255, 0, 0, 100)); // Rouge semi-transparent
                    g2d.fillRect(x - TAILLE_OBJETS / 2, y - TAILLE_OBJETS / 2, TAILLE_OBJETS, TAILLE_OBJETS);
                }

                // temporairement pour se repérer
                g.drawOval(x - 3, y - 3, 6, 6);

                if (obj.isSelected()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(Color.RED);
                    g2d.drawRect(x - TAILLE_OBJETS / 2, y - TAILLE_OBJETS / 2, TAILLE_OBJETS, TAILLE_OBJETS);
                }

                if (obj instanceof RessourceTemporaire) {
                    RessourceTemporaire ressourceTemp = (RessourceTemporaire) obj;
                    String str = (ressourceTemp.getTempsRestant() / 1000) + "s";
                    g.drawString(str, x - TAILLE_OBJETS / 2, y - TAILLE_OBJETS / 2 - 20);
                }
            }
            if (obj.getNbFourmis() > 0) {
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(obj.getNbFourmis()), obj.getX() - 5, obj.getY() + 5);
            }
        }

        // Affichage des déplacements
        for (var dep : terrain.getDeplacements()) {
            g.drawLine(dep.getDepX(), dep.getDepY(), dep.getDestX(), dep.getDestY());
            SpriteAnimation anim = dep.getSpriteAnim();
            if (anim != null) {
                var frame = anim.getCurrentSprite();
                if (frame != null) {
                    int x = dep.getX();
                    int y = dep.getY();
                    g.drawImage(frame,
                            x - TAILLE_FOURMIS / 2,
                            y - TAILLE_FOURMIS / 2,
                            TAILLE_FOURMIS,
                            TAILLE_FOURMIS,
                            this);
                    // temporairement pour se repérer
                    g.drawOval(x - 3, y - 3, 6, 6);
                }
            }
            if (dep instanceof DeplacementRessource) {
                Ressource ressource = ((DeplacementRessource) dep).getRessource();
                Image img = ressource.getImage();
                if (img != null) {
                    int x = dep.getX() - TAILLE_OBJETS / 2;
                    int y = dep.getY() - TAILLE_OBJETS / 2;
                    g.drawImage(img, x, y, TAILLE_OBJETS, TAILLE_OBJETS, this);
                }
            }
        }

        // Affichage du crapaud
        model.Crapaud crapaud = terrain.getCrapaud();
        if (crapaud != null) {
            Graphics2D g2d = (Graphics2D) g;
            int diametre = 2 * crapaud.getVisionRange();
            g2d.setColor(new Color(255, 0, 0, 50));
            g2d.fillArc(crapaud.getX() - crapaud.getVisionRange(), crapaud.getY() - crapaud.getVisionRange(), diametre,
                    diametre, crapaud.getDirectionAngle() - 45, 90);

            java.awt.image.BufferedImage frame = crapaud.getCurrentFrame();
            int x = crapaud.getX();
            int y = crapaud.getY();
            g.drawImage(frame, x - TAILLE_CRAPAUD / 2, y - TAILLE_CRAPAUD / 2, TAILLE_CRAPAUD, TAILLE_CRAPAUD, this);
            // temporairement pour se repérer
            g.drawOval(x - 3, y - 3, 6, 6);
        }

        // Dessin de la surcouche du mode nuit avec l'opacité actuelle
        if (currentAlpha > 0) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, currentAlpha));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
        // Après l'affichage de tous les autres éléments, dessiner les messages de mort
        Graphics2D g2d = (Graphics2D) g;
        Iterator<DeathMessage> iterator = deathMessages.iterator();
        while (iterator.hasNext()) {
            DeathMessage message = iterator.next();
            if (message.isExpired()) {
                iterator.remove();
            } else {
                message.updateOpacity();
                message.draw(g2d);
            }
        }

        // Remettre le composite par défaut après avoir dessiné les messages
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

    }
}
