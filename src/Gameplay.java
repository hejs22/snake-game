
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Gameplay extends JFrame {

    static int sizeOfBoard = 19;
    static int waitTime = 120;
    static int sizeOfBox = 30;
    static JFrame Frame = new JFrame();
    static Player P = new Player("hejs", Frame);
    static Board B1 = new Board(sizeOfBoard, sizeOfBoard, P);


    public Gameplay() {
        JPanel Panel = new JPanel();
        Frame.add(Panel);
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                P.alive = true;
                playButton.setVisible(false);
                Panel.setVisible(false);
                B1.fillGameBoard(Frame);
            }
        });
        Frame.setFocusable(true);
        Frame.setFocusTraversalKeysEnabled(false);
        Panel.add(playButton);
        Panel.setBackground(Color.ORANGE);
        Frame.setSize(150, 100);
        Frame.setLocation(0, 0);
        Frame.setVisible(true);
        Container C = Frame.getContentPane();
        C.setBackground(Color.ORANGE);
        Frame.setTitle("Snake!");
        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    P.direction = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    P.direction = 3;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    P.direction = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    P.direction = 4;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    P.direction = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    P.direction = 3;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    P.direction = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    P.direction = 4;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    P.direction = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    P.direction = 3;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    P.direction = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    P.direction = 4;
                }
            }
        });
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        new Gameplay();
        while (true) {
            P.movePlayer(Frame);
            wait(waitTime);
        }
    }
}