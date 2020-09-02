/*
    GitHub: https://github.com/LarsDoesCode/
    GitHub Repo: https://github.com/LarsDoesCode/Pong
    Last updated 31.08.2020
 */

import javax.swing.*;   // import modules
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Pong extends JPanel implements Runnable, KeyListener {

    JFrame myFrame; // declare basic attributes
    Random random;
    Ball[] matchBall;
    Brick brickLeft, brickRight;
    Bonus item;
    Image background;
    int counterLeft, counterRight;

    public Pong(int w, int h) {

        initGame(); // start game

        this.setPreferredSize(new Dimension(w, h)); // window gets created with given height and width
        this.setBackground(Color.BLACK);
        background = Toolkit.getDefaultToolkit().getImage("Pictures/Background.png");
        myFrame = new JFrame("Pong Game - Lars K.");
        myFrame.setLocation(100, 100);
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when window is closed exit the program
        myFrame.add(this);
        myFrame.addKeyListener(this); // add the KeyListener to the window (so the program can get keys)
        myFrame.pack();
        myFrame.setVisible(true); // make window visible

        Thread th = new Thread(this);
        th.start();
    }

    private void initGame() {
        random = new Random();

        brickLeft = new Brick(100, 200, 10, 50); // initialize bricks (bumpers)
        brickRight = new Brick(700, 200, 10, 50);

        item = new Bonus(random.nextInt(200 + 1) + 350 + 1, random.nextInt(150 + 1) + 250 + 1, 20, false); // randomize the first spawn location of the bonus item

        matchBall = new Ball[1]; // how many balls are in the game
        for (int i = 0; i < matchBall.length; i++) {
            matchBall[i] = new Ball(random.nextInt(200 + 1) + 350 + 1, random.nextInt(150 + 1) + 250 + 1, 5, 5, 20); // randomize spawn-location of the balls
        }
    }

    @Override
    public void run() { // runs the game as long as the window is open
        while (myFrame.isVisible()) {
            moveObjects(); // constantly move the objects
            repaint(); // constantly repaint the objects
            checkScore();

            if (counterLeft >= 10 || counterRight >= 10) {
                endScreen();
                System.exit(0);
            }
            if (counterLeft >= 5 || counterRight >= 5) { // when one of the players have a score of 5 or higher spawn random items
                item.setVisible(true); // makes item visible

                for (Ball ball : matchBall) { // extended for Explanation: For every ball object in Ball[] matchball do:
                    if (ball.getRectangle().intersects(item.getRectangle())) {
                        item.resetSpawn(); // spawns new item
                        item.setVisible(false); // hides the new item
                        int randomInteger = random.nextInt(6); // random integer defines what happens if object get hit

                        switch (randomInteger) {
                            case 0 -> {
                                ball.changeXDirection();
                                System.out.println("The Ball changed directions");
                            }
                            case 1 -> {
                                ball.speedUp(2);
                                System.out.println("The Ball moves faster");
                            }
                            case 2 -> {
                                brickLeft.speedUp();
                                System.out.println("The left Brick now moves faster");
                            }
                            case 3 -> {
                                brickLeft.slowDown();
                                System.out.println("The left Brick now moves slower");
                            }
                            case 4 -> {
                                brickRight.speedUp();
                                System.out.println("The right Brick now moves faster");
                            }
                            case 5 -> {
                                brickRight.slowDown();
                                System.out.println("The right Brick now moves slower");
                            }
                        }
                    }
                }
            }
            // if ball intersects with Brick then bounce of
            for (int i = 0; i < matchBall.length; i++) {  // balls bounce of Brick
                if (matchBall[i].getRectangle().intersects(brickLeft.getRectangle()) || matchBall[i].getRectangle().intersects(brickRight.getRectangle())) {
                    matchBall[i].changeXDirection();
                }
                // if ball intersects with another ball, they bounce of
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
                Thread.sleep(10); // how often the game repeats itself
            } catch (InterruptedException e) {
                System.out.println(e); // if error is caught print error
            }
        }
    }

    public void moveObjects() { // moves the objects
        brickLeft.move();
        brickRight.move();

        for (Ball ball : matchBall) { // enhanced for (For every Ball in Ball[] array)
            ball.move();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null); // draws the image

        if (item.isVisible()) { // makes item visible to the player
            g.setColor(Color.GREEN);
            g.fillRect(item.getxKoord(), item.getyKoord(), item.getSize1(), item.getSize2());
        }
        g.setColor(Color.WHITE);
        for (Ball ball : matchBall) { // draw every ball
            g.fillOval(ball.getxKoord(), ball.getyKoord(), ball.getSize1(), ball.getSize2()); // Draws matchBalls
        }

        g.setColor(Color.YELLOW); // draw the 2 bricks
        g.fillRect(brickLeft.getxKoord(), brickLeft.getyKoord(), brickLeft.getSize1(), brickLeft.getSize2()); // Draws brick
        g.fillRect(brickRight.getxKoord(), brickRight.getyKoord(), brickRight.getSize1(), brickRight.getSize2());

        g.setColor(Color.WHITE); // draw / display counter
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("" + counterLeft, 50, 40); // Displays current score
        g.drawString("" + counterRight, 740, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) { // Move brick
        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            brickRight.setDirection('w'); // moves brick up
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN)) {
            brickRight.setDirection('s'); // moves brick down
        }

        if ((e.getKeyChar() == 'w')) {
            brickLeft.setDirection('w');
        }

        if ((e.getKeyChar() == 's')) {
            brickLeft.setDirection('s');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // When key is released stop Brick
        if ((e.getKeyCode() == KeyEvent.VK_UP)) {
            brickRight.setDirection('x'); // makes brick stop
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 27) { // if escape is pressed exit
            System.exit(0);
        } // end of if
    }

    public void checkScore() {
        for (Ball ball : matchBall) {
            if (ball.getxKoord() < 0) {
                counterRight++;
            }
            if (ball.getxKoord() > 780) {
                counterLeft++;
            }
        }
    }

    public void endScreen() {
        System.out.println("The Game has ended!");

        if (counterLeft > counterRight) {
            System.out.println("The Left Player has won!");
            System.out.println("Score \nLeft: " + counterLeft + " - Right: " + counterRight);
        }
        if (counterLeft < counterRight) {
            System.out.println("The Right Player has won!");
            System.out.println("Score \nLeft: " + counterLeft + " - Right: " + counterRight);
        }
    }

    public static void main(String[] args) { // Main
        new Pong(800, 600);
    } // main
}


