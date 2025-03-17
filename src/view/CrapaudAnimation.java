package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Crapaud;

public class CrapaudAnimation extends JPanel implements ActionListener {
    // Délai entre les cadres (en ms)
    private static final int FRAME_DELAY = 50;
    // Enumération des états d'animation
    public enum AnimationState { IDLE, JUMP, LAND, TURN }
    
    private AnimationState currentState;
    private int frameIndex;
    private Timer timer;
    
    // Tableaux de cadres pour chaque état
    private BufferedImage[] idleFrames;
    private BufferedImage[] jumpFrames;
    private BufferedImage[] landFrames;
    private BufferedImage[] turnFrames;
    
    // Nombre de cadres pour chaque animation
    private int totalFramesIdle;
    private int totalFramesJump;
    private int totalFramesLand;
    private int totalFramesTurn;
    
    // Référence au crapaud (pour connaître sa position)
    private Crapaud crapaud;
    
    public CrapaudAnimation(Crapaud crapaud) {
        this.crapaud = crapaud;
        currentState = AnimationState.IDLE; // État par défaut
        frameIndex = 0;
        loadSprites();
        timer = new Timer(FRAME_DELAY, this);
        timer.start();
    }
    
    // Charge les spritesheets et découpe en cadres
    private void loadSprites() {
        try {
            // Exemple pour l'état IDLE
            BufferedImage idleSheet = ImageIO.read(getClass().getResource("/ressources/Frog/Idle.png"));
            int columns = 4; // à adapter selon votre spritesheet
            int rows = 1;
            totalFramesIdle = columns * rows;
            idleFrames = new BufferedImage[totalFramesIdle];
            int frameWidth = idleSheet.getWidth() / columns;
            int frameHeight = idleSheet.getHeight() / rows;
            for (int i = 0; i < totalFramesIdle; i++) {
                int x = (i % columns) * frameWidth;
                int y = (i / columns) * frameHeight;
                idleFrames[i] = idleSheet.getSubimage(x, y, frameWidth, frameHeight);
            }
            
            // État JUMP
            BufferedImage jumpSheet = ImageIO.read(getClass().getResource("/ressources/Frog/Jump.png"));
            columns = 4; rows = 1;
            totalFramesJump = columns * rows;
            jumpFrames = new BufferedImage[totalFramesJump];
            frameWidth = jumpSheet.getWidth() / columns;
            frameHeight = jumpSheet.getHeight() / rows;
            for (int i = 0; i < totalFramesJump; i++) {
                int x = (i % columns) * frameWidth;
                int y = (i / columns) * frameHeight;
                jumpFrames[i] = jumpSheet.getSubimage(x, y, frameWidth, frameHeight);
            }
            
            // État LAND
            BufferedImage landSheet = ImageIO.read(getClass().getResource("/ressources/Frog/Land.png"));
            columns = 4; rows = 1;
            totalFramesLand = columns * rows;
            landFrames = new BufferedImage[totalFramesLand];
            frameWidth = landSheet.getWidth() / columns;
            frameHeight = landSheet.getHeight() / rows;
            for (int i = 0; i < totalFramesLand; i++) {
                int x = (i % columns) * frameWidth;
                int y = (i / columns) * frameHeight;
                landFrames[i] = landSheet.getSubimage(x, y, frameWidth, frameHeight);
            }
            
            // État TURN
            BufferedImage turnSheet = ImageIO.read(getClass().getResource("/ressources/Frog/Turn.png"));
            columns = 4; rows = 1;
            totalFramesTurn = columns * rows;
            turnFrames = new BufferedImage[totalFramesTurn];
            frameWidth = turnSheet.getWidth() / columns;
            frameHeight = turnSheet.getHeight() / rows;
            for (int i = 0; i < totalFramesTurn; i++) {
                int x = (i % columns) * frameWidth;
                int y = (i / columns) * frameHeight;
                turnFrames[i] = turnSheet.getSubimage(x, y, frameWidth, frameHeight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode appelée par le Timer pour mettre à jour l'animation
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ici vous pouvez ajouter une logique pour changer d'état selon le comportement du crapaud.
        // Par exemple, si le crapaud vient de changer de direction, vous pouvez passer en TURN pendant quelques frames.
        // Pour cet exemple, on reste toujours en IDLE.
        currentState = AnimationState.IDLE;
        
        frameIndex++;
        switch (currentState) {
            case IDLE:
                if(frameIndex >= totalFramesIdle) frameIndex = 0;
                break;
            case JUMP:
                if(frameIndex >= totalFramesJump) frameIndex = 0;
                break;
            case LAND:
                if(frameIndex >= totalFramesLand) frameIndex = 0;
                break;
            case TURN:
                if(frameIndex >= totalFramesTurn) frameIndex = 0;
                break;
        }
        
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage currentFrame = null;
        switch(currentState) {
            case IDLE:
                currentFrame = idleFrames[frameIndex];
                break;
            case JUMP:
                currentFrame = jumpFrames[frameIndex];
                break;
            case LAND:
                currentFrame = landFrames[frameIndex];
                break;
            case TURN:
                currentFrame = turnFrames[frameIndex];
                break;
        }
        if(currentFrame != null) {
            // Centrer le dessin sur la position du crapaud
            int drawX = crapaud.getX() - currentFrame.getWidth()/2;
            int drawY = crapaud.getY() - currentFrame.getHeight()/2;
            g.drawImage(currentFrame, drawX, drawY, this);
        }
    }
}
