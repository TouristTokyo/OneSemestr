package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.SwingUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrameGames extends JFrame {
    private JPanel panelMain;
    private JButton buttonGame1;
    private JButton buttonGame2;
    private JButton buttonGame3;
    private JButton buttonGame4;
    private JLabel labelImage;
    private JButton buttonSmallErrorInLogicTasks;

    public FrameGames() {
        this.setTitle("LauncherGames");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/пользователь/Desktop/Taski (не все)/Game/Launcher.jpg"));
            labelImage.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FrameVoglGame().setVisible(true);
                    }
                });
            }
        });
        buttonGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FrameKrestiksNoliksGame().setVisible(true);
                    }
                });
            }
        });
        buttonGame3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FrameRubikGame().setVisible(true);
                    }
                });
            }
        });
        buttonGame4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FrameTetrisGame().setVisible(true);
                    }
                });
            }
        });
        buttonSmallErrorInLogicTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtils.showInfoMessageBox("В некоторых играх есть небольшие нарушения (с точки зрения напсиания кода).\n" +
                        "Все примечания лучше узнать у авторов игр.",
                        "LauncherGames");
            }
        });
    }
}
