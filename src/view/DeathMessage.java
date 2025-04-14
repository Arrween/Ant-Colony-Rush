package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class DeathMessage {
    private final int x;
    private final int y;
    private final long startTime;
    private final static int DURATION = 5000; // 5 secondes
    private float opacity;

    public DeathMessage(int x, int y) {
        this.x = x;
        this.y = y;
        this.startTime = System.currentTimeMillis();
        this.opacity = 1.0f;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    public void updateOpacity() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        opacity = 1.0f - (float) elapsedTime / DURATION;
        if (opacity < 0)
            opacity = 0;
    }

    public void draw(Graphics2D g2d) {
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(alphaComposite);
        g2d.setColor(Color.RED);
        g2d.drawString("† Fourmi morte †", x - 30, y - 20);
    }
}