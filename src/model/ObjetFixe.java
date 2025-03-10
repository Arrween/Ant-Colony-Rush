package model;

public class ObjetFixe {
    private int x;
    private int y;
    public static final int RAYON_HITBOX = 20;

    public ObjetFixe(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean hitBoxCliquee(int x, int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <= RAYON_HITBOX * RAYON_HITBOX;
    }
}
