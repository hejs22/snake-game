import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    boolean alive = false;
    int posX, posY, direction, length;
    int sizeOfBoard = Gameplay.sizeOfBoard;
    int sizeOfBody = 50;
    int waitTime = Gameplay.waitTime;
    int sizeOfBox = Gameplay.sizeOfBox;
    static ArrayList<JPanel> playerSprite = new ArrayList<JPanel>();
    static JPanel apple = new JPanel();
    String playerName;
    public Player(String name, JFrame Frame) {
        playerName = name;
        direction = 0;
        length = 1;
        posX = sizeOfBoard / 2;
        posY = sizeOfBoard / 2;
        playerSprite.add(new JPanel());
        playerSprite.get(0).setBackground(Color.BLACK);
        playerSprite.get(0).setLocation(sizeOfBox*posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox*posY + (sizeOfBox - sizeOfBody) / 2);
        playerSprite.get(0).setSize(sizeOfBody, sizeOfBody);
        Frame.add(playerSprite.get(0));
        for (int i = 0; i < 1000; i++) growSnake(Frame);
        apple.setBackground(Color.RED);
        apple.setSize(sizeOfBody, sizeOfBody);
        apple.setLocation(sizeOfBox*3 + (sizeOfBox - sizeOfBody) / 2, sizeOfBox*3 + (sizeOfBox - sizeOfBody) / 2);
        Frame.add(apple);
    }

    public void movePlayer(JFrame Frame) {
        if (direction != 0) {
            if (direction == 1) {
                posY--;
            } else if (direction == 2) {
                posX++;
            } else if (direction == 3) {
                posY++;
            } else if (direction == 4) {
                posX--;
            }

            if ((posX < 0) || (posX > sizeOfBoard) || (posY < 0) || (posY > sizeOfBoard)) { // zrobić bardziej uniwersalne
                alive = false;
            } else {
                for (int i = 1; i < length; i++) {
                    if (playerSprite.get(i).getLocation().x == playerSprite.get(0).getLocation().x) {
                        if (playerSprite.get(i).getLocation().y == playerSprite.get(0).getLocation().y) {
                            alive = false;
                        }
                    }
                }

                if (alive) {
                    for (int i = length - 1; i >= 1; i--) {
                        playerSprite.get(i).setLocation(playerSprite.get(i - 1).getLocation());
                        //System.out.println(playerSprite.get(i).getLocation().x);
                    }
                    playerSprite.get(0).setLocation(posX*sizeOfBox + (sizeOfBox - sizeOfBody) / 2, posY*sizeOfBox + (sizeOfBox - sizeOfBody) / 2);
                }
            }
        }

        Random rand = new Random();
        if ((posX == (apple.getLocation().x - (sizeOfBox - sizeOfBody) / 2) / 50) && (posY ==  (apple.getLocation().y - (sizeOfBox - sizeOfBody) / 2) / 50)) {
            growSnake2();
            int randX = rand.nextInt(sizeOfBoard) * 50 + (sizeOfBox - sizeOfBody) / 2;
            int randY = rand.nextInt(sizeOfBoard) * 50 + (sizeOfBox - sizeOfBody) / 2;
            for (int i = length - 1; i >= 0; i--) {
                int X = playerSprite.get(i).getLocation().x;
                int Y = playerSprite.get(i).getLocation().y;
                if ((X == randX) && (Y == randY)) {
                    i = -1;
                    randX = rand.nextInt(sizeOfBoard) * 50 + (sizeOfBox - sizeOfBody) / 2;
                    randY = rand.nextInt(sizeOfBoard) * 50 + (sizeOfBox - sizeOfBody) / 2;
                }
            }
            apple.setLocation(randX, randY);
        }
    }
    public void growSnake2() {
        length++;
        if (Gameplay.waitTime > 50) {
            Gameplay.waitTime -= 1;
        }
    }
    public void growSnake(JFrame Frame) {
        JPanel node = new JPanel();
        node.setBackground(Color.DARK_GRAY);
        node.setVisible(true);
        node.setLocation(-100, -100);
        node.setSize(sizeOfBody, sizeOfBody);
        playerSprite.add(node);
        Frame.add(node);
    }

}
