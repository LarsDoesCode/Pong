import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Pong extends JPanel implements Runnable, KeyListener {

    JFrame myFrame;
    Random rndm;
    Ball[] spielbaelle;
    Brick brickLeft, brickRight;


    public Pong(int w, int h) {
        ;
        initGame();

        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.WHITE);
        myFrame = new JFrame("Pong Game - Lars Kühn");
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
        //Hier werden alle Objekte des Spiels initialisiert
        brickLeft = new Brick(100, 200, 40, 250);
        brickRight = new Brick(700, 200, 40, 250);
        rndm = new Random();
        spielbaelle = new Ball[1];
        for (int i = 0; i < spielbaelle.length; i++) {
            spielbaelle[i] = new Ball(rndm.nextInt(200 + 1) + 350 + 1, rndm.nextInt(150 + 1) + 250 + 1, 5, 5, 20);
        }
    }

    @Override
    public void run() {
        while (myFrame.isVisible()) {
            moveObjects();
            repaint();

            for (int i = 0; i < spielbaelle.length; i++) {  // Bälle prallen an Brick ab
                if (spielbaelle[i].getRect().intersects(brickLeft.getRect()) || spielbaelle[i].getRect().intersects(brickRight.getRect())) {
                    spielbaelle[i].aendereXRichtung();
                }

                for (int k = i + 1; k < spielbaelle.length; k++) { // Bälle prallen an sich selber ab
                    if (spielbaelle[i].getRect().intersects(spielbaelle[k].getRect())) {
                        spielbaelle[i].aendereXRichtung();
                        spielbaelle[k].aendereXRichtung();
                        spielbaelle[i].aendereYRichtung();
                        spielbaelle[k].aendereYRichtung();
                    }
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            ;
        }
    }

    public void moveObjects() {
        //Hier werden die Objekte bewegt
        brickLeft.move('x');
        brickRight.move('x');

        for (int i = 0; i < spielbaelle.length; i++) {
            spielbaelle[i].move();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Hier werden die Objekte gezeichnet
        g.setColor(Color.RED);
        for (int i = 0; i < spielbaelle.length; i++) {
            g.fillOval(spielbaelle[i].getXKoord(), spielbaelle[i].getYKoord(), spielbaelle[i].getSize(), spielbaelle[i].getSize());
        }
        g.setColor(Color.BLUE);
        g.fillRect(brickLeft.getXKoord(), brickLeft.getYKoord(), brickLeft.getSize1(), brickLeft.getSize2());
        g.fillRect(brickRight.getXKoord(), brickRight.getYKoord(), brickRight.getSize1(), brickRight.getSize2());
        g.setColor(Color.BLACK);
        g.drawString("Links: " + Integer.toString(spielbaelle[0].getCounterLeft()), 50, 20);
        g.drawString("Rechts: " + Integer.toString(spielbaelle[0].getCounterRight()), 700, 20);
        // Für String zeichnen g.forStringw
    }


    @Override
    public void keyPressed(KeyEvent e) {
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

        if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {

        }

        if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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

        if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {

        }

        if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 27) {
            System.exit(0);
        } // end of if
    }

    public static void main(String[] args) {
        new Pong(800, 600);
    }
}


