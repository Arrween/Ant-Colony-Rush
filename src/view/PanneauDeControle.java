package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Fourmi;

public class PanneauDeControle extends JPanel {

    private JLabel lblValeurNutritiveRessources; // Label pour afficher la valeur nutritive des ressources
    private JPanel pnlFourmis; // Panel pour afficher les fourmis

    public PanneauDeControle(int valeurNutritiveRessources, List<Fourmi> fourmis) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500)); // Taille de la fenêtre

        lblValeurNutritiveRessources = new JLabel("SCORE : " + valeurNutritiveRessources,
                SwingConstants.CENTER);
        lblValeurNutritiveRessources.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblValeurNutritiveRessources, BorderLayout.NORTH);

        pnlFourmis = new JPanel(new GridLayout(0, 1, 5, 5));
        for (int i = 0; i < fourmis.size(); i++) {
            FourmiButton fourmiButton = new FourmiButton(fourmis.get(i), i + 1);
            fourmiButton.setPreferredSize(new Dimension(250, 40));
            pnlFourmis.add(fourmiButton);
        }

        JScrollPane scrollPane = new JScrollPane(pnlFourmis);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        JButton btnAjouterFourmi = new JButton("Ajouter une fourmi");
        btnAjouterFourmi.setPreferredSize(new Dimension(280, 40));
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(btnAjouterFourmi);
        add(southPanel, BorderLayout.SOUTH);
    }

    // Mise à jour dynamique
    public void updateValeurNutritiveRessources(int valeurNutritiveRessources) {
        lblValeurNutritiveRessources.setText("Valeur nutritive des ressources : " + valeurNutritiveRessources);
    }

    public void updateFourmis(List<Fourmi> fourmis) {
        pnlFourmis.removeAll();
        for (int i = 0; i < fourmis.size(); i++) {
            FourmiButton fourmiButton = new FourmiButton(fourmis.get(i), i + 1);
            fourmiButton.setPreferredSize(new Dimension(250, 40));
            pnlFourmis.add(fourmiButton);
        }
        revalidate();
        repaint();
    }
}
