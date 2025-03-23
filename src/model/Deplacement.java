package model;

public class Deplacement {
    private Terrain t;
    private Fourmi f;
    private int currentX, currentY, destX, destY, depX, depY;
    private ObjetFixe dest;
    private boolean isDone = false;

    private static int compteur = 0;

    private int numero;

    public Deplacement(Terrain t, Fourmi f, ObjetFixe dest, ObjetFixe depart) {
        this.t = t;
        this.f = f;
        this.currentX = f.getX();
        this.currentY = f.getY();
        this.dest = dest;
        this.destX = dest.getX();
        this.destY = dest.getY();
        numero = ++compteur;
        depX = depart.getX();
        depY = depart.getY();
    }

    public void decrEnergieFourmi() {
        f.decrEnergie();
    }

    public int getId() {
        return numero;
    }

    public int getX() {
        return currentX;
    }

    public int getY() {
        return currentY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public int getDepX() {
        return depX;
    }

    public int getDepY() {
        return depY;
    }

    public void avancer() {
        System.out.println("Fourmi " + f.getId() + " en déplacement");
        //algo de déplacmenent////
        if (currentX < destX) {
            currentX+=10;
            if (currentX > destX) {
                currentX = destX;
            }
        } else if (currentX > destX) {
            currentX-=10;
            if (currentX < destX) {
                currentX = destX;
            }
        } else if (currentY < destY) {
            currentY+=10;
            if (currentY > destY) {
                currentY = destY;
            }
        } else if (currentY > destY) {
            currentY-=10;
            if (currentY < destY) {
                currentY = destY;
            }
        }

        //si déplacment fini ajouter la fourmi à l'objet destination et supprimer le déplacement de la liste de déplacements du terrain
        if (!isDone && currentX == destX && currentY == destY) {
            dest.ajouterFourmi(f);
            isDone = true;
        }
    }

    public boolean isDone() {
        return isDone;
    }
}
