import java.awt.Rectangle;

public class Bonus {
    public int counterRight, counterLeft;
    private final int xKoord;
    private final int yKoord;
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