import java.awt.Rectangle;

public class Brick{
    private int xKoord, yKoord, size1, size2;
    private Rectangle rect;
    public Brick(int pXKoord, int pYKoord, int pSize1, int pSize2){
        xKoord = pXKoord;
        yKoord = pYKoord;
        size1 = pSize1;
        size2 = pSize2;
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

    public void goUp(){
        yKoord -= 50;
        rect.setLocation(xKoord, yKoord);
    }

    public void goDown(){
        yKoord += 50;
        rect.setLocation(xKoord, yKoord);
    }
}