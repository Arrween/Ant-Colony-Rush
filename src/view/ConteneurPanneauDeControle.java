package view;

import java.awt.*;
import javax.swing.*;
import model.Score;

public class ConteneurPanneauDeControle extends JPanel {
    public static final String TABLEAU_DE_BORD = "TableauDeBord";
    public static final String DETAIL = "Detail";

    private CardLayout gestionnaireCartes;
    private PanneauDeTableauDeBord panneauTableauDeBord;
    private JPanel panneauDetail; // panneau de détail actuellement affiché

    public ConteneurPanneauDeControle(Score score) {
        gestionnaireCartes = new CardLayout();
        setLayout(gestionnaireCartes);
        panneauTableauDeBord = new PanneauDeTableauDeBord(score);
        add(panneauTableauDeBord, TABLEAU_DE_BORD);
    }

    public PanneauDeTableauDeBord getPanneauTableauDeBord() {
        return panneauTableauDeBord;
    }

    /**
     * Affiche un panneau de détail (par exemple, le panneau de contrôle d'un nid).
     */
    public void afficherPanneauDetail(JPanel detail) {
        if (panneauDetail != null) {
            remove(panneauDetail);
        }
        panneauDetail = detail;
        add(panneauDetail, DETAIL);
        gestionnaireCartes.show(this, DETAIL);
        revalidate();
        repaint();
    }

    /**
     * Revient à l'affichage par défaut (tableau de bord).
     */
    public void afficherTableauDeBord() {
        gestionnaireCartes.show(this, TABLEAU_DE_BORD);
    }
}
