package com.keyboard.keylogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyPanel extends JPanel {

    private JLabel label;
    private BufferedWriter fileWriter;

    public MyPanel() {
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(MyFrame.WIDTH, MyFrame.HEIGHT));
        this.setFocusable(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 16));
        try {
            fileWriter = new BufferedWriter(new FileWriter("assets/output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key != KeyEvent.VK_ESCAPE && key != KeyEvent.VK_SHIFT && key != KeyEvent.VK_BACK_SPACE) {
                    if(e.isShiftDown()) {
                        label.setText(label.getText() + String.valueOf(e.getKeyChar()).toUpperCase());
                        try {
                            fileWriter.write(String.valueOf(e.getKeyChar()).toUpperCase());
                            fileWriter.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        label.setText(label.getText() + e.getKeyChar());
                        try {
                            fileWriter.write(e.getKeyChar());
                            fileWriter.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if(key == KeyEvent.VK_BACK_SPACE && label.getText().length() > 0) {
                    label.setText(label.getText().substring(
                            0, label.getText().length()-1)
                    );
                    try {
                        fileWriter = delete(label.getText());
                        fileWriter.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(label);
    }

    private BufferedWriter delete(String text) throws IOException {
        BufferedWriter tmp = new BufferedWriter(new FileWriter("assets/output.txt"));
        tmp.write(text);
        return tmp;
    }
}
