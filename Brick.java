import java.awt.Rectangle;

public class Brick {
    private int xKoord, yKoord, size1, size2;
    private Rectangle rect;
    private char direction;

    public Brick(int pXKoord, int pYKoord, int pSize1, int pSize2) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        size1 = pSize1;
        size2 = pSize2;
        direction = 'x';
        rect = new Rectangle(xKoord, yKoord, size1, size2);
    }

    public int getXKoord() {
        return xKoord;
    }

    public int getYKoord() {
        return yKoord;
    }

    public int getSize1() {
        return size1;
    }

    public int getSize2() {
        return size2;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setDirection(char pDirection) {
        direction = pDirection;
    }

    public void move(char pKey) {
        rect.setLocation(xKoord, yKoord);

        if (direction == 'x') {
            rect.setLocation(xKoord, yKoord);
        }
        if (yKoord > 600 - size2) {
            yKoord -= 6;
        }
        if (yKoord < 0) {
            yKoord += 6;
        }
        if (direction == 'w') {
            yKoord -= 6;
        }

        if (direction == 's') {
            yKoord += 6;
        }


    }
}