package model;

public class Ressource extends ObjetFixe {
    private int poids;
    private int valeur_nutritive;
    private int nbr_fourmis;
    private int x;
    private int y;

    public Ressource(int poids, int valeur_nutritive, int nbr_fourmis, int x, int y) {
        super(x, y);
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
