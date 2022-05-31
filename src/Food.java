import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Food extends Sprites{
    private Point[] apples = new Point[10];

    private int iterationDelay;

    public Food() {
        for (int i = 0; i < apples.length; i++) {
            apples[i] = new Point(-1, -1);
        }
        iterationDelay = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        for (Point point : getApples()) {
            g.drawImage(appleImage.getImage(),point.x * Board.SIZE, point.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    public Point[] getApples() {
        return apples;
    }

    public int getIterationDelay() {
        return iterationDelay;
    }

    public void setIterationDelay(int iterationDelay) {
        this.iterationDelay = iterationDelay;
    }
}
