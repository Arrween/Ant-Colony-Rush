package view;

import controler.DestSelector;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Fourmi;
import model.Nid;

public class PanneauDeControle extends JPanel {

    private Nid nid;
    private JLabel lblScore; // Label pour afficher la valeur nutritive des ressources
    private JPanel pnlFourmis; // Panel pour afficher les fourmis

    public PanneauDeControle(Nid nid, DestSelector ds) {
        this.nid = nid;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500)); // Taille de la fenêtre

        // NORTH : Affichage de la valeur nutritive des ressources + bouton fermeture

        JPanel headerPanel = new JPanel(new BorderLayout());
        lblScore = new JLabel("SCORE : " + nid.getScore(), SwingConstants.CENTER);
        lblScore.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(lblScore, BorderLayout.CENTER);

        // Bouton de fermeture (croix)
        JButton btnClose = new JButton("X");
        btnClose.setPreferredSize(new Dimension(40, 40));
        btnClose.setMargin(new Insets(0, 0, 0, 0));
        btnClose.addActionListener(e -> {
            // Retire ce panneau de son conteneur parent
            Container parent = PanneauDeControle.this.getParent();
            if (parent != null) {
                parent.remove(PanneauDeControle.this);
                parent.revalidate();
                parent.repaint();
            }
        });
        headerPanel.add(btnClose, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerPanel, BorderLayout.NORTH);

        // CENTRE : Liste des fourmis

        pnlFourmis = new JPanel(new GridLayout(0, 1, 5, 5));
        for (int i = 0; i < nid.getFourmis().size(); i++) {
            FourmiButton fourmiButton = new FourmiButton(nid.getFourmis().get(i), ds, nid);
            fourmiButton.setPreferredSize(new Dimension(250, 40));
            pnlFourmis.add(fourmiButton);
        }

        JScrollPane scrollPane = new JScrollPane(pnlFourmis);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // SOUTH : Bouton pour ajouter une fourmi

        JButton btnAjouterFourmi = new JButton("Ajouter une fourmi");
        btnAjouterFourmi.setPreferredSize(new Dimension(280, 40));
        btnAjouterFourmi.addActionListener(e -> {
            // Appel au modèle pour ajouter une fourmi
            nid.ajouterFourmi();
            // Met à jour la liste des fourmis dans l'affichage
            updateFourmis(nid.getFourmis(), ds);
        });
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.add(btnAjouterFourmi);
        add(southPanel, BorderLayout.SOUTH);
    }

    // Mise à jour dynamique
    public void updateScore(int score) {
        lblScore.setText("SCORE : " + score);
    }

    public void updateFourmis(List<Fourmi> fourmis, DestSelector ds) {
        pnlFourmis.removeAll();
        for (Fourmi fourmi : fourmis) {
            FourmiButton fourmiButton = new FourmiButton(fourmi, ds, nid);
            pnlFourmis.add(fourmiButton);
        }
        revalidate();
        repaint();
    }
}
