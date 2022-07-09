import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    boolean alive = false;
    int posX, posY, direction, length;
    int sizeOfBoard = Gameplay.sizeOfBoard;
    int sizeOfBody = 30;
    int waitTime = Gameplay.waitTime;
    int sizeOfBox = Gameplay.sizeOfBox;
    static ArrayList<JPanel> playerSprite = new ArrayList<JPanel>();
    static JPanel apple = new JPanel();
    String playerName;
    public Player(String name, JFrame Frame) {  // Initialization of Snake
        playerName = name;  // Setting parameters of Snake
        direction = 0;
        length = 1;
        posX = sizeOfBoard / 2;
        posY = sizeOfBoard / 2;
        playerSprite.add(new JPanel()); // Spawning head of Snake
        playerSprite.get(0).setBackground(Color.BLACK);
        playerSprite.get(0).setLocation(sizeOfBox*posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox*posY + (sizeOfBox - sizeOfBody) / 2);
        playerSprite.get(0).setSize(sizeOfBody, sizeOfBody);
        Frame.add(playerSprite.get(0));
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) spawnNode(Frame); // Spawning multiple nodes at the beginning

        apple.setBackground(Color.RED); // Spawning first apple
        apple.setSize(sizeOfBody, sizeOfBody);
        apple.setLocation(sizeOfBox*3 + (sizeOfBox - sizeOfBody) / 2, sizeOfBox*3 + (sizeOfBox - sizeOfBody) / 2);
        Frame.add(apple);
    }

    public void movePlayer(JFrame Frame) {       // Making moves based on input, checking if collision occurs or if apple is eaten
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

            if ((posX < 0) || (posX >= sizeOfBoard) || (posY < 0) || (posY >= sizeOfBoard)) {  // Collision with borders
                alive = false;
            } else {
                for (int i = 1; i < length; i++) {
                    if (playerSprite.get(i).getLocation().x == playerSprite.get(0).getLocation().x) {
                        if (playerSprite.get(i).getLocation().y == playerSprite.get(0).getLocation().y) {   // Collsion with another part of body
                            alive = false;
                        }
                    }
                }

                if (alive) {
                    for (int i = length - 1; i >= 1; i--) {
                        playerSprite.get(i).setLocation(playerSprite.get(i - 1).getLocation());   // Changing location of body
                        //System.out.println(playerSprite.get(i).getLocation().x);
                    }
                    playerSprite.get(0).setLocation(posX*sizeOfBox + (sizeOfBox - sizeOfBody) / 2, posY*sizeOfBox + (sizeOfBox - sizeOfBody) / 2);   // Changing location of head
                }
            }
        }

        Random rand = new Random();  // Spawning apple at random location
        if ((posX == (apple.getLocation().x - (sizeOfBox - this.sizeOfBody) / 2) / sizeOfBody) && (posY ==  (apple.getLocation().y - (sizeOfBox - this.sizeOfBody) / 2) / sizeOfBody)) {
            growSnake();
            int randX = rand.nextInt(sizeOfBoard) * sizeOfBody + (sizeOfBox - this.sizeOfBody) / 2;
            int randY = rand.nextInt(sizeOfBoard) * sizeOfBody + (sizeOfBox - this.sizeOfBody) / 2;
            for (int i = length - 1; i >= 0; i--) {
                int X = playerSprite.get(i).getLocation().x;
                int Y = playerSprite.get(i).getLocation().y;
                if ((X == randX) && (Y == randY)) {  // Checking if apple can be spawned
                    i = length;
                    randX = rand.nextInt(sizeOfBoard) * sizeOfBody + (sizeOfBox - this.sizeOfBody) / 2;
                    randY = rand.nextInt(sizeOfBoard) * sizeOfBody + (sizeOfBox - this.sizeOfBody) / 2;
                }
            }
            apple.setLocation(randX, randY);  // If location isn't occupied, spawn apple
        }
    }
    public void growSnake() {                      // Increasing size length and reducing time between moves.
        length++;
        if (Gameplay.waitTime > 50) {
            Gameplay.waitTime -= 1;
        }
    }
    public void spawnNode(JFrame Frame) {       // Spawning elements of snake body
        JPanel node = new JPanel();
        node.setBackground(Color.DARK_GRAY);
        node.setVisible(true);
        node.setLocation(-100, -100);
        node.setSize(sizeOfBody, sizeOfBody);
        playerSprite.add(node);
        Frame.add(node);
    }

}
