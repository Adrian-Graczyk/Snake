import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Sprites {

    public static ImageIcon background = new ImageIcon("Images/Background3.png");
    public static ImageIcon gameOverBackground = new ImageIcon("Images/GameOverBackground.jpg");
    public static ImageIcon gameOverLabel = new ImageIcon("Images/GameOver.png");

    public static ImageIcon sadSnakeImage = new ImageIcon("Images/SadSnake.png");
    public static ImageIcon sadSnakeImage2 = new ImageIcon("Images/SadSnake2.png");

    public static ImageIcon appleImage = new ImageIcon("Images/apple.png");
    public static ImageIcon headImageUP = new ImageIcon("Images/redHeadUP.png");
    public static ImageIcon headImageDOWN = new ImageIcon("Images/redHeadDOWN.png");
    public static ImageIcon headImageLEFT = new ImageIcon("Images/redHeadLEFT.png");
    public static ImageIcon headImageRIGHT = new ImageIcon("Images/redHeadRIGHT.png");
    public static ImageIcon bodyImageX = new ImageIcon("Images/redBodyX.png");
    public static ImageIcon bodyImageY = new ImageIcon("Images/redBodyY.png");
    public static ImageIcon tailImageUP = new ImageIcon("Images/redTailUP.png");
    public static ImageIcon tailImageDOWN = new ImageIcon("Images/redTailDOWN.png");
    public static ImageIcon tailImageLEFT = new ImageIcon("Images/redTailLEFT.png");
    public static ImageIcon tailImageRIGHT = new ImageIcon("Images/redTailRIGHT.png");
    public static ImageIcon turnUP_LEFT = new ImageIcon("Images/redBodyUP_LEFT.png");
    public static ImageIcon turnUP_RIGHT = new ImageIcon("Images/redBodyUP_RIGHT.png");
    public static ImageIcon turnDOWN_LEFT = new ImageIcon("Images/redBodyDOWN_LEFT.png");
    public static ImageIcon turnDOWN_RIGHT = new ImageIcon("Images/redBodyDOWN_RIGHT.png");

    public static ImageObserver observer = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    };
}
