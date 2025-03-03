package model;

public class Ressource {
    private int poids;
    private int valeur_nutritive;

    public Ressource(int poids, int valeur_nutritive) {
        this.poids = poids;
        this.valeur_nutritive = valeur_nutritive;
    }

    public int getPoids() {
        return poids;
    }

    public int getValeurNutritive() {
        return valeur_nutritive;
    }
}
