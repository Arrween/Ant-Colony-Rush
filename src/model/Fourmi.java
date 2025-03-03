package model;

public class Fourmi {
    private int x;
    private int y;
    private int energie;

    public Fourmi(int x, int y, int energie) {
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
}
