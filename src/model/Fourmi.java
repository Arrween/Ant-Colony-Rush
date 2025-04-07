package model;

public class Fourmi {
    public final static int MAX_ENERGIE = 100;

    private static int compteur = 0;
    private int numero;

    private int x;
    private int y;
    private int energie;

    public Fourmi(int x, int y) {
        numero = ++compteur;
        this.x = x;
        this.y = y;
        this.energie = MAX_ENERGIE;
    }

    public Fourmi(int x, int y, int energie) {
        numero = ++compteur;
        this.x = x;
        this.y = y;
        this.energie = energie;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergie() {
        return energie;
    }

    public void incrEnergie() {
        if (energie < MAX_ENERGIE)
            energie++;
    }

    public void decrEnergie() {
        if (energie > 0)
            energie--;
    }

    public int getId() {
        return numero;
    }

    public boolean isDead() {
        return energie <= 0;
    }
}
