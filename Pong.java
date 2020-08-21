import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Pong extends JPanel implements Runnable, KeyListener {

    JFrame myFrame;
    Random random;
    Ball[] matchBall;
    Brick brickLeft, brickRight;
    Bonus item;

    public Pong(int w, int h) {

        initGame();

        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.BLACK);
        myFrame = new JFrame("Pong Game - Lars K.");
        myFrame.setLocation(100, 100);
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(this);
        myFrame.addKeyListener(this);
        myFrame.pack();
        myFrame.setVisible(true);

        Thread th = new Thread(this);
        th.start();
    }

    private void initGame() {

        random = new Random();
        brickLeft = new Brick(100, 200, 10, 50);
        brickRight = new Brick(700, 200, 10, 50);
        matchBall = new Ball[1];
        item = new Bonus(random.nextInt(200 + 1) + 350 + 1, random.nextInt(150 + 1) + 250 + 1, 20, false);
        for (int i = 0; i < matchBall.length; i++) {
            matchBall[i] = new Ball(random.nextInt(200 + 1) + 350 + 1, random.nextInt(150 + 1) + 250 + 1, 3, 3, 20);
        }
    }

    @Override
    public void run() {
        while (myFrame.isVisible()) {
            moveObjects();
            repaint();

            if(matchBall[0].getCounterLeft() >= 5 || matchBall[0].getCounterRight() >= 5){
                item.setVisible(true);

                for (Ball ball : matchBall) {
                    if (ball.getRectangle().intersects(item.getRectangle())) {
                        System.exit(10);
                    }
                }
            }

            for (int i = 0; i < matchBall.length; i++) {  // Balls bounce of Brick
                if (matchBall[i].getRectangle().intersects(brickLeft.getRectangle()) || matchBall[i].getRectangle().intersects(brickRight.getRectangle())) {
                    matchBall[i].changeXDirection();
                }

                for (int k = i + 1; k < matchBall.length; k++) { // Balls bounce of each other
                    if (matchBall[i].getRectangle().intersects(matchBall[k].getRectangle())) {
                        matchBall[i].changeXDirection();
                        matchBall[k].changeXDirection();
                        matchBall[i].changeYDirection();
                        matchBall[k].changeYDirection();
                    }
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
    }

    public void moveObjects() {
        brickLeft.move(6);
        brickRight.move(6);

        for (Ball ball : matchBall) { // Enhanced for (For every Ball in Ball[] array)
            ball.move();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(item.isVisible()){
            g.setColor(Color.GREEN);
            System.out.println("yes");
            g.fillRect(item.getXKoord(), item.getYKoord(), item.getSize(), item.getSize());
        }
        g.setColor(Color.YELLOW);
        for (Ball ball : matchBall) {
            g.fillOval(ball.getXKoord(), ball.getYKoord(), ball.getSize(), ball.getSize()); // Draws matchBalls
        }

        g.setColor(Color.YELLOW);
        g.fillRect(brickLeft.getXKoord(), brickLeft.getYKoord(), brickLeft.getSize1(), brickLeft.getSize2()); // Draws brick
        g.fillRect(brickRight.getXKoord(), brickRight.getYKoord(), brickRight.getSize1(), brickRight.getSize2());

        g.setColor(Color.YELLOW);
        g.drawString("Left: " + matchBall[0].getCounterLeft(), 50, 20); // Displays current score
        g.drawString("Right: " + matchBall[0].getCounterRight(), 720, 20);

        for (int i = 0; i < 600; i++) {
            g.drawString("|", 400, i);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { // Move brick
        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            brickRight.setDirection('w');
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
            brickRight.setDirection('s');
        }

        if ((e.getKeyChar() == 'w')) {
            brickLeft.setDirection('w');
        }

        if ((e.getKeyChar() == 's')) {
            brickLeft.setDirection('s');
        }

//        if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {
//
//        }
//
//        if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
//
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // When key is released stop Brick
        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            brickRight.setDirection('x');
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
            brickRight.setDirection('x');
        }

        if ((e.getKeyChar() == 'w')) {
            brickLeft.setDirection('x');
        }

        if ((e.getKeyChar() == 's')) {
            brickLeft.setDirection('x');
        }

//        if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {
//
//        }
//
//        if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
//
//        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 27) { // If escape is pressed exit
            System.exit(0);
        } // end of if
    }

    public static void main(String[] args) { // Main
        new Pong(800, 600);
    }
}


