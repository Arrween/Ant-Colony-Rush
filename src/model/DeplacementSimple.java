package model;

public class DeplacementSimple extends Deplacement {
    private Fourmi f;

    public DeplacementSimple(Terrain t, Fourmi fourmi, ObjetFixe dest, ObjetFixe depart) {
        super(t, dest, depart);
        this.f = fourmi;
    }

    public void decrEnergieFourmi() {
        f.decrEnergie();
    }

    public Fourmi getFourmi() {
        return f;
    }

    @Override
    public void avancer() {
        // System.out.println("Fourmi " + f.getId() + " en déplacement");
        // algo de déplacmenent////

        // Mise à jour de la vitesse, position, direction et animation de la fourmi
        updatePosition();
        updateDirection();
        animationFourmi.updateFrame();

        // si déplacment fini ajouter la fourmi à l'objet destination et marquer comme
        // fini
        if (!isDone && currentX == destX && currentY == destY) {
            dest.ajouterFourmi(f);
            isDone = true;
        }
    }

    public void eliminerFourmisMortes() {
        if (f.isDead()) {
            t.expeditions.removeIf(d -> d.getId() == this.getId());
        }
    }
}