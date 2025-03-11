package model;

public class Ressource extends ObjetFixe {
    private int poids;
    private int valeur_nutritive;

    public Ressource(int poids, int valeur_nutritive, int x, int y) {
        super(x, y, 0);
        this.poids = poids;
        this.valeur_nutritive = valeur_nutritive;
    }

    public int getPoids() {
        return poids;
    }

    public int getValeurNutritive() {
        return valeur_nutritive;
    }

    public int getNbrFourmis() {
        return fourmis.size();
    }

    public void majEnergieFourmis() {

    }
}
