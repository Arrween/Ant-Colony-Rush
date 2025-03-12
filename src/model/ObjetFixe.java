package model;

import java.util.ArrayList;
import java.awt.*;

public abstract class ObjetFixe {
    private int x;
    private int y;
    protected ArrayList<Fourmi> fourmis;
    public static final int HALF_SIZE = 20;

    public ObjetFixe(int x, int y, int nbFourmis) {
        this.x = x;
        this.y = y;
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

    // public boolean hitBoxCliquee(int x, int y) {
    //     return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <= RAYON_HITBOX * RAYON_HITBOX;
    // }

    public boolean hitBoxCliquee(int x, int y) {
        return (x >= this.x - HALF_SIZE && x <= this.x + HALF_SIZE) &&
               (y >= this.y - HALF_SIZE && y <= this.y + HALF_SIZE);
    }
    

    public abstract void majEnergieFourmis();
    public abstract Image getImage();
}
