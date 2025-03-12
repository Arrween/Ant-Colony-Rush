package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Terrain;
import view.Affichage;

public class ReactionClic implements MouseListener {

    private Terrain t;
    private Point p;

    public ReactionClic(Terrain t, Point p) {
        super();
        this.t = t;
        this.p = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        t.getEltClic((int) (e.getX() / (Affichage.RATIO_X)), (int) (e.getY() / (Affichage.RATIO_Y)));

        p.setLocation((int) (e.getX() / (Affichage.RATIO_X)), (int) (e.getY() / (Affichage.RATIO_Y)));

        System.out.println("Clic en (" + p.getX() + ", " + p.getY() + ")");
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