import java.awt.Rectangle;

public class Ball extends Sprite {
    private int dx;
    private int dy;

    public Ball(int pX, int pY, int pDx, int pDy, int pSize) {
        xKoord = pX;
        yKoord = pY;
        dx = pDx;
        dy = pDy;
        size1 = pSize;
        size2 = pSize;
        rectangle = new Rectangle(xKoord, yKoord, size1, size2);
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

    @Override
    public void move() {

        if (xKoord < 0) { // when ball gets 'out of bounce' reset
            changeXDirection();
            changeYDirection();
            resetSpawn();
        }

        if (xKoord > 780) {
            changeXDirection();
            changeYDirection();
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
