package view;

import controller.DestinationSelectionnee;
import controller.FourmiButton;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Fourmi;
import model.Nid;

public class PanneauDeControle extends JPanel {

    private Nid nid;
    private JLabel lblScore;
    private JPanel pnlFourmis;
    private JButton btnRetour;

    public PanneauDeControle(Nid nid, DestinationSelectionnee ds) {
        this.nid = nid;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // EN-TÊTE : Affichage du score et bouton "Retour"
        JPanel panneauEntete = new JPanel(new BorderLayout());
        lblScore = new JLabel("SCORE : " + nid.getScore(), SwingConstants.CENTER);
        lblScore.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauEntete.add(lblScore, BorderLayout.CENTER);

        btnRetour = new JButton("Retour");
        btnRetour.setPreferredSize(new Dimension(80, 40));
        btnRetour.addActionListener(e -> {
            Container parent = PanneauDeControle.this.getParent();
            if (parent instanceof ConteneurPanneauDeControle) {
                ((ConteneurPanneauDeControle) parent).afficherTableauDeBord();
            }
        });
        panneauEntete.add(btnRetour, BorderLayout.EAST);
        panneauEntete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panneauEntete, BorderLayout.NORTH);

        // CENTRE : Liste des fourmis
        pnlFourmis = new JPanel(new GridLayout(0, 1, 5, 5));
        for (Fourmi fourmi : nid.getFourmis()) {
            FourmiButton boutonFourmi = new FourmiButton(fourmi, ds, nid);
            boutonFourmi.setPreferredSize(new Dimension(250, 40));
            pnlFourmis.add(boutonFourmi);
        }
        JScrollPane scrollPane = new JScrollPane(pnlFourmis);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // BAS : Bouton pour ajouter une fourmi
        JButton btnAjouterFourmi = new JButton("Ajouter une fourmi");
        btnAjouterFourmi.setPreferredSize(new Dimension(280, 40));
        btnAjouterFourmi.addActionListener(e -> {
            nid.ajouterFourmi();
            mettreAJourFourmis(nid.getFourmis(), ds);
        });
        JPanel panneauBas = new JPanel();
        panneauBas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauBas.add(btnAjouterFourmi);
        add(panneauBas, BorderLayout.SOUTH);
    }

    public void mettreAJourScore(int score) {
        lblScore.setText("SCORE : " + score);
    }

    public void mettreAJourFourmis(List<Fourmi> fourmis, DestinationSelectionnee ds) {
        pnlFourmis.removeAll();
        for (Fourmi fourmi : fourmis) {
            FourmiButton boutonFourmi = new FourmiButton(fourmi, ds, nid);
            pnlFourmis.add(boutonFourmi);
        }
        revalidate();
        repaint();
    }
}
