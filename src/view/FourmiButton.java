package view;

import javax.swing.JButton;

import controler.DestSelector;
import model.Fourmi;
import model.ObjetFixe;

public class FourmiButton extends JButton {

    private Fourmi fourmi;
    private int index;

    public FourmiButton(Fourmi fourmi, DestSelector ds, ObjetFixe o) {
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
