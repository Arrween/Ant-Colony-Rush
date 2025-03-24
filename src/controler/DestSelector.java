package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.ObjetFixe;
import model.Terrain;
import view.Affichage;

public class DestSelector implements MouseListener {

    private Terrain t;
    private boolean active;
    private ObjetFixe depart;
    private Integer idFourmi;

    public DestSelector(Terrain t) {
        super();
        this.t = t;
        active = false;
        depart = null;
        idFourmi = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (active) {
            ObjetFixe dest = null;
            while (dest == null) {
                dest = t.getEltClic((int) (e.getX() / (Affichage.RATIO_X)), (int) (e.getY() / (Affichage.RATIO_Y)));
            }
            t.deplacerFourmi(idFourmi, depart, dest);

            this.active = false;
            depart = null;
            idFourmi = null;
        }
    }

    public void setActive(ObjetFixe dep, Integer id) {
        this.active = true;
        depart = dep;
        idFourmi = id;
        System.out.println("set Active");
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