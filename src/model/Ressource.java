package model;

public class Ressource extends ObjetFixe {
    private int poids;
    private int valeurNutritive;

    public Ressource(int poids, int vn, int x, int y) {
        super(x, y, 0);
        this.poids = poids;
        this.valeurNutritive = vn;
    }

    public int getPoids() {
        return poids;
    }

    public int getValeurNutritive() {
        return valeurNutritive;
    }

    public int getNbrFourmis() {
        return fourmis.size();
    }

    public void majEnergieFourmis() {
        for (Fourmi fourmi : fourmis) {
            fourmi.decrEnergie();
        }
    }
}
