package model;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Nid extends ObjetFixe {
    private int cptRessources;
    private ArrayList<Fourmi> fourmis;
    private int x;
    private int y;
    private Image imageNid; // L'image représentant le nid

    public Nid(int cptRessources, Fourmi[] fourmis, int x, int y) {
        super(x, y);
        this.cptRessources = cptRessources;
        this.fourmis = new ArrayList<Fourmi>();
        this.x = x;
        this.y = y;
        
    }
}
