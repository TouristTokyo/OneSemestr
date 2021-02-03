package ru.vsu.cs.Shemenev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JSpinner spinnerSize;
    private JSpinner spinnerLevelCount;
    private JButton buttonDraw;
    private JLabel labelImg;
    private JButton buttonSaveOutputFile;
    private BufferedImage image;

    public FrameMain() {
        this.setTitle("2D");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        spinnerSize.setValue(512);
        spinnerLevelCount.setValue(1);

        buttonDraw.addActionListener(e -> {
            int size = (int) spinnerSize.getValue();
            int levelCount = (int) spinnerLevelCount.getValue();

            BufferedImage img = new BufferedImage(size, (int) Math.round(size * Math.sqrt(3) / 2), BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.BLUE);
            Rounds.paintRounds(g2d, img.getWidth(), img.getHeight(), levelCount);
            labelImg.setIcon(new ImageIcon(img));
            image = img;
        });

        buttonSaveOutputFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(image, "png", new File("2D.png"));
                } catch (IOException f) {
                    System.out.println("Error");
                }
            }
        });
    }
}

