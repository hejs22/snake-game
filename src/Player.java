import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    boolean alive = false, hard = false;
    int posX, posY, direction, length;
    static int score = 0;
    int sizeOfBoard = Gameplay.sizeOfBoard;
    static int sizeOfBody = 30;
    int sizeOfBox = Gameplay.sizeOfBox;
    static ArrayList<JPanel> playerSprite = new ArrayList<>();
    static Consumable apple = new Consumable(3, 3, sizeOfBody, Consumable.type.APPLE);
    static Consumable shrink = new Consumable(-5, -5, sizeOfBody, Consumable.type.SHRINK);
    static Consumable slowDown = new Consumable(-5, -5, sizeOfBody, Consumable.type.FREEZE);
    static Consumable watermelon = new Consumable(-5, -5, sizeOfBody, Consumable.type.WATERMELON);
    static JLabel ScoreLine = new JLabel("Score: 0");

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

        Frame.add(apple.body);
        Frame.add(shrink.body);
        Frame.add(slowDown.body);
        Frame.add(watermelon.body);

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
                if (hard) {
                    alive = false;
                    Frame.setTitle("Press R to restart!");
                } else {
                    if (posX < 0) posX = sizeOfBoard - 1;
                    if (posY < 0) posY = sizeOfBoard - 1;
                    if (posX == sizeOfBoard) posX = 0;
                    if (posY == sizeOfBoard) posY = 0;
                }
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
        if ((posX == apple.posX) && (posY == apple.posY)) {
            growSnake();
            apple.posX = rand.nextInt(sizeOfBoard);
            apple.posY = rand.nextInt(sizeOfBoard);
            for (int i = length - 1; i >= 0; i--) {
                int X = (playerSprite.get(i).getLocation().x - (sizeOfBox - sizeOfBody) / 2) / sizeOfBody;
                int Y = (playerSprite.get(i).getLocation().y - (sizeOfBox - sizeOfBody) / 2) / sizeOfBody;
                if ((X == apple.posX) && (Y == apple.posY)) {  // Checking if apple can be spawned
                    i = length;
                    apple.posX = rand.nextInt(sizeOfBoard);
                    apple.posY = rand.nextInt(sizeOfBoard);
                }
            }
            apple.body.setLocation(sizeOfBox * apple.posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * apple.posY + (sizeOfBox - sizeOfBody) / 2);
            if ((apple.posX + apple.posY) % 20 == 0) {
                shrink.posX = rand.nextInt(sizeOfBoard);
                shrink.posY = rand.nextInt(sizeOfBoard);
                shrink.body.setLocation(sizeOfBox * shrink.posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * shrink.posY + (sizeOfBox - sizeOfBody) / 2);
            }

            if ((apple.posX + apple.posY + 5) % 20 == 0) {
                slowDown.posX = rand.nextInt(sizeOfBoard);
                slowDown.posY = rand.nextInt(sizeOfBoard);
                slowDown.body.setLocation(sizeOfBox * slowDown.posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * slowDown.posY + (sizeOfBox - sizeOfBody) / 2);
            }

            if ((apple.posX + apple.posY + 3) % 12 == 0) {
                watermelon.posX = rand.nextInt(sizeOfBoard);
                watermelon.posY = rand.nextInt(sizeOfBoard);
                watermelon.body.setLocation(sizeOfBox * watermelon.posX + (sizeOfBox - sizeOfBody) / 2, sizeOfBox * watermelon.posY + (sizeOfBox - sizeOfBody) / 2);
            }
        }

        if ((posX == shrink.posX) && (posY == shrink.posY)) {
            for (int i = length - 1; i >= length / 2; i--) {
                playerSprite.get(i).setLocation(-100, -100);   // Changing location of body
                //System.out.println(playerSprite.get(i).getLocation().x);
            }
            length /= 2;
            shrink.body.setLocation(-100, -100);
            shrink.posX = -5;
            shrink.posY = -5;
        }

        if ((posX == slowDown.posX) && (posY == slowDown.posY)) {
            Gameplay.waitTime += 15;
            direction = 0;
            slowDown.body.setLocation(-100, -100);
            slowDown.posX = -5;
            slowDown.posY = -5;
        }

        if ((posX == watermelon.posX) && (posY == watermelon.posY)) {
            growSnake(); growSnake(); growSnake(); growSnake(); growSnake();
            watermelon.body.setLocation(-100, -100);
            watermelon.posX = -5;
            watermelon.posY = -5;
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
        slowDown.posX = slowDown.posY = watermelon.posY = watermelon.posX = shrink.posX = shrink.posY = -5;
        watermelon.body.setLocation(-100, -100);
        shrink.body.setLocation(-100, -100);
        slowDown.body.setLocation(-100, -100);

        playerSprite.get(0).setLocation(posX * sizeOfBox, posY * sizeOfBox);
        length = 1;
        direction = 0;
        score = 0;
        if (hard) {
            Gameplay.waitTime = 70;
        } else {
            Gameplay.waitTime = 120;
        }
        alive = true;
        Frame.setTitle("Snake!");
    }

}
