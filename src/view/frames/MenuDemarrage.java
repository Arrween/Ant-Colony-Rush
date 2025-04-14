package view.frames;

import javax.swing.*;
import model.MusicPlayer;
import view.panels.DifficultePanel;

public class MenuDemarrage extends JFrame {
    private DifficultePanel difficultePanel;

    public MenuDemarrage() {
        setTitle("Jeu Fourmis - Menu de démarrage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        MusicPlayer.initSound(); // Initialisation des sons
        MusicPlayer.playMusicMenu(); // Lecture de la musique de menu

        difficultePanel = new DifficultePanel();
        add(difficultePanel);

    }

    public DifficultePanel getDifficultePanel() {
        return difficultePanel;
    }
}
