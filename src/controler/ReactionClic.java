package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Terrain;
import view.Affichage;

public class ReactionClic implements MouseListener {

    private Terrain t;

    public ReactionClic(Terrain t) {
        super();
        this.t = t;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        t.getEltClic((int)(e.getX()/(Affichage.RATIO_X)), (int)(e.getY()/(Affichage.RATIO_Y)));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}