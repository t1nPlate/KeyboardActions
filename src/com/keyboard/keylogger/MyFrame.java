package com.keyboard.keylogger;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;

    public MyFrame() {
        super("Key Logger");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dm.width/2-WIDTH/2, dm.height/2-HEIGHT/2, WIDTH, HEIGHT);
        MyPanel panel = new MyPanel();
        this.add(panel);
        this.setVisible(true);
    }

}
