package main;

import javax.swing.SwingUtilities;

import model.Fourmi;
import model.GestionnaireDeplacement;
import model.GestionnaireEnergie;
import model.Terrain;
import view.GameFrame;
import view.RepaintGameFrame;

public class Main {
    public static void main(String[] args) {
        Terrain t = new Terrain();
        GestionnaireEnergie ge = new GestionnaireEnergie(t);
        GestionnaireDeplacement gd = new GestionnaireDeplacement(t);
        GameFrame gf = new GameFrame(t);
        RepaintGameFrame rgf = new RepaintGameFrame(gf);
        ge.start();
        gd.start();
        rgf.start();

        /*
        SwingUtilities.invokeLater(() -> {
            new GameFrame(t);
        });
        */
    }
}
