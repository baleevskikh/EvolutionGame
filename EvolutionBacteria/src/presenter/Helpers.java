package presenter;

import model.Bacterium;
import model.Food;

public abstract class Helpers {
    public static void bacteriumMove(Bacterium bacterium, float x, float y, float distance) {
        float speedX = (Math.abs(x - bacterium.x) / distance) * bacterium.speed;
        float speedY = (Math.abs(y - bacterium.y) / distance) * bacterium.speed;
        if(bacterium.x < x) bacterium.x += speedX;
        else if(bacterium.x > x) bacterium.x -= speedX;
        if(bacterium.y < y) bacterium.y += speedY;
        else if(bacterium.y > y) bacterium.y -= speedY;
    }

    public static float calculateDistance(float fX, float fY, float sX, float sY) {
        return (float) Math.sqrt((sX - fX) * (sX - fX) + (sY - fY) * (sY - fY));
    }
}
