package model;

public class Score {
    private int Score  = 10 ;

    // Getter pour les Score
    public int getScore() {
        return Score;
    }

    public void augmenterScore(int poids) {
        this.Score += poids;
    }

    public void diminuerScore(){
        this.Score -= 3;
    }

    public boolean AjoutFourmiPossible() {
        return Score >= 3;
    }
}