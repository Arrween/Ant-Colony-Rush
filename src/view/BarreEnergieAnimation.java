package view;

import javax.swing.*;
import model.Fourmi;

public class BarreEnergieAnimation extends Thread {
    private JProgressBar barreEnergie; // Barre d'énergie à animer
    private Fourmi fourmi; // Référence à la fourmi associée
    private boolean animationActive = true; // Indique si l'animation est active
    private final int DELAY = 1000; // Délai entre chaque mise à jour (1 seconde)

    public BarreEnergieAnimation(JProgressBar barreEnergie, Fourmi fourmi) {
        this.barreEnergie = barreEnergie;
        this.fourmi = fourmi;
    }

    @Override
    public void run() {
        while (animationActive) {
            try {
                Thread.sleep(DELAY); // Attendre avant de diminuer l'énergie
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Diminuer l'énergie de la fourmi
            if (fourmi.getEnergie() > 0) {
                fourmi.decrEnergie();

                // Mettre à jour la barre d'énergie directement (sans thread-safe ici)
                barreEnergie.setValue(fourmi.getEnergie());
            } else {
                animationActive = false; // Arrêter l'animation si l'énergie est à 0
            }
        }
    }

    // Méthode pour arrêter l'animation
    public void arreterAnimation() {
        animationActive = false;
    }
}