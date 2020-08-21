import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    public int counterRight, counterLeft;
    private int xKoord;
    private int yKoord;
    private int dx;
    private int dy;
    private final int size;
    private final Rectangle rectangle;

    public Ball(int pX, int pY, int pDx, int pDy, int pSize) {
        xKoord = pX;
        yKoord = pY;
        dx = pDx;
        dy = pDy;
        size = pSize;
        counterRight = 0;
        counterLeft = 0;
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

    public void changeXDirection() {
        dx = -dx;
    }

    public void changeYDirection() {
        dy = -dy;
    }

    public void speedUp(int pSpeed) {
        dx -= pSpeed;
    }

    public void resetSpawn() {
        Random random = new Random();
        xKoord = random.nextInt(200 + 1) + 350 + 1; // random spawn in specific field
        yKoord = random.nextInt(150 + 1) + 250 + 1;
        dx = -dx; // Turns the direction of the ball around to make it fair
        dy = -dy;
        rectangle.setLocation(xKoord, yKoord);
    }

    public int getCounterLeft() {
        return counterLeft;
    }

    public int getCounterRight() {
        return counterRight;
    }

    public void move() {

        if (xKoord < 0) { // when ball gets 'out of bounce' reset
            counterRight++;
            resetSpawn();
        }

        if (xKoord > 780) {
            counterLeft++;
            resetSpawn();
        }

        if (yKoord < 0 || yKoord > 580) { // change directions when top or bottom is hit
            dy = -dy;
        }
        xKoord += dx;
        yKoord += dy;
        rectangle.setLocation(xKoord, yKoord);
    }
}
