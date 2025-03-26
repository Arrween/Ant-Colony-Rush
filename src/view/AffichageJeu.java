// package view;

// import java.awt.*;
// import javax.swing.*;
// import model.Crapaud;
// import model.ObjetFixe;
// import model.Terrain;

// public class AffichageJeu extends JPanel {
// public static final Dimension PANELSIZE = new Dimension(Terrain.LARGEUR,
// Terrain.HAUTEUR);
// private Terrain terrain;
// private Crapaud crapaud;

// public AffichageJeu(Terrain terrain, Crapaud crapaud) {
// this.terrain = terrain;
// this.crapaud = crapaud;
// setPreferredSize(PANELSIZE);
// }

// @Override
// protected void paintComponent(Graphics g) {
// super.paintComponent(g);
// // Afficher tous les objets fixes (nid, abris, ressources)
// for (ObjetFixe obj : Terrain.getObjetsFixes()) {
// Image img = obj.getImage();
// if (img != null) {
// g.drawImage(img, obj.getX() - model.ObjetFixe.HALF_SIZE, obj.getY() -
// model.ObjetFixe.HALF_SIZE,
// 2 * model.ObjetFixe.HALF_SIZE, 2 * model.ObjetFixe.HALF_SIZE, this);
// }
// }
// // Afficher le crapaud
// if (crapaud != null && crapaud.getImage() != null) {
// int size = 40; // Taille d'affichage du crapaud
// g.drawImage(crapaud.getImage(), crapaud.getX() - size / 2, crapaud.getY() -
// size / 2, size, size, this);
// // Facultatif : dessiner le champ de vision en rouge
// g.setColor(Color.RED);
// g.drawOval(crapaud.getX() - crapaud.getVisionRange(), crapaud.getY() -
// crapaud.getVisionRange(),
// 2 * crapaud.getVisionRange(), 2 * crapaud.getVisionRange());
// }
// }
// }
