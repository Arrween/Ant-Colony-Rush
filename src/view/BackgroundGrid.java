package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

public class BackgroundGrid {
    private static final int TILE_SIZE = 30;         // Taille de chaque tuile en pixels
    private static final int NB_TILES = 12;          // Nombre total de tuiles différentes
    private Image[] tiles;                          // Tableau des images (les 12 tuiles)
    private int[][] grid;                           // Tableau 2D d'indices vers tiles[]
    private int nbCols, nbRows;                     // Nombre de colonnes et lignes pour couvrir la surface
    private Random random;

    public BackgroundGrid(int totalWidth, int totalHeight) {
        random = new Random();
        
        // Calcul du nombre de colonnes et de lignes selon la taille du terrain
        nbCols = (int) Math.ceil(totalWidth / (double) TILE_SIZE);
        nbRows = (int) Math.ceil(totalHeight / (double) TILE_SIZE);

        // Charger les 12 tuiles
        loadTiles();

        // Créer la grille aléatoire
        generateRandomGrid();
    }

    private void loadTiles() {
        tiles = new Image[NB_TILES];
        
        try {
            // Exemple : si vous avez 12 images "tile0.png", "tile1.png" ... "tile11.png" 
            // dans votre répertoire /resources/Tiles/
            for (int i = 0; i < NB_TILES; i++) {
                // Chemin et nom de fichier adaptés à votre structure
                String filename = "/resources/Grass/" + i + ".png";
                BufferedImage img = ImageIO.read(getClass().getResource(filename));
                tiles[i] = img; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur éventuellement
        }
    }

    private void generateRandomGrid() {
        grid = new int[nbRows][nbCols];
        for (int row = 0; row < nbRows; row++) {
            for (int col = 0; col < nbCols; col++) {
                // Choisit au hasard l'une des 12 tuiles
                int tileIndex = random.nextInt(NB_TILES);
                grid[row][col] = tileIndex;
            }
        }
    }

    public void draw(Graphics g) {
        // Parcourt la grille et dessine chaque tuile à la bonne position
        for (int row = 0; row < nbRows; row++) {
            for (int col = 0; col < nbCols; col++) {
                int tileIndex = grid[row][col];
                Image tile = tiles[tileIndex];
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                g.drawImage(tile, x, y, null);
            }
        }
    }
}
