package view;

import javax.swing.JButton;
import model.Fourmi;

public class FourmiButton extends JButton {

    private Fourmi fourmi;
    private int index;

    public FourmiButton(Fourmi fourmi, int index) {
        super("Fourmi " + index);
        this.fourmi = fourmi;
        this.index = index;
        addActionListener(e -> {
            FourmiDetail fourmiDetail = new FourmiDetail(fourmi, index);
            fourmiDetail.setVisible(true);
            // controler

        });
    }

    public Fourmi getFourmi() {
        return fourmi;
    }

}
