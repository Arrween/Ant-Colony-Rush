package view.panels;

import controller.DestinationSelectionnee;
import java.awt.*;
import javax.swing.*;
import model.objetsFixes.Abri;

public class AbriDetail extends JPanel {
    private JLabel lblCapacite; // Capacité de l'abri
    private JLabel lblMessage; // Message si l'abri est vide
    private JLabel lblImage; // Label pour afficher l'image de l'abri
    private PanneauCartesFourmis panneauCartesFourmis; // Panneau pour afficher les cartes des fourmis

    public AbriDetail(Abri abri, DestinationSelectionnee ds) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // En-tête : Titre et capacité
        JPanel pnlEntete = new JPanel(new BorderLayout());
        JLabel lblTitre = new JLabel("Détails de l'Abri", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlEntete.add(lblTitre, BorderLayout.NORTH);

        lblCapacite = new JLabel("Capacité : " + abri.getNbFourmis() + " / " + abri.getCapacite(),
                SwingConstants.CENTER);
        lblCapacite.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlEntete.add(lblCapacite, BorderLayout.SOUTH);
        add(pnlEntete, BorderLayout.NORTH);

        // Centre : Image de l'abri et panneau des cartes
        JPanel pnlCentre = new JPanel();
        pnlCentre.setLayout(new BoxLayout(pnlCentre, BoxLayout.Y_AXIS));

        // Image de l'abri
        JPanel pnlImage = new JPanel(new GridBagLayout()); // Utiliser GridBagLayout pour centrer l'image
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Image image = abri.getImage();
        if (image != null) {
            lblImage.setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH))); // Redimensionner
                                                                                                    // l'image
        } else {
            lblImage.setText("Aucune image disponible");
        }
        pnlImage.add(lblImage); // Ajouter l'image au panneau centré
        pnlCentre.add(pnlImage);

        // Panneau des cartes ou message si vide
        if (abri.getFourmis().isEmpty()) {
            lblMessage = new JLabel("Aucune fourmi dans cet abri.", SwingConstants.CENTER);
            lblMessage.setFont(new Font("Arial", Font.ITALIC, 12));
            lblMessage.setForeground(Color.RED);
            pnlCentre.add(lblMessage);
        } else {
            panneauCartesFourmis = new PanneauCartesFourmis(abri, ds);
            pnlCentre.add(panneauCartesFourmis);
        }

        add(pnlCentre, BorderLayout.CENTER);

        // Bas : Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            Container parent = AbriDetail.this.getParent();
            if (parent instanceof ConteneurPanneauDeControle) {
                ((ConteneurPanneauDeControle) parent).afficherTableauDeBord();
            }
        });
        JPanel pnlBas = new JPanel();
        pnlBas.add(btnRetour);
        add(pnlBas, BorderLayout.SOUTH);
    }

    public void mettreAJourFourmis(Abri abri) {
        if (panneauCartesFourmis != null) {
            panneauCartesFourmis.mettreAJourCartes();
        }
        lblCapacite.setText("Capacité : " + abri.getNbFourmis() + " / " + abri.getCapacite());
        revalidate();
        repaint();
    }
}