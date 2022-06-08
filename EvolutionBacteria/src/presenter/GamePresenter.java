package presenter;

import model.Bacterium;
import model.Food;

import java.util.ArrayList;

public class GamePresenter {
    // Настройки пространства
    public final int width = 1650;
    public final int height = 900;

    private final int foodCount = 100;
    private final ArrayList<Bacterium> bacteriums = new ArrayList<>();
    private final ArrayList<Food> foods = new ArrayList<>();

    public GamePresenter() {
        // Добавление бактерии
        bacteriumAdd();
        // Добавление еды
        foodAdd();
    }

    public void makeStep() {
        ArrayList<Bacterium> bacteriums = getBacteriums();
        for (int i =0; i < bacteriums.size(); i++) {
            Bacterium bacterium = bacteriums.get(i);
            float minDistance = 1000;
            Food nearestFood = null;
            float minX = 1;
            float minY = 1;
            for (Food food : foods) {
                float distance = Helpers.calculateDistance(bacterium.x, bacterium.y, food.x, food.y);
                if (distance < minDistance) {
                    minDistance = distance;
                    minX = food.x;
                    minY = food.y;
                    nearestFood = food;
                }
            }
            Helpers.bacteriumMove(bacterium, minX, minY, minDistance);
            if (minDistance < bacterium.radius) {
                bacterium.addFood();
                // Удаляем еду
                foods.remove(nearestFood);
            }
            if (bacterium.amountFood >= bacterium.split) {
                bacteriums.add(new Bacterium(bacterium.x + bacterium.radius, bacterium.y + bacterium.radius));
                bacterium.amountFood = 0;
                getStatistics();
            }
            bacterium.age += 1;
            // Удаляем бактерию, если она умерла
            if (bacterium.age > bacterium.lifeTime)
                bacteriums.remove(bacterium);
        }
        foodAdd();
    }

    private void bacteriumAdd() {
        bacteriums.add(new Bacterium(
                (float)(Math.random() * (width - 100) + 50),
                (float)(Math.random() * (height - 100) + 50)
        ));
    }

    private void foodAdd() {
        for(int i = getFoods().size(); i < foodCount; i++)
            foods.add(new Food(
                    (float)(Math.random() * (width - 100) + 50),
                    (float)(Math.random() * (height - 100) + 50)
            ));
    }

    private void getStatistics() {
        ArrayList<Bacterium> bacteriums = getBacteriums();
        float averageSpeed = 0;
        int averageAmountFood = 0;
        for (Bacterium bacterium : bacteriums) {
            averageSpeed += bacterium.speed;
            averageAmountFood += bacterium.split;
        }
        averageSpeed /= bacteriums.size();
        averageAmountFood /= bacteriums.size();
        System.out.println("Количество бактерий: " + bacteriums.size());
        System.out.println("Средняя скорость: " + averageSpeed);
        System.out.println("Среднее количество еды для размножения: " + averageAmountFood);
    }

    public ArrayList<Bacterium> getBacteriums() {
        return bacteriums;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}
