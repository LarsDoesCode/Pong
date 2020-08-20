import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    private int xKoord, yKoord, dx, dy, size;
    private int originalX, originalY;
    private Rectangle rect;
    public int counterRight, counterLeft;


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
        xKoord = originalX; // Standart Spawn
        yKoord = originalY;
        dx = -dx; // Verdreht Richtung --> Macht es fair
        dy = -dy;
        rect.setLocation(xKoord, yKoord);
        System.out.println("Es steht: Links: " + counterLeft + " Rechts: " + counterRight);
    }

    public void move() {
        //if(xKoord < 0 || xKoord > 780){
        if (xKoord < 0) { // Wenn trifft seite dann reset
            counterRight++;
            resetSpawn();
        }

        if (xKoord > 780) {
            counterLeft++;
            resetSpawn();
        }

        if (yKoord < 0 || yKoord > 580) {
            dy = -dy;
        }
        xKoord += dx;
        yKoord += dy;
        rect.setLocation(xKoord, yKoord);
    }

    public int getCounterLeft() {
        return counterLeft;
    }

    public int getCounterRight() {
        return counterRight;
    }
}
