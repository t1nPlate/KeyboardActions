package com.keyboard.simplemoving;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleMoving {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    protected static JPanel panel;

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Simple Moving");

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setFocusable(true);

        BufferedImage image = ImageIO.read(new File("assets/lion.png"));
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(0, 0, image.getWidth(), image.getHeight());
        panel.add(label);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN -> {
                        if(label.getY() >= HEIGHT - label.getHeight()*2)
                            label.setLocation(label.getX(), 0);
                        label.setLocation(label.getX(), label.getY() + 15);
                    }
                    case KeyEvent.VK_UP -> {
                        if(label.getY() - 15 <= 0)
                            label.setLocation(label.getX(), HEIGHT-label.getHeight()*2+30);
                        label.setLocation(label.getX(), label.getY() - 15);
                    }
                    case KeyEvent.VK_LEFT -> {
                        if(label.getX() - 15 <= 0)
                            label.setLocation(WIDTH - label.getWidth(), label.getY());
                        label.setLocation(label.getX() - 15, label.getY());
                    }
                    case KeyEvent.VK_RIGHT -> {
                        if(label.getX() + 15 >= WIDTH - label.getWidth())
                            label.setLocation(0, label.getY());
                        label.setLocation(label.getX() + 15, label.getY());
                    }
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dm.width/2-WIDTH/2, dm.height/2-HEIGHT/2, WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
    }
}
