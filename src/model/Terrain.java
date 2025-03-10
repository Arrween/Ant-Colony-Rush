package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Terrain {
    private static final int TAILLE_PIXEL = 20;
    public static final int NB_PIXELS_LAR = 60;
    public static final int NB_PIXELS_HAU = 40;
    public static final int LARGEUR = TAILLE_PIXEL * NB_PIXELS_LAR;
    public static final int HAUTEUR = TAILLE_PIXEL * NB_PIXELS_HAU;

    //ObjetFixe[][] grid;
    ArrayList<ObjetFixe> elts;

    public Terrain() {
        elts = new ArrayList<ObjetFixe>();
    }
}