import javax.swing.*;
import java.awt.*;

public class Consumable {
    int posX, posY, size;
    enum type {APPLE, FREEZE, SHRINK, WATERMELON}
    JPanel body;
    type powerup;

    public Consumable(int X, int Y, int dimensions, type kind) {
        posX = X;
        posY = Y;
        size = dimensions;
        powerup = kind;
        if (powerup == type.APPLE) {
            body = new CircularPanel(new java.awt.Color(255, 21, 0), 50, 50);
            body.setBackground(new java.awt.Color(255, 21, 0, 0));
            body.setSize(dimensions, dimensions);
            body.setLocation(Gameplay.sizeOfBox * posX + (Gameplay.sizeOfBox - dimensions) / 2, Gameplay.sizeOfBox * posY + (Gameplay.sizeOfBox - dimensions) / 2);
        } else if (powerup == type.SHRINK) {
            body = new CircularPanel(new java.awt.Color(255, 242, 0), 50, 50);
            body.setBackground(new java.awt.Color(255, 21, 0, 0));
            body.setSize(dimensions, dimensions);
            body.setLocation(Gameplay.sizeOfBox * posX + (Gameplay.sizeOfBox - dimensions) / 2, Gameplay.sizeOfBox * posY + (Gameplay.sizeOfBox - dimensions) / 2);
        } else if (powerup == type.FREEZE) {
            body = new CircularPanel(new java.awt.Color(0, 255, 217), 50, 50);
            body.setBackground(new java.awt.Color(255, 21, 0, 0));
            body.setSize(dimensions, dimensions);
            body.setLocation(Gameplay.sizeOfBox * posX + (Gameplay.sizeOfBox - dimensions) / 2, Gameplay.sizeOfBox * posY + (Gameplay.sizeOfBox - dimensions) / 2);
        } else if (powerup == type.WATERMELON) {
            body = new CircularPanel(new java.awt.Color(227, 1, 115), 50, 50);
            body.setBackground(new java.awt.Color(255, 21, 0, 0));
            body.setSize(dimensions, dimensions);
            body.setLocation(Gameplay.sizeOfBox * posX + (Gameplay.sizeOfBox - dimensions) / 2, Gameplay.sizeOfBox * posY + (Gameplay.sizeOfBox - dimensions) / 2);
        }
    }


}
