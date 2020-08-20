import java.awt.Rectangle;

public class Ball {
    public int counterRight, counterLeft;
    private int xKoord, yKoord, dx, dy, size;
    private int originalX, originalY;
    private Rectangle rect;


    public Ball(int pX, int pY, int pDx, int pDy, int pSize) {
        xKoord = pX;
        originalX = pX;
        yKoord = pY;
        originalY = pY;
        dx = pDx;
        dy = pDy;
        size = pSize;
        counterRight = 0;
        counterLeft = 0;
        rect = new Rectangle(xKoord, yKoord, size, size);
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

    public Rectangle getRect() {
        return rect;
    }

    public void aendereXRichtung() {
        dx = -dx;
    }

    public void aendereYRichtung() {
        dy = -dy;
    }

    public void resetSpawn() {
        xKoord = originalX;
        yKoord = originalY;
        dx = -dx; // Turns the direction of the ball around to make it fair
        dy = -dy;
        rect.setLocation(xKoord, yKoord);
        System.out.println("Es steht: Links: " + counterLeft + " Rechts: " + counterRight);
    }

    public int getCounterLeft() {
        return counterLeft;
    }

    public int getCounterRight() {
        return counterRight;
    }

    public void move() {

        if (xKoord < 0) { // When ball gets 'out of bounce' reset
            counterRight++;
            resetSpawn();
        }

        if (xKoord > 780) {
            counterLeft++;
            resetSpawn();
        }

        if (yKoord < 0 || yKoord > 580) { // Change directions when top or bottom is hitted
            dy = -dy;
        }
        xKoord += dx;
        yKoord += dy;
        rect.setLocation(xKoord, yKoord);
    }
}
