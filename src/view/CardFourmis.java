package view;

import controller.DestinationSelectionnee;
import controller.FourmiController;
import java.awt.*;
import javax.swing.*;
import model.Fourmi;
import model.ObjetFixe;

public class CardFourmis extends JPanel {
    private JProgressBar barreEnergie; // Barre d'énergie
    private JLabel lblNumeroFourmi; // Numéro de la fourmi
    private JButton btnExpedition; // Bouton pour envoyer en expédition
    private Fourmi fourmi; // Référence à la fourmi
    private Thread threadEnergie; // Thread pour animer la barre d'énergie

    public CardFourmis(Fourmi fourmi, DestinationSelectionnee ds, ObjetFixe objF, FourmiController controller) {
        this.fourmi = fourmi;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(300, 80)); // Taille fixe pour chaque carte

        // En-tête : Numéro de la fourmi
        lblNumeroFourmi = new JLabel("Fourmi #" + fourmi.getId(), SwingConstants.CENTER);
        lblNumeroFourmi.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblNumeroFourmi, BorderLayout.NORTH);

        // Centre : Barre d'énergie
        barreEnergie = new JProgressBar(0, Fourmi.MAX_ENERGIE);
        barreEnergie.setValue(fourmi.getEnergie());
        barreEnergie.setStringPainted(true);
        barreEnergie.setForeground(new Color(0, 200, 0)); // Couleur verte
        barreEnergie.setBackground(new Color(200, 200, 200)); // Couleur de fond
        add(barreEnergie, BorderLayout.CENTER);

        // Bas : Bouton "Envoyer en expédition"
        btnExpedition = new JButton("Envoyer en expédition");
        btnExpedition.addActionListener(e -> {
            controller.envoyerEnExpedition(fourmi, objF, ds);
        });
        add(btnExpedition, BorderLayout.SOUTH);

        // Lancer l'animation de la barre d'énergie
        lancerAnimationEnergie();
    }

    // Méthode pour lancer l'animation de la barre d'énergie
    private void lancerAnimationEnergie() {
        BarreEnergieAnimation animation = new BarreEnergieAnimation(barreEnergie, fourmi);
        animation.start(); // Démarrer le thread directement
    }

    // Méthode pour arrêter l'animation (par exemple, lorsque la carte est
    // supprimée)
    public void arreterAnimation() {
        if (threadEnergie != null && threadEnergie.isAlive()) {
            threadEnergie.interrupt();
        }
    }

    // Méthode pour mettre à jour l'énergie de la fourmi
    public void mettreAJourEnergie() {
        barreEnergie.setValue(fourmi.getEnergie());
    }

    public Fourmi getFourmi() {
        return fourmi;
    }
}