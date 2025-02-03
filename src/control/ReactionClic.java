package control ;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Position;

public class ReactionClic extends MouseAdapter {
    
    // On stocke ici la Position passée au constructeur
    private Position maPosition;

    // Constructeur qui prend un objet Position en paramètre
    public ReactionClic(Position pos) {
        this.maPosition = pos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // À chaque clic : on augmente la hauteur de la Position
        maPosition.jump();
        System.out.println("Souris cliquée : hauteur = " + maPosition.get());
    }
}
