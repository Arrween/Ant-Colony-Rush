package model;

public class Score {
    private Nid nid; // Référence au Nid
    public static final int FOURMI_AJOUT = 60;
    private int meilleurScore;
    private Sauvegarde sauvegarde;

    public Score(Nid nid) {
        this.nid = nid; // Associer le Nid
        this.sauvegarde = new Sauvegarde("meilleur_score.txt");
        this.meilleurScore = sauvegarde.chargerMeilleurScore();
    }

    // Getter pour les Score
    public int getScore() {
        return nid.getScore();
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public void augmenterScore(int vn) {
        // Incrémenter le score dans le Nid
        nid.incrScore(vn);
        verifierMeilleurScore(); // Vérifier si le score actuel dépasse le meilleur score
    }

    public void diminuerScore() {
        // Décrémenter le score dans le Nid
        nid.decrScore(FOURMI_AJOUT);
    }

    public boolean AjoutFourmiPossible() {
        return getScore() >= FOURMI_AJOUT;
    }

    private void verifierMeilleurScore() {
        // Vérifier si le score actuel est supérieur au meilleur score
        if (getScore() > meilleurScore) {
            System.out.println("Nouveau record atteint : " + getScore());
            meilleurScore = getScore();
            sauvegarde.sauvegarderMeilleurScore(meilleurScore);
        }
    }

}