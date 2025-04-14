package model;

public class Score {
    private Nid nid; // Référence au Nid
    public static final int FOURMI_AJOUT = 60;
    private int meilleurScore;
    private Sauvegarde sauvegarde;
    private int difficulte;

    public Score(Nid nid, int difficulte) {
        this.nid = nid; // Associer le Nid
        this.difficulte = difficulte; // Stocker la difficulté choisie
        this.sauvegarde = new Sauvegarde("src/textes/meilleur_score.txt");
        this.meilleurScore = sauvegarde.chargerScorePourDifficulte(difficulte);
    }

    // Getter pour les Score
    public int getScore() {
        return nid.getScore();
    }

    public int getMeilleurScore() {
        verifierMeilleurScore();
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
        int score = getScore();
        if ( score > meilleurScore) {
            System.out.println("Nouveau record atteint : " + getScore());
            meilleurScore = score;
            sauvegarde.sauvegarderScorePourDifficulte(difficulte,score);
        }
    }

}