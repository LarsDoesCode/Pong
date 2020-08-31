import java.awt.Rectangle;

public class Brick extends Sprite {
    private char direction;
    private int speed;

    public Brick(int pXKoord, int pYKoord, int pSize1, int pSize2) {
        xKoord = pXKoord;
        yKoord = pYKoord;
        size1 = pSize1;
        size2 = pSize2;
        direction = 'x';
        rectangle = new Rectangle(xKoord, yKoord, size1, size2);
        speed = 6;
    }

    public void setDirection(char pDirection) { // sets direction
        direction = pDirection;
    }

    public void slowDown() {
        speed -= 2;
    }

    public void speedUp() {
        speed += 2;
    }

    @Override
    public void move() {
        rectangle.setLocation(xKoord, yKoord); // rearranges hitbox

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