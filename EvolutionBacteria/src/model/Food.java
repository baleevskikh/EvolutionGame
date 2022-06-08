package model;

import java.awt.*;

public class Food {
    // Координаты
    public float x;
    public float y;
    // Радиус
    public final int radius = 5;
    // Цвет
    public int color;
    public static Color[] COLOR = {
            new Color(255, 100, 100),
            new Color(200, 200, 100),
            new Color(50, 150, 50),
            new Color(100, 150, 200),
            new Color(50, 50, 255),
            new Color(150, 50, 150)
    };

    public Food(float x, float y) {
        this.x = x;
        this.y = y;
        this.color = (int)(Math.random() * 6);
    }
}
