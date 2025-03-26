// package tests;

// import java.awt.BorderLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.*;
// import model.Crapaud;
// import model.Terrain;
// import view.AffichageJeu;
// import view.CrapaudAnimation;

// public class TestCrapaud {
// public static void main(String[] args) {
// // Création du terrain et d'un crapaud
// Terrain terrain = new Terrain();
// int startX = Terrain.RANDOM.nextInt(Terrain.LARGEUR);
// int startY = Terrain.RANDOM.nextInt(Terrain.HAUTEUR);
// int visionRange = 100;
// Crapaud crapaud = new Crapaud(startX, startY, visionRange);

// // Création de la fenêtre principale
// JFrame frame = new JFrame("Jeu avec Crapaud animé");
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.setLayout(new BorderLayout());

// // Panneau d'affichage du terrain (pour les objets fixes)
// // (Optionnel : fusionner ce panneau avec l'animation du crapaud)
// AffichageJeu affichageTerrain = new AffichageJeu(terrain, crapaud);
// frame.add(affichageTerrain, BorderLayout.CENTER);

// // Panneau d'animation pour le crapaud (dessiné par-dessus, par exemple)
// CrapaudAnimation crapaudAnim = new CrapaudAnimation(crapaud);
// // Vous pouvez par exemple utiliser un JLayeredPane pour superposer les
// // affichages
// frame.add(crapaudAnim, BorderLayout.SOUTH);

// frame.pack();
// frame.setLocationRelativeTo(null);
// frame.setVisible(true);

// // Boucle de jeu avec un Timer pour mettre à jour le crapaud et rafraîchir
// // l'affichage
// Timer gameTimer = new Timer(50, new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// crapaud.update(terrain);
// affichageTerrain.repaint();
// }
// });
// gameTimer.start();
// }
// }
