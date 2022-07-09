import java.awt.*;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Board {
    public int boardWidth, boardHeight;
    int sizeOfBox = Gameplay.sizeOfBox;
    public Board(int width, int height) {
        boardHeight = height;
        boardWidth = width;
    }

    public void fillGameBoard(JFrame Frame) {
        int width = boardWidth;
        int height = boardHeight;
        Component C = Frame.getContentPane();
        C.setBackground(Color.RED);
        Frame.setSize(sizeOfBox*width + 15, sizeOfBox*height + 35);
        Frame.setLayout(null);
        JPanel[][] Square = new JPanel[width][height];

        boolean red = true;
        for (int i = 0; i < width; i++) {  // Filling gameboard with green and lightgreen squares
            for (int j = 0; j < height; j++) {
                Square[i][j] = new JPanel();
                Square[i][j].setLocation(i * sizeOfBox, j * sizeOfBox);
                Square[i][j].setSize(sizeOfBox, sizeOfBox);
                if (red) {
                    Square[i][j].setBackground(new java.awt.Color(60,189,113));
                    red = false;
                } else {
                    Square[i][j].setBackground(new java.awt.Color(60,199,113));
                    red = true;
                }
                Frame.add(Square[i][j]);
            }
        }
    }
}
