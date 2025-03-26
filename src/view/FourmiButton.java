package view;

import controller.DestitationSelectionnee;
import javax.swing.JButton;
import model.Fourmi;
import model.ObjetFixe;

public class FourmiButton extends JButton {

    private Fourmi fourmi;
    private int index;

    public FourmiButton(Fourmi fourmi, DestitationSelectionnee ds, ObjetFixe o) {
        super("Fourmi " + fourmi.getId());
        this.fourmi = fourmi;
        this.index = index;
        addActionListener(e -> {
            FourmiDetail fourmiDetail = new FourmiDetail(fourmi, ds, o);
            fourmiDetail.setVisible(true);
        });
    }

    public Fourmi getFourmi() {
        return fourmi;
    }

}
