package model;

public class Nid extends ObjetFixe {
    private int cptRessources;
    private Fourmi[] fourmis;
    private int x;
    private int y;

    public Nid(int cptRessources, Fourmi[] fourmis, int x, int y) {
        super(x, y);
        this.cptRessources = cptRessources;
        this.fourmis = fourmis;
    }
}
