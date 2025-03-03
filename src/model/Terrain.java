package model;

public class Terrain {
    private static final int TAILLE_PIXEL = 20;
    public static final int NB_PIXELS_LAR = 60;
    public static final int NB_PIXELS_HAU = 40;
    public static final int LARGEUR = TAILLE_PIXEL * NB_PIXELS_LAR;
    public static final int HAUTEUR = TAILLE_PIXEL * NB_PIXELS_HAU;

    ObjetFixe[][] grid;

    public Terrain() {
        grid = new ObjetFixe[NB_PIXELS_HAU][NB_PIXELS_LAR];
    }

    public ObjetFixe getObjetFixe(int x, int y) {
        return grid[y / TAILLE_PIXEL][x / TAILLE_PIXEL];
    }
}