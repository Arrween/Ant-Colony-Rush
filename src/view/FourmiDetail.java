package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Fourmi;

public class FourmiDetail extends JFrame {

    private Fourmi fourmi;

    public FourmiDetail(Fourmi fourmi, int index) {
        this.fourmi = fourmi;
        setTitle("Détail de la fourmi " + index);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("../ressources/fourmiTest.png"));
            Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            imageLabel = new JLabel(icon);
        } catch (Exception e) {
            imageLabel = new JLabel("Photo fourmi non trouvée");
        }

        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(imageLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel energieLabel = new JLabel("Energie : " + fourmi.getEnergie());
        JLabel positionLabel = new JLabel("Position : (" + fourmi.getX() + ", " + fourmi.getY() + ")");
        infoPanel.add(energieLabel);
        infoPanel.add(positionLabel);
        add(infoPanel, BorderLayout.CENTER);

        JButton expeditionButton = new JButton("Envoyer en expedition");
        expeditionButton.addActionListener(e -> {
            // controler
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(expeditionButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
