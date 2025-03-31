package model;

public class Score {
    private int Score  = 5 ;

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
}