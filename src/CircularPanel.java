import javax.swing.*;
import java.awt.*;

public class CircularPanel extends JPanel {
    public Color CLR;
    public int arcW, arcH;

    public CircularPanel(Color C, int w, int h) {
        CLR = C;
        arcH = h;
        arcW = w;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(CLR);
        g.fillRoundRect(0, 0, Player.sizeOfBody, Player.sizeOfBody, arcW, arcH);
    }
}
