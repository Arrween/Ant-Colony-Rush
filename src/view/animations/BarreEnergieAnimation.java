package view.animations;

import javax.swing.*;
import model.Fourmi;

public class BarreEnergieAnimation extends Thread {
    private JProgressBar barreEnergie; // Barre d'énergie à afficher
    private Fourmi fourmi; // Référence à la fourmi associée
    private boolean animationActive = true; // Indique si l'animation est active
    private final int DELAY = 100; // Délai entre chaque mise à jour (100 ms)

    public BarreEnergieAnimation(JProgressBar barreEnergie, Fourmi fourmi) {
        this.barreEnergie = barreEnergie;
        this.fourmi = fourmi;
    }

    @Override
    public void run() {
        while (animationActive) {
            try {
                Thread.sleep(DELAY); // Attendre avant de mettre à jour l'affichage
            } catch (InterruptedException e) {
                animationActive = false; // Arrêter l'animation si le thread est interrompu
                Thread.currentThread().interrupt(); // Réinterrompre le thread
            }

            // Mettre à jour la barre d'énergie avec la valeur actuelle de la fourmi
            SwingUtilities.invokeLater(() -> barreEnergie.setValue(fourmi.getEnergie()));
        }
    }

    // Méthode pour arrêter l'animation
    public void arreterAnimation() {
        animationActive = false;
    }
}