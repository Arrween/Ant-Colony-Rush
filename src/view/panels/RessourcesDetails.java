package view.panels;

import java.awt.*;
import javax.swing.*;
import model.objetsFixes.Ressource;

public class RessourcesDetails extends JPanel {
    private JLabel lblPoids;
    private JLabel lblValeurNutritive;
    private JLabel lblFourmisAssociees;
    private JLabel lblImage; // Label pour afficher l'image
    private JButton btnRetour;

    public RessourcesDetails(Ressource ressource) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // En-tête : Titre
        JLabel lblTitre = new JLabel("Détail de la ressource", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitre, BorderLayout.NORTH);

        // Centre : Informations et image
        JPanel pnlCentre = new JPanel();
        pnlCentre.setLayout(new BoxLayout(pnlCentre, BoxLayout.Y_AXIS));
        pnlCentre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Informations sur la ressource
        lblPoids = new JLabel("Poids : " + ressource.getPoids());
        lblValeurNutritive = new JLabel("Valeur nutritive : " + ressource.getValeurNutritive());
        lblFourmisAssociees = new JLabel("Fourmis associées : " + ressource.getNbrFourmis());

        lblPoids.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValeurNutritive.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFourmisAssociees.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlCentre.add(lblPoids);
        pnlCentre.add(Box.createVerticalStrut(5)); // Espacement vertical
        pnlCentre.add(lblValeurNutritive);
        pnlCentre.add(Box.createVerticalStrut(5)); // Espacement vertical
        pnlCentre.add(lblFourmisAssociees);
        pnlCentre.add(Box.createVerticalStrut(20)); // Espacement avant l'image

        // Image de la ressource
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Image image = ressource.getImage();
        if (image != null) {
            lblImage.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Redimensionner
                                                                                                    // l'image
        } else {
            lblImage.setText("Aucune image disponible");
        }
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCentre.add(lblImage);

        add(pnlCentre, BorderLayout.CENTER);

        // Bas : Bouton Retour
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