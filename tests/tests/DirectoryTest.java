package tests;

public class DirectoryTest {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Répertoire courant : " + currentDir);
        // Afficher une image aux points (0, 0)
        Image img = new ImageIcon(currentDir + "/ressources/Not/nid.png").getImage();
        // Afficher img dans une fenêtre

    }
}
