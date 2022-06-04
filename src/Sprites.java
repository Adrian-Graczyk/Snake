import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Klasa Sprites przechowująca obrazy wykorzystwyane w grze
 */
public class Sprites {
    /**
     * Obraz tła gry
     */
    public static ImageIcon background = new ImageIcon("Images/Background3.png");
    /**
     * Obraz tła planszy przed właczneniem gry
     */
    public static ImageIcon startBackground = new ImageIcon("Images/StartBackground.jpg");
    /**
     * Obraz loga Snake
     */
    public static ImageIcon logoImage = new ImageIcon("Images/Logo.png");
    /**
     * Obraz tła planszy po zakończeniu gry
     */
    public static ImageIcon gameOverBackground = new ImageIcon("Images/GameOverBackground.jpg");
    /**
     * Obraz Game Over
     */
    public static ImageIcon gameOverLabel = new ImageIcon("Images/GameOver.png");

    /**
     * Obraz z polem do wpisania nazwy
     */
    public static ImageIcon startSnakeImage = new ImageIcon("Images/StartSnake.png");
    /**
     * Obraz uzywany na planszy po zakończeniu gry
     */
    public static ImageIcon sadSnakeImage = new ImageIcon("Images/SadSnake.png");
    /**
     * Obraz uzywany na planszy po zakończeniu gry
     */
    public static ImageIcon sadSnakeImage2 = new ImageIcon("Images/SadSnake2.png");

    /**
     * Obraz jabłka
     */
    public static ImageIcon appleImage = new ImageIcon("Images/apple.png");
    /**
     * Obraz kamieni
     */
    public static ImageIcon rocksImage = new ImageIcon("Images/Rocks.png");

    /**
     * Obraz myszy w górę
     */
    public static ImageIcon mouseImageUP = new ImageIcon("Images/mouseUP.png");
    /**
     * Obraz myszy w dół
     */
    public static ImageIcon mouseImageDOWN = new ImageIcon("Images/mouseDOWN.png");
    /**
     * Obraz myszy w lewo
     */
    public static ImageIcon mouseImageLEFT = new ImageIcon("Images/mouseLEFT.png");
    /**
     * Obraz myszy w prawo
     */
    public static ImageIcon mouseImageRIGHT = new ImageIcon("Images/mouseRIGHT.png");

    /**
     * Obraz głowy czerwonego Snake w górę
     */
    public static ImageIcon headImageUP = new ImageIcon("Images/redHeadUP.png");
    /**
     * Obraz głowy czerwonego Snake w dół
     */
    public static ImageIcon headImageDOWN = new ImageIcon("Images/redHeadDOWN.png");
    /**
     * Obraz głowy czerwonego Snake w lewo
     */
    public static ImageIcon headImageLEFT = new ImageIcon("Images/redHeadLEFT.png");
    /**
     * Obraz głowy czerwonego Snake w prawo
     */
    public static ImageIcon headImageRIGHT = new ImageIcon("Images/redHeadRIGHT.png");
    /**
     * Obraz ciała czerwonego Snake we współrzędnej X
     */
    public static ImageIcon bodyImageX = new ImageIcon("Images/redBodyX.png");
    /**
     * Obraz ciała czerwonego Snake we współrzędnej Y
     */
    public static ImageIcon bodyImageY = new ImageIcon("Images/redBodyY.png");
    /**
     * Obraz ogona czerwonego Snake w górę
     */
    public static ImageIcon tailImageUP = new ImageIcon("Images/redTailUP.png");
    /**
     * Obraz ogona czerwonego Snake w dół
     */
    public static ImageIcon tailImageDOWN = new ImageIcon("Images/redTailDOWN.png");
    /**
     * Obraz ogona czerwonego Snake w lewo
     */
    public static ImageIcon tailImageLEFT = new ImageIcon("Images/redTailLEFT.png");
    /**
     * Obraz ogona czerwonego Snake w prawo
     */
    public static ImageIcon tailImageRIGHT = new ImageIcon("Images/redTailRIGHT.png");
    /**
     * Obraz ciała czerwonego Snake z góry w lewo
     */
    public static ImageIcon turnUP_LEFT = new ImageIcon("Images/redBodyUP_LEFT.png");
    /**
     * Obraz ciała czerwonego Snake  z góry w prawo
     */
    public static ImageIcon turnUP_RIGHT = new ImageIcon("Images/redBodyUP_RIGHT.png");
    /**
     * Obraz ciała czerwonego Snake z dołu w lewo
     */
    public static ImageIcon turnDOWN_LEFT = new ImageIcon("Images/redBodyDOWN_LEFT.png");
    /**
     * Obraz ciała czerwonego Snake z dołu w prawo
     */
    public static ImageIcon turnDOWN_RIGHT = new ImageIcon("Images/redBodyDOWN_RIGHT.png");

    /**
     * Obraz głowy niebieskiego Snake w górę
     */
    public static ImageIcon AIheadImageUP = new ImageIcon("Images/blueHeadUP.png");
    /**
     * Obraz głowy niebieskiego Snake w dół
     */
    public static ImageIcon AIheadImageDOWN = new ImageIcon("Images/blueHeadDOWN.png");
    /**
     * Obraz głowy niebieskiego Snake w lewo
     */
    public static ImageIcon AIheadImageLEFT = new ImageIcon("Images/blueHeadLEFT.png");
    /**
     * Obraz głowy niebieskiego Snake w prawo
     */
    public static ImageIcon AIheadImageRIGHT = new ImageIcon("Images/blueHeadRIGHT.png");
    /**
     * Obraz ciała niebieskiego Snake we współrzędnej X
     */
    public static ImageIcon AIbodyImageX = new ImageIcon("Images/blueBodyX.png");
    /**
     * Obraz ciała niebieskiego Snake we współrzędnej Y
     */
    public static ImageIcon AIbodyImageY = new ImageIcon("Images/blueBodyY.png");
    /**
     * Obraz ogona niebieskiego Snake w górę
     */
    public static ImageIcon AItailImageUP = new ImageIcon("Images/blueTailUP.png");
    /**
     * Obraz ogona niebieskiego Snake w dół
     */
    public static ImageIcon AItailImageDOWN = new ImageIcon("Images/blueTailDOWN.png");
    /**
     * Obraz ogona niebieskiego Snake w lewo
     */
    public static ImageIcon AItailImageLEFT = new ImageIcon("Images/blueTailLEFT.png");
    /**
     * Obraz ogona niebieskiego Snake w prawo
     */
    public static ImageIcon AItailImageRIGHT = new ImageIcon("Images/blueTailRIGHT.png");
    /**
     * Obraz ciała niebieskiego Snake z góry w lewo
     */
    public static ImageIcon AIturnUP_LEFT = new ImageIcon("Images/blueBodyUP_LEFT.png");
    /**
     * Obraz ciała niebieskiego Snake z góry w prawo
     */
    public static ImageIcon AIturnUP_RIGHT = new ImageIcon("Images/blueBodyUP_RIGHT.png");
    /**
     * Obraz ciała niebieskiego Snake z dołu w lewo
     */
    public static ImageIcon AIturnDOWN_LEFT = new ImageIcon("Images/blueBodyDOWN_LEFT.png");
    /**
     * Obraz ciała niebieskiego Snake z dołu w prawo
     */
    public static ImageIcon AIturnDOWN_RIGHT = new ImageIcon("Images/blueBodyDOWN_RIGHT.png");

    /**
     * Metoda uakatualniająca obraz
     */
    public static ImageObserver observer = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    };
}
