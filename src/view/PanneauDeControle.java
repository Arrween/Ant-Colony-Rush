package view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import model.Fourmi;

public class PanneauDeControle extends JPanel {

    private JLabel lblValeurNutritiveRessources;
    private JPanel pnlFourmis;

    public PanneauDeControle(int valeurNutritiveRessources, List<Fourmi> fourmis) {
        setLayout(new BorderLayout());

        lblValeurNutritiveRessources = new JLabel("Valeur nutritive des ressources : " + valeurNutritiveRessources);
        add(lblValeurNutritiveRessources, BorderLayout.NORTH);

        pnlFourmis = new JPanel();
        pnlFourmis.setLayout(new BoxLayout(pnlFourmis, BoxLayout.Y_AXIS));
        int cpt = 1;
        for (Fourmi fourmi : fourmis) {
            FourmiButton fourmiButton = new FourmiButton(fourmi, cpt);
            pnlFourmis.add(fourmiButton);
            cpt++;
        }

        JScrollPane scrollPane = new JScrollPane(pnlFourmis);
        add(scrollPane, BorderLayout.CENTER);

        // Je veux un bouton pour ajouter une fourmi
        JButton btnAjouterFourmi = new JButton("Ajouter une fourmi");
        // btnAjouterFourmi.addActionListener(e -> {
        // Fourmi fourmi = new Fourmi(0,0);
        // FourmiButton fourmiButton = new FourmiButton(fourmi, cpt);
        // pnlFourmis.add(fourmiButton);
        // cpt++;
        // revalidate();
        // repaint();
        // });
        // Là on va utiliser la méthode ajouterFourmi de model.Fourmi

        add(btnAjouterFourmi, BorderLayout.SOUTH);
    }

    // Les updates à utiliser dans le controler pour mettre à jour les valeurs de
    // manière dynamique
    public void updateValeurNutritiveRessources(int valeurNutritiveRessources) {
        lblValeurNutritiveRessources.setText("Valeur nutritive des ressources : " + valeurNutritiveRessources);
    }

    public void updateFourmis(List<Fourmi> fourmis) {
        pnlFourmis.removeAll();
        int cpt = 1;
        for (Fourmi fourmi : fourmis) {
            FourmiButton fourmiButton = new FourmiButton(fourmi, cpt);
            pnlFourmis.add(fourmiButton);
            cpt++;
        }
        revalidate();
        repaint();
    }
}
