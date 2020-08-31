
import java.awt.Rectangle;
import java.util.Random;

public abstract class Sprite {

    protected int xKoord, yKoord;
    protected int size1, size2;
    protected Rectangle rectangle;

    public int getyKoord() {
        return yKoord;
    }

    public int getxKoord() {
        return xKoord;
    }

    public int getSize1() {
        return size1;
    }

    public int getSize2() {
        return size2;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void resetSpawn() {
        Random random = new Random();
        xKoord = random.nextInt(200 + 1) + 350 + 1;
        yKoord = random.nextInt(150 + 1) + 250 + 1;
        rectangle.setLocation(xKoord, yKoord);
    }

    protected abstract void move();
}
