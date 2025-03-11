package model;

public class Abri extends ObjetFixe {
    private int capacite;

    public Abri(int capacite, int x, int y) {
        super(x, y, 0);
        this.capacite = capacite;
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.decrEnergie();
        }
    }
}
