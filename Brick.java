import java.awt.Rectangle;

public class Brick {
    private final int xKoord;
    private int yKoord;
    private final int size1;
    private final int size2;
    private final Rectangle rectangle;
    private char direction;

    public Brick(int pXKoord, int pYKoord, int pSize1, int pSize2) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        size1 = pSize1;
        size2 = pSize2;
        direction = 'x';
        rectangle = new Rectangle(xKoord, yKoord, size1, size2);
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setDirection(char pDirection) {
        direction = pDirection;
    }

    public void move(int speed) {
        rectangle.setLocation(xKoord, yKoord);

        if (direction == 'x') {
            rectangle.setLocation(xKoord, yKoord);
        }
        if (yKoord > 600 - size2) { // When Brick hits the top or bottom stop it
            yKoord -= speed;
        }
        if (yKoord < 0) {
            yKoord += speed;
        }
        if (direction == 'w') {
            yKoord -= speed;
        }

        if (direction == 's') {
            yKoord += speed;
        }


    }
}