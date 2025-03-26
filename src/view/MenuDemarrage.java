package view;

import javax.swing.*;

public class MenuDemarrage extends JFrame {
    private DifficultePanel difficultePanel;

    public MenuDemarrage() {
        setTitle("Jeu Fourmis - Menu de démarrage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        difficultePanel = new DifficultePanel();
        add(difficultePanel);
    }

    public DifficultePanel getDifficultePanel() {
        return difficultePanel;
    }
}
