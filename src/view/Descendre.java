package view;
import model.Position;

/**
 * La classe Descendre représente un thread qui déplace une position vers le bas à intervalles réguliers.
 * Elle hérite de la classe Thread.
 */
public class Descendre extends Thread {

    private Position maPosition; // La position à déplacer

    private static final int DELAI = 100; // Délai de 100 ms entre chaque déplacement

    /**
     * Constructeur de la classe Descendre.
     * 
     * @param pos La position initiale à déplacer vers le bas.
     */
    public Descendre(Position pos) {
        this.maPosition = pos;
    }

    /**
     * Méthode exécutée lorsque le thread est démarré.
     * Déplace la position vers le bas à intervalles réguliers de 100 ms.
     */
    @Override
    public void run() {
        while(true) {
            // Décrémente la hauteur s'il y a lieu
            maPosition.moveDown();

            // Pause de 100 ms entre deux décréments
            try {
                Thread.sleep(DELAI);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
