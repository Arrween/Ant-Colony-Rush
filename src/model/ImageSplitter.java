package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSplitter {

    public static BufferedImage[][] SeedlingImage() throws IOException {
        int rows = 7;
        int cols = 17;

        BufferedImage[][] images = new BufferedImage[rows][cols];

        BufferedImage sourceImage = ImageIO
                .read(ImageSplitter.class.getResourceAsStream("/resources/Resources/Seedling.png"));

        // Calculer la largeur et la hauteur de chaque cellule
        int cellWidth = sourceImage.getWidth() / cols;
        int cellHeight = sourceImage.getHeight() / rows;

        // Découper l'image en sous-images
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                images[row][col] = sourceImage.getSubimage(

                        col * cellWidth,
                        row * cellHeight,
                        cellWidth,
                        cellHeight);
            }
        }

        return images;
    }

    public static BufferedImage[] getSixImages(int col) throws IOException {
        BufferedImage[][] imagesMatrix = SeedlingImage();
        BufferedImage[] sixImages = new BufferedImage[6];

        for (int i = 1; i < 6; i++) {

            sixImages[i - 1] = imagesMatrix[i][col];

            sixImages[i - 1] = imagesMatrix[i][col];

        }
        sixImages[5] = imagesMatrix[0][col];

        return sixImages;
    }
}
