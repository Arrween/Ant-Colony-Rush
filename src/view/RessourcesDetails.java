package view;

import java.awt.*;
import javax.swing.*;
import model.Ressource;

public class RessourcesDetails extends JPanel {
    private JLabel lblPoids;
    private JLabel lblValeurNutritive;
    private JLabel lblFourmisAssociees;
    private JButton btnRetour;

    public RessourcesDetails(Ressource ressource) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // En-tête
        JLabel lblTitre = new JLabel("Détails de la Ressource", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitre, BorderLayout.NORTH);

        // Informations sur la ressource
        JPanel pnlInfos = new JPanel();
        pnlInfos.setLayout(new BoxLayout(pnlInfos, BoxLayout.Y_AXIS));
        pnlInfos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblPoids = new JLabel("Poids : " + ressource.getPoids());
        lblValeurNutritive = new JLabel("Valeur nutritive : " + ressource.getValeurNutritive());
        lblFourmisAssociees = new JLabel("Fourmis associées : " + ressource.getNbrFourmis());

        pnlInfos.add(lblPoids);
        pnlInfos.add(lblValeurNutritive);
        pnlInfos.add(lblFourmisAssociees);

        add(pnlInfos, BorderLayout.CENTER);

        // Bouton Retour
        btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            Container parent = RessourcesDetails.this.getParent();
            if (parent instanceof ConteneurPanneauDeControle) {
                ((ConteneurPanneauDeControle) parent).afficherTableauDeBord();
            }
        });

        JPanel pnlBas = new JPanel();
        pnlBas.add(btnRetour);
        add(pnlBas, BorderLayout.SOUTH);
    }
}