package model;

public enum Difficulte {
    FACILE(15, 10, 30),
    MOYEN(10, 5, 20),
    DIFFICILE(5, 2, 10);

    private final int nbFourmisInitial;
    private final int nbAbris;
    private final int nbRessources;

    Difficulte(int nbFourmisInitial, int nbAbris, int nbRessources) {
        this.nbFourmisInitial = nbFourmisInitial;
        this.nbAbris = nbAbris;
        this.nbRessources = nbRessources;
    }
    

    public int getNbFourmisInitial() {
        return nbFourmisInitial;
    }

    public int getNbAbris() {
        return nbAbris;
    }

    public int getNbRessources() {
        return nbRessources;
    }
}