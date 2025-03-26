package model;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Crapaud {
    private int x;
    private int y;
    private int visionRange;
    private int dx;
    private int dy;
    private Image imageCrapaud;
    private static final Random random = new Random();

    public Crapaud(int startX, int startY, int visionRange) {
        this.x = startX;
        this.y = startY;
        this.visionRange = visionRange;
        // randomizeDirection();
        imageCrapaud = new ImageIcon(getClass().getResource("/ressources/Frog/Idle.png")).getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVisionRange() {
        return visionRange;
    }

    public Image getImage() {
        return imageCrapaud;
    }

}
