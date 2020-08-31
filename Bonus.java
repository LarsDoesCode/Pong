import java.awt.Rectangle;

public class Bonus extends Sprite {

    private boolean visible;

    public Bonus(int pX, int pY, int pSize, boolean pVisible) {
        xKoord = pX;
        yKoord = pY;
        size1 = pSize;
        size2 = pSize;
        visible = pVisible;
        rectangle = new Rectangle(xKoord, yKoord, size1, size2);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean pVisible) {
        visible = pVisible;
    }

    @Override
    protected void move() {

    }
}