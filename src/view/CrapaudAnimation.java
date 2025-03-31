package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CrapaudAnimation {
    private final BufferedImage[] frames = new BufferedImage[4];
    private int index = 0;

    public CrapaudAnimation() {
        try {

            BufferedImage sheet = ImageIO.read(getClass().getResource("/resources/Frog/Idle.png"));
            int cols = 2, rows = 2;
            int frameW = sheet.getWidth() / cols;
            int frameH = sheet.getHeight() / rows;

            for (int i = 0; i < cols; i++) {
                frames[i] = sheet.getSubimage(i * frameW, 0, frameW, frameH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFrame() {
        index = (index + 1) % frames.length;
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public int getWidth() {
        return frames[index].getWidth();
    }

    public int getHeight() {
        return frames[index].getHeight();
    }
}
