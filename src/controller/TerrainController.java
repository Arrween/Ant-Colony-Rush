package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import model.Terrain;
import model.objetsFixes.Abri;
import model.objetsFixes.Nid;
import model.objetsFixes.ObjetFixe;
import model.objetsFixes.Ressource;
import view.panels.TerrainPanel;

public class TerrainController extends MouseAdapter implements ActionListener, KeyListener {

    private Terrain terrain;
    private TerrainPanel terrainPanel; // Pour pouvoir faire un repaint()
    private TerrainPanel.ControlPanelListener controlListener;
    private DestinationSelectionnee destSelector; // Renommage pour corriger la faute
    private int lastMouseX;
    private int lastMouseY;
    private GestionScore gestionScore;
    private FourmiController fourmiController;

    /**
     * Constructeur du contrôleur, recevant :
     * - le modèle (Terrain)
     * - la vue (TerrainPanel) pour pouvoir redessiner
     * - le controlListener (pour la gestion du clic sur les Nids).
     */
    public TerrainController(Terrain terrain, TerrainPanel terrainPanel,
            TerrainPanel.ControlPanelListener controlListener, GestionScore gestionScore,
            FourmiController fourmiController) {
        this.terrain = terrain;
        this.terrainPanel = terrainPanel;
        this.controlListener = controlListener;
        this.gestionScore = gestionScore;
        this.fourmiController = fourmiController;
        // On initialise la sélection de destination
        this.destSelector = new DestinationSelectionnee(terrain, fourmiController);
    }

    public DestinationSelectionnee getDestSelector() {
        return destSelector;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // On récupère les coordonnées du clic de la souris
        // et on les stocke pour les utiliser dans la méthode keyPressed
        // (pour savoir quelle ressource déplacer)
        lastMouseX = e.getX();
        lastMouseY = e.getY();

        // Réinitialiser la sélection de tous les objets
        for (ObjetFixe obj : Terrain.getObjetsFixes()) {
            obj.setSelected(false);
        }

        // Si la sélection de destination est active, on délègue le clic
        if (destSelector.isActive()) {
            destSelector.handleMouseClicked(e);
            return;
        }
        // Sinon, on vérifie si le clic est sur un Nid
        ObjetFixe clickedObj = terrain.getEltClic(e.getX(), e.getY());
        if (clickedObj != null) {
            clickedObj.setSelected(true); // Marquer l'objet comme sélectionné
        }
        // Redessiner le panneau pour refléter les changements
        terrainPanel.repaint();
        if (clickedObj instanceof Nid) {
            Nid nid = (Nid) clickedObj;
            if (controlListener != null) {
                controlListener.nidClicked(nid);
            }
        } else if (clickedObj instanceof Ressource) {
            Ressource ressource = (Ressource) clickedObj;
            if (controlListener != null) {
                controlListener.ressourceClicked(ressource);
            }
        } else if (clickedObj instanceof Abri) {
            Abri abri = (Abri) clickedObj;
            if (controlListener != null) {
                controlListener.abriClicked(abri); // Appeler la méthode abriClicked
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Vérifier si une ressource est sélectionnée
            ObjetFixe selectedObj = terrain.getEltClic(lastMouseX, lastMouseY);
            if (selectedObj instanceof Ressource ressource) {
                if (ressource.canBeCollected()) {
                    if (ressource.isReadyToGo() && !ressource.isMoving()) {
                        ressource.deplacerVersNid(terrain, terrain.getNid(), gestionScore);
                        terrainPanel.repaint(); // Mettre à jour l'affichage
                    } else if (ressource.isMoving()) {
                        JOptionPane.showMessageDialog(null, "Cette ressource est déjà en déplacement !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pas assez de fourmis pour déplacer la ressource !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cette ressource n'est pas prête à être collectée !");
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implémentation vide ou logique supplémentaire
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implémentation vide ou logique supplémentaire
    }
}