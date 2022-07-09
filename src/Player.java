import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    boolean alive = false;
    int posX, posY, direction, length;
    static int score = 0;
    int sizeOfBoard = Gameplay.sizeOfBoard;
    int sizeOfBody = 30;
    int waitTime = Gameplay.waitTime;
    int sizeOfBox = Gameplay.sizeOfBox;
    static ArrayList<JPanel> playerSprite = new ArrayList<JPanel>();
    static JPanel apple = new JPanel(), shrink = new JPanel(), slowDown = new JPanel(), watermelon = new JPanel();
    static JLabel ScoreLine = new JLabel("Score: 0");

    int appleX = 3, appleY = 3, shrinkX = -5, shrinkY = -5, slowDownX = -5, slowDownY = -5, watermelonX = -5, watermelonY = -5;
    String playerName;
    public Player(String name, JFrame Frame) {  // Initialization of Snake
        playerName = name;  // Setting parameters of Snake
        direction = 0;
        length = 1;
        posX = sizeOfBoard / 2;
        posY = sizeOfBoard / 2;
        playerSprite.add(new JPanel()); // Spawning head of Snake
        playerSprite.get(0).setBackground(new java.awt.Color(74, 0, 147));
        playerSprite.get(0).setLocation(sizeOfBox*posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox*posY + (sizeOfBox - sizeOfBody) / 2);
        playerSprite.get(0).setSize(sizeOfBody, sizeOfBody);
        Frame.add(playerSprite.get(0));
        for (int i = 0; i < sizeOfBoard * sizeOfBoard; i++) spawnNode(Frame); // Spawning multiple nodes at the beginning

        apple.setBackground(new java.awt.Color(255, 21, 0)); // Spawning first apple
        apple.setSize(sizeOfBody, sizeOfBody);
        apple.setLocation(sizeOfBox * appleX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * appleY + (sizeOfBox - sizeOfBody) / 2);
        Frame.add(apple);

        shrink.setBackground(new java.awt.Color(255, 242, 0)); // Spawning first apple
        shrink.setSize(sizeOfBody, sizeOfBody);
        shrink.setLocation(-100, -100);
        Frame.add(shrink);

        slowDown.setBackground(new java.awt.Color(0, 255, 217)); // Spawning first apple
        slowDown.setSize(sizeOfBody, sizeOfBody);
        slowDown.setLocation(-100, -100);
        Frame.add(slowDown);

        watermelon.setBackground(new java.awt.Color(227, 1, 115)); // Spawning first apple
        watermelon.setSize(sizeOfBody, sizeOfBody);
        watermelon.setLocation(-100, -100);
        Frame.add(watermelon);

        Frame.add(ScoreLine);
        ScoreLine.setLocation(sizeOfBoard * sizeOfBox - 120, 15);
        ScoreLine.setSize(100, 10);
    }

    public void movePlayer(JFrame Frame) {       // Making moves based on input, checking if collision occurs or if apple is eaten
        ScoreLine.setText("Score: " + score);

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
                if (posX < 0) posX = sizeOfBoard - 1;
                if (posY < 0) posY = sizeOfBoard - 1;
                if (posX == sizeOfBoard) posX = 0;
                if (posY == sizeOfBoard) posY = 0;
            }


                for (int i = 1; i < length; i++) {
                    if (playerSprite.get(i).getLocation().x == playerSprite.get(0).getLocation().x) {
                        if (playerSprite.get(i).getLocation().y == playerSprite.get(0).getLocation().y) {   // Collsion with another part of body
                            alive = false;
                            Frame.setTitle("Press R to restart!");
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


        Random rand = new Random();  // Spawning apple at random location
        if ((posX == appleX) && (posY == appleY)) {
            growSnake();
            appleX = rand.nextInt(sizeOfBoard);
            appleY = rand.nextInt(sizeOfBoard);
            for (int i = length - 1; i >= 0; i--) {
                int X = (playerSprite.get(i).getLocation().x - (sizeOfBox - sizeOfBody) / 2) / sizeOfBody;
                int Y = (playerSprite.get(i).getLocation().y - (sizeOfBox - sizeOfBody) / 2) / sizeOfBody;
                if ((X == appleX) && (Y == appleY)) {  // Checking if apple can be spawned
                    i = length;
                    appleX = rand.nextInt(sizeOfBoard);
                    appleY = rand.nextInt(sizeOfBoard);
                }
            }
            apple.setLocation(sizeOfBox * appleX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * appleY + (sizeOfBox - sizeOfBody) / 2);
            if ((appleX + appleY) % 20 == 0) {
                shrinkX = rand.nextInt(sizeOfBoard);
                shrinkY = rand.nextInt(sizeOfBoard);
                shrink.setLocation(sizeOfBox * shrinkX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * shrinkY + (sizeOfBox - sizeOfBody) / 2);
            }

            if ((appleX + appleY + 5) % 20 == 0) {
                slowDownX = rand.nextInt(sizeOfBoard);
                slowDownY = rand.nextInt(sizeOfBoard);
                slowDown.setLocation(sizeOfBox * slowDownX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * slowDownY + (sizeOfBox - sizeOfBody) / 2);
            }

            if ((appleX + appleY + 3) % 12 == 0) {
                watermelonX = rand.nextInt(sizeOfBoard);
                watermelonY = rand.nextInt(sizeOfBoard);
                watermelon.setLocation(sizeOfBox * watermelonX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * watermelonY + (sizeOfBox - sizeOfBody) / 2);
            }
        }

        if ((posX == shrinkX) && (posY == shrinkY)) {
            for (int i = length - 1; i >= length / 2; i--) {
                playerSprite.get(i).setLocation(-100, -100);   // Changing location of body
                //System.out.println(playerSprite.get(i).getLocation().x);
            }
            length /= 2;
            shrink.setLocation(-100, -100);
            shrinkX = -5;
            shrinkY = -5;
        }

        if ((posX == slowDownX) && (posY == slowDownY)) {
            Gameplay.waitTime += 15;
            direction = 0;
            slowDown.setLocation(-100, -100);
            slowDownX = -5;
            slowDownY = -5;
        }

        if ((posX == watermelonX) && (posY == watermelonY)) {
            growSnake(); growSnake(); growSnake(); growSnake(); growSnake();
            watermelon.setLocation(-100, -100);
            watermelonX = -5;
            watermelonY = -5;
        }

    }
    public void growSnake() {                      // Increasing size length and reducing time between moves.
        length++;
        score++;
        if (Gameplay.waitTime > 50) {
            Gameplay.waitTime -= 1;
        }
    }
    public void spawnNode(JFrame Frame) {       // Spawning elements of snake body
        JPanel node = new JPanel();
        node.setBackground(new java.awt.Color(0, 63, 178));
        node.setVisible(true);
        node.setLocation(-100, -100);
        node.setSize(sizeOfBody, sizeOfBody);
        playerSprite.add(node);
        Frame.add(node);
    }

    public void restart(JFrame Frame) {
        for (int i = length - 1; i >= 1; i--) {
            playerSprite.get(i).setLocation(-100, -100);   // Changing location of body
            //System.out.println(playerSprite.get(i).getLocation().x);
        }
        posX = sizeOfBoard / 2;
        posY = sizeOfBoard / 2;
        slowDownX = slowDownY = watermelonY = watermelonX = shrinkX = shrinkY = -5;
        watermelon.setLocation(-100, -100);
        shrink.setLocation(-100, -100);
        slowDown.setLocation(-100, -100);

        playerSprite.get(0).setLocation(posX * sizeOfBox, posY * sizeOfBox);
        length = 1;
        direction = 0;
        score = 0;
        Gameplay.waitTime = 120;
        alive = true;
        Frame.setTitle("Snake!");
    }

}
