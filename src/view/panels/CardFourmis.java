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
    private JProgressBar barreEnergie; // Barre d'énergie
    private JLabel lblNumeroFourmi; // Numéro de la fourmi
    private JButton btnExpedition; // Bouton pour envoyer en expédition
    private Fourmi fourmi; // Référence à la fourmi
    private Thread threadEnergie; // Thread pour animer la barre d'énergie

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 14);

    public CardFourmis(Fourmi fourmi, DestinationSelectionnee ds, ObjetFixe objF, FourmiController controller) {
        this.fourmi = fourmi;

        setLayout(new BorderLayout());
        setBackground(BEIGE);
        setBorder(BorderFactory.createLineBorder(GREEN, 1));
        setPreferredSize(new Dimension(300, 80));
        setOpaque(true);

        // En-tête : affichage du numéro de la fourmi
        lblNumeroFourmi = new JLabel("Fourmi #" + fourmi.getId(), SwingConstants.CENTER);
        lblNumeroFourmi.setFont(CONTROL_HEADER);
        lblNumeroFourmi.setForeground(DARK_BROWN);
        lblNumeroFourmi.setBorder(new EmptyBorder(5, 0, 5, 0));
        add(lblNumeroFourmi, BorderLayout.NORTH);

        // Centre : barre d'énergie avec affichage des valeurs
        barreEnergie = new JProgressBar(0, Fourmi.MAX_ENERGIE);
        barreEnergie.setValue(fourmi.getEnergie());
        barreEnergie.setStringPainted(true);
        barreEnergie.setForeground(GREEN);
        barreEnergie.setBackground(new Color(200, 200, 200));
        barreEnergie.setFont(CONTROL_FONT);
        add(barreEnergie, BorderLayout.CENTER);

        // Bas : bouton "Envoyer en expédition" avec les styles adaptés
        btnExpedition = new JButton("Envoyer en expédition");
        btnExpedition.setFont(CONTROL_FONT);
        btnExpedition.setForeground(DARK_BROWN);
        btnExpedition.setBackground(BEIGE);
        btnExpedition.setFocusPainted(false);
        btnExpedition.addActionListener(e -> {
            controller.envoyerEnExpedition(fourmi, objF, ds);
        });
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        btnPanel.setBackground(BEIGE);
        btnPanel.add(btnExpedition);
        add(btnPanel, BorderLayout.SOUTH);

        lancerAnimationEnergie();
    }

    // Lance l'animation de la barre d'énergie
    private void lancerAnimationEnergie() {
        BarreEnergieAnimation animation = new BarreEnergieAnimation(barreEnergie, fourmi);
        animation.start();
    }

    // Arrête l'animation (par exemple lorsque la carte est supprimée)
    public void arreterAnimation() {
        if (threadEnergie != null && threadEnergie.isAlive()) {
            threadEnergie.interrupt();
        }
    }

    // Met à jour l'affichage de l'énergie
    public void mettreAJourEnergie() {
        barreEnergie.setValue(fourmi.getEnergie());
    }

    public Fourmi getFourmi() {
        return fourmi;
    }
}
