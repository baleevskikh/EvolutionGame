package view;

import model.Bacterium;
import model.Food;
import presenter.GamePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameField extends JPanel {
    // Настройки холста
    private final GamePresenter gamePresenter = new GamePresenter();
    private final Color backgroundColor = new Color(200, 200, 200, 255);
    private final BufferedImage canvas = new BufferedImage(gamePresenter.width, gamePresenter.height, BufferedImage.TYPE_INT_RGB);
    private final BufferedImage scene = new BufferedImage(gamePresenter.width, gamePresenter.height, BufferedImage.TYPE_INT_RGB);

    public GameField() {
        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        // Отрисовка сцены
        try {
            drawScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g2 = canvas.createGraphics();
        g2.drawImage(scene, null, 0, 0);
        ((Graphics2D)g).drawImage(canvas, null, 8, 30);
        // Логика сцены
        gamePresenter.makeStep();
    }

    private void drawScene(BufferedImage scene) throws IOException {
        Graphics2D g2 = scene.createGraphics();
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, gamePresenter.width, gamePresenter.height);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Отрисовка бактерий
        ArrayList<Bacterium> bacteriums = gamePresenter.getBacteriums();
        for (Bacterium bacterium : bacteriums) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillOval((int) bacterium.x - bacterium.radius, (int) bacterium.y - bacterium.radius,
                    bacterium.radius * 2, bacterium.radius * 2);
            g2.setColor(bacterium.color);
            int bacteriumRadius = bacterium.radius - 2;
            g2.fillOval((int) bacterium.x - bacteriumRadius, (int) bacterium.y - bacteriumRadius,
                    bacteriumRadius * 2, bacteriumRadius * 2);
        }
        // Отрисовка еды
        ArrayList<Food> foods = gamePresenter.getFoods();
        for (Food food : foods) {
            g2.setColor(Food.COLOR[food.color]);
            g2.fillOval((int) food.x - food.radius, (int) food.y - food.radius, food.radius * 2, food.radius * 2);
        }
    }
}
