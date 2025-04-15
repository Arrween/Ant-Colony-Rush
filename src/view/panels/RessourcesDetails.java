package view.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.objetsFixes.Ressource;
import view.components.BoutonImage;

public class RessourcesDetails extends JPanel {
    private JLabel lblPoids;
    private JLabel lblValeurNutritive;
    private JLabel lblFourmisAssociees;
    private JLabel lblImage;
    private BoutonImage btnRetour;
    private JPanel pnlFourmisIcons; // Nouveau champ pour stocker le panneau d'icônes
    private Ressource ressource; // Référence à la ressource

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 16);

    public RessourcesDetails(Ressource ressource) {
        this.ressource = ressource;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBackground(BEIGE);

        JLabel lblTitre = new JLabel("Détail de la ressource", SwingConstants.CENTER);
        lblTitre.setFont(CONTROL_HEADER);
        lblTitre.setForeground(DARK_BROWN);
        lblTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(lblTitre, BorderLayout.NORTH);

        JPanel pnlCentre = new JPanel();
        pnlCentre.setLayout(new BoxLayout(pnlCentre, BoxLayout.Y_AXIS));
        pnlCentre.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlCentre.setBackground(BEIGE);

        lblPoids = new JLabel("Poids : " + ressource.getPoids());
        lblPoids.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPoids.setFont(CONTROL_FONT);
        lblPoids.setForeground(DARK_BROWN);
        pnlCentre.add(lblPoids);
        pnlCentre.add(Box.createVerticalStrut(5));

        lblValeurNutritive = new JLabel("Valeur nutritive : " + ressource.getValeurNutritive());
        lblValeurNutritive.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValeurNutritive.setFont(CONTROL_FONT);
        lblValeurNutritive.setForeground(DARK_BROWN);
        pnlCentre.add(lblValeurNutritive);
        pnlCentre.add(Box.createVerticalStrut(5));

        lblFourmisAssociees = new JLabel("Fourmis associées : " + ressource.getNbrFourmis());
        lblFourmisAssociees.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFourmisAssociees.setFont(CONTROL_FONT);
        lblFourmisAssociees.setForeground(DARK_BROWN);
        pnlCentre.add(lblFourmisAssociees);
        pnlCentre.add(Box.createVerticalStrut(20));

        // Initialiser le panneau d'icônes de fourmis
        pnlFourmisIcons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlFourmisIcons.setBackground(BEIGE);
        pnlFourmisIcons.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCentre.add(pnlFourmisIcons);
        pnlCentre.add(Box.createVerticalStrut(20));

        // Première mise à jour des icônes
        mettreAJourFourmis();

        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBorder(new EmptyBorder(10, 10, 10, 10));
        Image image = ressource.getImage();
        if (image != null) {
            lblImage.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else {
            lblImage.setText("Aucune image disponible");
            lblImage.setForeground(DARK_BROWN);
        }
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCentre.add(lblImage);

        add(pnlCentre, BorderLayout.CENTER);

        btnRetour = new BoutonImage("/resources/Menu/back.png", "/resources/Menu/back_hover.png");
        btnRetour.addActionListener(e -> {
            Container parent = RessourcesDetails.this.getParent();
            while (parent != null && !(parent instanceof ConteneurPanneauDeControle)) {
                parent = parent.getParent();
            }
            if (parent instanceof ConteneurPanneauDeControle conteneur) {
                conteneur.afficherTableauDeBord();
            }
        });
        JPanel pnlBas = new JPanel();
        pnlBas.setBackground(BEIGE);
        pnlBas.add(btnRetour);
        add(pnlBas, BorderLayout.SOUTH);
    }

    // Nouvelle méthode pour mettre à jour l'affichage des fourmis
    public void mettreAJourFourmis() {
        // Mettre à jour le label textuel
        lblFourmisAssociees.setText("Fourmis associées : " + ressource.getNbrFourmis());

        // Mettre à jour les icônes
        pnlFourmisIcons.removeAll();

        // Recharger l'image de fourmi
        ImageIcon antIcon = new ImageIcon(getClass().getResource("/resources/Ant/ant_lonely.png"));
        Image scaledAnt = antIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledAntIcon = new ImageIcon(scaledAnt);

        // Ajouter les nouvelles icônes
        int nbFourmis = ressource.getNbrFourmis();
        for (int i = 0; i < nbFourmis; i++) {
            JLabel antLabel = new JLabel(scaledAntIcon);
            pnlFourmisIcons.add(antLabel);
        }

        // Rafraîchir l'affichage
        pnlFourmisIcons.revalidate();
        pnlFourmisIcons.repaint();
    }
}
