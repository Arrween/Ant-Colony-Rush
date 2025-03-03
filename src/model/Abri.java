package model;

public class Abri extends ObjetFixe {
    private int capacite;
    private int x;
    private int y;

    public Abri(int capacite, int x, int y) {
        super(x, y);
        this.capacite = capacite;
    }
}
