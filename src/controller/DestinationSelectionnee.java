package controller;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import model.Terrain;
import model.objetsFixes.Nid;
import model.objetsFixes.ObjetFixe;
import model.objetsFixes.Ressource;

public class DestinationSelectionnee {

    private Terrain terrain;
    private boolean active;
    private ObjetFixe depart;
    private Integer idFourmi;
    private Runnable onDestinationValidee;
    private FourmiController fourmiController;

    public DestinationSelectionnee(Terrain terrain, FourmiController controller) {
        this.terrain = terrain;
        this.active = false;
        this.depart = null;
        this.idFourmi = null;
        this.fourmiController = controller;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(ObjetFixe dep, Integer id, Runnable callback) {
        this.active = true;
        this.depart = dep;
        this.idFourmi = id;
        this.onDestinationValidee = callback;
        //System.out.println("Destination selection active.");
    }

    // Méthode à appeler lors d'un clic pour traiter la sélection de destination
    public void handleMouseClicked(MouseEvent e) {
        ObjetFixe dest = terrain.getEltClic(e.getX(), e.getY());
        if (dest != null) {
            // Vérifier si la destination est valide
            // On ne peut pas se déplacer vers un abri plein
            if (!fourmiController.isDestinationValide(dest)) {
                JOptionPane.showMessageDialog(null,
                        "Cet abri est plein, choisissez une autre destination.",
                        "Abri plein",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // On ne peut pas se déplacer vers une ressource pleine
            if (dest instanceof Ressource && ((Ressource) dest).isReadyToGo()) {
                JOptionPane.showMessageDialog(null,
                        "Cette ressource est pleine, choisissez une autre destination.",
                        "Ressource pleine",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // On ne peut pas se déplacer vers un nid quand on est dans le nid
            if (dest instanceof Nid && depart instanceof Nid) {
                JOptionPane.showMessageDialog(null,
                        "Choisissez une autre destination.",
                        "Déplacement impossible",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            terrain.deplacerFourmi(idFourmi, depart, dest);
            if (onDestinationValidee != null) {
                onDestinationValidee.run(); // Supprimer la carte uniquement après une destination valide
            }
            active = false;
            depart = null;
            idFourmi = null;
            onDestinationValidee = null;
        }
    }
}
