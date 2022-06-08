package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements Runnable {
    private final int width = 1920;
    private final int height = 1080;

    public MainWindow() {
        setTitle("Эволюция бактерий");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        add(new GameField());
        setVisible(true);
    }

    @Override
    public void run() {
        while(true) {
            this.repaint();
        }
    }
}