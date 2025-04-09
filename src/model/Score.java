package model;

public class Score {
    private Nid nid; // Référence au Nid
    public static final int FOURMI_AJOUT= 60;

    public Score(Nid nid) {
        this.nid = nid; // Associer le Nid
    }

    // Getter pour les Score
    public int getScore() {
        return nid.getScore();
    }

    public void augmenterScore(int vn) {
        // Incrémenter le score dans le Nid
        nid.incrScore(vn);
    }

    public void diminuerScore() {
        // Décrémenter le score dans le Nid
        nid.decrScore(FOURMI_AJOUT);
    }

    public boolean AjoutFourmiPossible() {
        return getScore() >= FOURMI_AJOUT;
    }
}