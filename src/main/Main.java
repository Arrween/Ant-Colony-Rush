package main;

import javax.swing.SwingUtilities;
import model.GestionnaireEnergie;
import model.Terrain;
import view.GameFrame;

public class Main {
    public static void main(String[] args) {
        Terrain t = new Terrain();
        GestionnaireEnergie ge = new GestionnaireEnergie(t);
        ge.start();

        SwingUtilities.invokeLater(() -> {
            new GameFrame(t);
        });
    }
}
