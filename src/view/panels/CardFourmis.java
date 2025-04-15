package view.panels;

import controller.DestinationSelectionnee;
import controller.FourmiController;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Fourmi;
import model.objetsFixes.ObjetFixe;
import view.animations.BarreEnergieAnimation;

public class CardFourmis extends JPanel {
    private JProgressBar barreEnergie;
    private JLabel lblNumeroFourmi;
    private JButton btnExpedition;
    private Fourmi fourmi;
    private Thread threadEnergie;

    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font CONTROL_BOLD = new Font("Segoe UI", Font.BOLD, 14);

    public CardFourmis(Fourmi fourmi, DestinationSelectionnee ds, ObjetFixe objF, FourmiController controller) {
        this.fourmi = fourmi;
        setLayout(new BorderLayout(5, 5));
        setBackground(BEIGE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GREEN, 1),
                new EmptyBorder(5, 5, 5, 5)));

        lblNumeroFourmi = new JLabel("Fourmi #" + fourmi.getId(), SwingConstants.CENTER);
        lblNumeroFourmi.setFont(CONTROL_BOLD);
        lblNumeroFourmi.setForeground(DARK_BROWN);
        add(lblNumeroFourmi, BorderLayout.NORTH);

        barreEnergie = new JProgressBar(0, Fourmi.MAX_ENERGIE);
        barreEnergie.setValue(fourmi.getEnergie());
        barreEnergie.setStringPainted(true);
        barreEnergie.setForeground(GREEN);
        add(barreEnergie, BorderLayout.CENTER);

        btnExpedition = new JButton("Envoyer en expédition");
        btnExpedition.setFont(CONTROL_FONT);
        btnExpedition.setBackground(BEIGE);
        btnExpedition.setForeground(DARK_BROWN);
        btnExpedition.addActionListener(e -> {
            controller.envoyerEnExpedition(fourmi, objF, ds);
        });
        add(btnExpedition, BorderLayout.SOUTH);

        // Lancement de l’animation
        BarreEnergieAnimation animation = new BarreEnergieAnimation(barreEnergie, fourmi);
        animation.start();
    }

    public void mettreAJourEnergie() {
        barreEnergie.setValue(fourmi.getEnergie());
    }

    public Fourmi getFourmi() {
        return fourmi;
    }

    public void arreterAnimation() {
        if (threadEnergie != null && threadEnergie.isAlive()) {
            threadEnergie.interrupt();
        }
    }
}
