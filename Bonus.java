import java.awt.Rectangle;
import java.util.Random;

public class Bonus {
    private int xKoord;
    private int yKoord;
    private final int size;
    private final Rectangle rectangle;
    private boolean visible;

    public Bonus(int pX, int pY, int pSize, boolean pVisible) {
        xKoord = pX;
        yKoord = pY;
        size = pSize;
        visible = pVisible;
        rectangle = new Rectangle(xKoord, yKoord, size, size);
    }
    public void newSpawn(){
        Random random = new Random();
        xKoord = random.nextInt(200 + 1) + 350 + 1;
        yKoord = random.nextInt(150 + 1) + 250 + 1;
    }

    public void reArrange(){
        rectangle.setLocation(xKoord, yKoord);
    }
    public int getXKoord() {
        return xKoord;
    }

    public int getYKoord() {
        return yKoord;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean pVisible) {
        visible = pVisible;
    }
}