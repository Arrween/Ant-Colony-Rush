package model;

import java.util.ArrayList;

public abstract class ObjetFixe {
    private int x;
    private int y;
    protected ArrayList<Fourmi> fourmis;
    public static final int RAYON_HITBOX = 20;

    public ObjetFixe(int x, int y, int nbFourmis) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < nbFourmis; i++) {
            fourmis.add(new Fourmi(x, y));
        }
    }


    public boolean hitBoxCliquee(int x, int y) {
        return (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y) <= RAYON_HITBOX * RAYON_HITBOX;
    }

    public abstract void majEnergieFourmis();
}
