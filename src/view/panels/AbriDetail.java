package view.panels;

import controller.DestinationSelectionnee;
import java.awt.*;
import javax.swing.*;
import model.objetsFixes.Abri;
import view.components.BoutonImage;

public class AbriDetail extends JPanel {
    private JLabel lblCapacite;
    private JLabel lblMessage;
    private JLabel lblImage;
    private PanneauCartesFourmis panneauCartesFourmis;

    // Constantes de style
    private static final Color BEIGE = new Color(245, 245, 220);
    private static final Color GREEN = new Color(0, 128, 0);
    private static final Color DARK_BROWN = new Color(101, 67, 33);
    private static final Font CONTROL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CONTROL_HEADER = new Font("Segoe UI", Font.BOLD, 16);
    
    public AbriDetail(Abri abri, DestinationSelectionnee ds) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBackground(BEIGE);

        JPanel pnlEntete = new JPanel(new BorderLayout());
        pnlEntete.setBackground(BEIGE);
        JLabel lblTitre = new JLabel("Détails de l'Abri", SwingConstants.CENTER);
        lblTitre.setFont(CONTROL_HEADER);
        lblTitre.setForeground(DARK_BROWN);
        lblTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlEntete.add(lblTitre, BorderLayout.NORTH);

        lblCapacite = new JLabel("Capacité : " + abri.getNbFourmis() + " / " + abri.getCapacite(), SwingConstants.CENTER);
        lblCapacite.setFont(CONTROL_FONT);
        lblCapacite.setForeground(DARK_BROWN);
        pnlEntete.add(lblCapacite, BorderLayout.SOUTH);
        add(pnlEntete, BorderLayout.NORTH);

        JPanel pnlCentre = new JPanel();
        pnlCentre.setLayout(new BoxLayout(pnlCentre, BoxLayout.Y_AXIS));
        pnlCentre.setBackground(BEIGE);

        JPanel pnlImage = new JPanel(new GridBagLayout());
        pnlImage.setBackground(BEIGE);
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Image image = abri.getImage();
        if (image != null) {
            lblImage.setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            lblImage.setText("Aucune image disponible");
            lblImage.setForeground(DARK_BROWN);
        }
        pnlImage.add(lblImage);
        pnlCentre.add(pnlImage);

        if (abri.getFourmis().isEmpty()) {
            lblMessage = new JLabel("Aucune fourmi dans cet abri.", SwingConstants.CENTER);
            lblMessage.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            lblMessage.setForeground(DARK_BROWN);
            pnlCentre.add(lblMessage);
        } else {
            panneauCartesFourmis = new PanneauCartesFourmis(abri, ds);
            pnlCentre.add(panneauCartesFourmis);
        }
        add(pnlCentre, BorderLayout.CENTER);

        // Bouton Retour redimensionné (60x30)
        BoutonImage btnRetour = new BoutonImage("/resources/Menu/back.png", "/resources/Menu/back_hover.png");
        btnRetour.setPreferredSize(new Dimension(60, 30));
        btnRetour.addActionListener(e -> {
            Container parent = AbriDetail.this.getParent();
            if (parent instanceof ConteneurPanneauDeControle) {
                ((ConteneurPanneauDeControle) parent).afficherTableauDeBord();
            }
        });
        JPanel pnlBas = new JPanel();
        pnlBas.setBackground(BEIGE);
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
