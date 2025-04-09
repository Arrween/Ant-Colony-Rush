package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Gère une petite animation d'idle pour le crapaud :
 *  - 2 colonnes, 2 lignes => 4 frames.
 */
public class CrapaudIdleAnimation {

    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrame;
    private int frameWidth, frameHeight;

    public CrapaudIdleAnimation() {
        loadSpriteSheet();
        extractFrames();
        currentFrame = 0;
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/resources/Frog/idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Découpe la spriteSheet en 4 frames (2x2).
     */
    private void extractFrames() {
        frameWidth = spriteSheet.getWidth() / 2;  // 2 colonnes
        frameHeight = spriteSheet.getHeight() / 2; // 2 lignes

        frames = new BufferedImage[4];
        int index = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                int x = col * frameWidth;
                int y = row * frameHeight;
                frames[index] = spriteSheet.getSubimage(x, y, frameWidth, frameHeight);
                index++;
            }
        }
    }

    /**
     * Fait avancer l'animation d'une frame.
     */
    public void updateFrame() {
        currentFrame = (currentFrame + 1) % frames.length;
    }

    /**
     * @return L'image correspondant à la frame courante.
     */
    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return frameHeight;
    }
}
