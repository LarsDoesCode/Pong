import java.awt.Rectangle;

public class Bonus {
    public int counterRight, counterLeft;
    private int xKoord;
    private int yKoord;
    private final int size;
    private final Rectangle rectangle;


    public Bonus(int pX, int pY, int pSize) {
        xKoord = pX;
        yKoord = pY;
        size = pSize;
        rectangle = new Rectangle(xKoord, yKoord, size, size);
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

}