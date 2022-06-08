package model;

import java.awt.*;

public class Bacterium {
    // Координаты
    public float x;
    public float y;
    // Радиус
    public final int radius = 15;
    // Время существования
    public int age;
    // Время жизни
    public int lifeTime;
    // Скорость
    public float speed;
    // Цвет
    public Color color;
    // Количество еды
    public int amountFood;
    // Количество еды для размножения
    public int split;

    public void addFood() {
        this.amountFood++;
        this.age = 0;
    }
    public void mutate() {
        /*
        Мутация бактерии
        Максимальня скорость 1.1, минимальная 0.3
        Максимальное количество еды для деления 4, минимальное 1
        скорость <0.5 - еда 1
        скорость >=0.5 - еда 2
        скрость >=0.7 - еда 3
        скорость >=0.9 - еда 4
         */
        this.speed = (float)(Math.random() * 1.1f + 0.3f);
        if (this.speed < 0.5) {
            this.split = 1;
            this.color = new Color(0, 169, 255);
        }
        else if (this.speed >= 0.5 && this.speed < 0.7) {
            this.split = 2;
            this.color = new Color(55, 169, 200);
        }
        else if (this.speed >= 0.7 && this.speed < 0.9) {
            this.split = 3;
            this.color = new Color(200, 169, 55);
        }
        else {
            this.split = 4;
            this.color = new Color(255, 169, 0);
        }
        System.out.println("Добавлена бактерия со скростью: " + this.speed + " и количеством еды: " + this.split);
    }
    public Bacterium(float x, float y) {
        this.x = x;
        this.y = y;
        this.age = 0;
        this.lifeTime = 500;
        this.amountFood = 0;
        this.split = 2;
        mutate();
    }
}
