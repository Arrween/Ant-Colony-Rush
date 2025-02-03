package control;
import view.Affichage;

public class Redessine extends Thread {
    
    // Délai (en millisecondes) entre deux redessins
    private static final int DELAY = 50;

    // On stocke l’Affichage sur lequel on veut agir
    private Affichage monAffichage;

    // Constructeur qui reçoit l’affichage
    public Redessine(Affichage affichage) {
        this.monAffichage = affichage;
    }

    @Override
    public void run() {
        while(true) {
            // Demande à Swing de redessiner le composant
            monAffichage.repaint();

            // Petite pause
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
