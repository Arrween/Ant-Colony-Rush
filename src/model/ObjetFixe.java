package model;

import java.awt.*;
import java.util.ArrayList;

public abstract class ObjetFixe {
    private int x;
    private int y;
    protected ArrayList<Fourmi> fourmis;
    public static final int HALF_SIZE = 20;
    public static final int RAYON_HITBOX = 100;
    public static final int RAYON_HITBOX_R = 40;

    public ObjetFixe(int x, int y, int nbFourmis) {
        this.x = x;
        this.y = y;
        this.fourmis = new ArrayList<Fourmi>();
        for (int i = 0; i < nbFourmis; i++) {
            fourmis.add(new Fourmi(x, y));
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hitBoxAbri(int x, int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <=
            RAYON_HITBOX * RAYON_HITBOX;
    }
    public boolean hitBoxRess(int x, int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <=
            RAYON_HITBOX_R * RAYON_HITBOX_R;
    }

    public boolean hitBoxCliquee(int x, int y) {
        return (x >= this.x - HALF_SIZE && x <= this.x + HALF_SIZE) &&
                (y >= this.y - HALF_SIZE && y <= this.y + HALF_SIZE);
    }

    public abstract void majEnergieFourmis();

    public abstract Image getImage();
}
