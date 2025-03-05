package view;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SpriteAnimation extends JPanel implements ActionListener {
    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrame = 0;
    private Timer timer;
    
    // Nombre total de frames à utiliser dans l'animation (ici 62)
    private final int totalFrames = 62;
    // Organisation du spritesheet en grille 8x8
    private final int cols = 8;
    private final int rows = 8;
    private int frameWidth, frameHeight;

    public SpriteAnimation() {
        try {
            spriteSheet = ImageIO.read(new File("ressources/ant.png"));

            // Calculer la taille de chaque frame dans la grille
            frameWidth = spriteSheet.getWidth() / cols;
            frameHeight = spriteSheet.getHeight() / rows;
            
            // Créer un tableau pour stocker les frames de l'animation
            frames = new BufferedImage[totalFrames];
            int frameIndex = 0;
            
            // Parcourir la grille pour extraire les frames
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (frameIndex < totalFrames) { // Ne pas dépasser les 62 images
                        frames[frameIndex] = spriteSheet.getSubimage(
                            col * frameWidth, 
                            row * frameHeight, 
                            frameWidth, 
                            frameHeight
                        );
                        frameIndex++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Initialiser un Timer qui rafraîchit l'affichage
        timer = new Timer(50, this);
        timer.start();
    }

    // Redéfinition de paintComponent pour dessiner la frame courante
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frames != null && frames[currentFrame] != null) {
            g.drawImage(frames[currentFrame], 0, 0, this);
        }
    }

    // Mise à jour de la frame à chaque tic du Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % totalFrames;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Spritesheet");
        SpriteAnimation panel = new SpriteAnimation();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Vous pouvez ajuster la taille de la fenêtre en fonction de vos besoins
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
