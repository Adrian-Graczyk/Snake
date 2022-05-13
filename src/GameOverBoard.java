import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameOverBoard {
    public static final int FIELD_X = 50;
    public static final int FIELD_Y = 50;
    public static final int SIZE = 20;
    public static final int MAX_X = FIELD_X * SIZE;
    public static final int MAX_Y = FIELD_Y * SIZE;
    public static ImageIcon image = new ImageIcon("Images/Background2.jpg");

    public static ImageObserver observer = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    };

    public static void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MAX_X, MAX_Y);
    }

}
