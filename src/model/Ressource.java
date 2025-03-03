package model;

public class Ressource {
    private int poids;
    private int valeur_nutritive;
    private int nbr_fourmis;

    public Ressource(int poids, int valeur_nutritive, int nbr_fourmis) {
        this.poids = poids;
        this.valeur_nutritive = valeur_nutritive;
        this.nbr_fourmis = nbr_fourmis;
    }

    public int getPoids() {
        return poids;
    }

    public int getValeurNutritive() {
        return valeur_nutritive;
    }

    public int getNbrFourmis() {
        return nbr_fourmis;
    }
}
