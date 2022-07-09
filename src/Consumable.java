import javax.swing.*;

public class Consumable {
    int posX, posY, size;
    enum type {APPLE, FREEZE, SHRINK, WATERMELON}
    JPanel body = new JPanel();
    type powerup;
    public Consumable(int X, int Y, int dimensions, type kind) {
        posX = X;
        posY = Y;
        size = dimensions;
        powerup = kind;
        body.setSize(dimensions, dimensions);
        body.setLocation(Gameplay.sizeOfBox * posX + (Gameplay.sizeOfBox - dimensions) / 2, Gameplay.sizeOfBox * posY + (Gameplay.sizeOfBox - dimensions) / 2);
        if (powerup == type.APPLE) {
            body.setBackground(new java.awt.Color(255, 21, 0)); // Spawning first apple
        } else if (powerup == type.SHRINK) {
            body.setBackground(new java.awt.Color(255, 242, 0)); // Spawning first apple
        } else if (powerup == type.FREEZE) {
            body.setBackground(new java.awt.Color(0, 255, 217)); // Spawning first apple
        } else if (powerup == type.WATERMELON) {
            body.setBackground(new java.awt.Color(227, 1, 115)); // Spawning first apple
        }
    }
}
