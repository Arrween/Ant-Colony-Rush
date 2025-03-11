package tests;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.Fourmi;
import view.PanneauDeControle; // Adjust the package name as needed

public class TestPanneauDeControle {
    public static void main(String[] args) {
        // On utilise SwingUtilities.invokeLater pour lancer l'interface sur le thread
        // dédié à Swing.
        SwingUtilities.invokeLater(() -> {
            // Création de la fenêtre principale
            JFrame frame = new JFrame("Test Panneau de Contrôle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            // Création d'une liste de quelques fourmis pour le test
            List<Fourmi> fourmis = new ArrayList<>();
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));
            fourmis.add(new Fourmi(10, 20, 100));
            fourmis.add(new Fourmi(30, 40, 80));
            fourmis.add(new Fourmi(50, 60, 60));

            // Valeur nutritive des ressources initiale (exemple)
            int valeurNutritiveRessources = 50;

            // Instanciation du panneau de contrôle avec les données de test
            PanneauDeControle panneau = new PanneauDeControle(valeurNutritiveRessources, fourmis);
            frame.getContentPane().add(panneau);

            // Affichage de la fenêtre
            frame.setVisible(true);
        });
    }
}
