import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Board extends Sprites{
    public static final int FIELD_X = 50;
    public static final int FIELD_Y = 50;
    public static final int SIZE = 20;
    public static final int MAX_X = FIELD_X * SIZE;
    public static final int MAX_Y = FIELD_Y * SIZE;

    public static void draw(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, MAX_X, MAX_Y, observer);
    }

}
