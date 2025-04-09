package model;

public class Score {
    private int Score = 10;

    // Getter pour les Score
    public int getScore() {
        return Score;
    }

    public void augmenterScore(int vn) {
        this.Score += vn;
    }

    public void diminuerScore() {
        this.Score -= 60;
    }

    public boolean AjoutFourmiPossible() {
        return Score >= 60;
    }
}