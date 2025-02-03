package model ;

public class Position {

    // Attribut entier hauteur (privé), initialisé à 0
    private int hauteur = 0;
    
    // Attribut vitesse
    private int vitesse = 0;

    // Constante définissant la valeur du saut
    public static final int HAUTEUR = 10;

    // Accesseur : permet de récupérer la valeur de hauteur
    public int get() {
        return hauteur;
    }

    // Méthode jump : augmente la hauteur de HAUTEUR
    public void jump() {
        vitesse = HAUTEUR;
    }

    public void moveDown() {
        vitesse -= 1;
        hauteur += vitesse;
        if (hauteur < 0)
            hauteur = 0;
    }
}
