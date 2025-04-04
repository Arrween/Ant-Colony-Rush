package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Deplacement;
import model.Nid;
import model.ObjetFixe;
import model.Ressource;
import model.Terrain;
import view.TerrainPanel;

public class TerrainController extends MouseAdapter implements ActionListener, KeyListener {

    private Terrain terrain;
    private TerrainPanel terrainPanel; // Pour pouvoir faire un repaint()
    private TerrainPanel.ControlPanelListener controlListener;
    private DestinationSelectionnee destSelector; // Renommage pour corriger la faute
    private Timer timer;
    private int lastMouseX;
    private int lastMouseY;

    /**
     * Constructeur du contrôleur, recevant :
     * - le modèle (Terrain)
     * - la vue (TerrainPanel) pour pouvoir redessiner
     * - le controlListener (pour la gestion du clic sur les Nids).
     */
    public TerrainController(Terrain terrain, TerrainPanel terrainPanel,
            TerrainPanel.ControlPanelListener controlListener) {
        this.terrain = terrain;
        this.terrainPanel = terrainPanel;
        this.controlListener = controlListener;

        // On initialise la sélection de destination
        this.destSelector = new DestinationSelectionnee(terrain);

        // On crée un Timer Swing pour rafraîchir le modèle toutes les 25 ms
        this.timer = new Timer(25, this);
        this.timer.start();
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
        // Si la sélection de destination est active, on délègue le clic
        if (destSelector.isActive()) {
            destSelector.handleMouseClicked(e);
            return;
        }
        // Sinon, on vérifie si le clic est sur un Nid
        ObjetFixe clickedObj = terrain.getEltClic(e.getX(), e.getY());
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mise à jour des déplacements (modèle)
        for (Deplacement d : terrain.getDeplacements()) {
            d.avancer();
        }
        terrain.updateCrapaud(); // déplacer & animer le crapaud
        // On redessine le TerrainPanel
        terrainPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Vérifier si une ressource est sélectionnée
            ObjetFixe selectedObj = terrain.getEltClic(lastMouseX, lastMouseY);
            if (selectedObj instanceof Ressource ressource) {
                if (ressource.isReadyToGo()) {
                    ressource.deplacerVersNid(terrain, terrain.getNid());
                    terrainPanel.repaint(); // Mettre à jour l'affichage
                } else {
                    JOptionPane.showMessageDialog(null, "Pas assez de fourmis pour déplacer la ressource !");
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