import java.awt.*;

/**
 * Klasa odpwoiadająca za pole do gry
 */
public class GameBoard extends Board{
    /**
     * Zmienna określająca rozmiar na osi X
     */
    public static final int FIELD_X = 50;
    /**
     * Zmienna określająca rozmiar na osi Y
     */
    public static final int FIELD_Y = 50;
    /**
     * Zmienna określająca rozmiar planszy
     */
    public static final int SIZE = 20;
    /**
     * Zmienna określająca maksymalny rozmiar planszy na osi X
     */
    public static final int MAX_X = FIELD_X * SIZE;
    /**
     * Zmienna określająca maksymalny rozmiar planszy na osi Y
     */
    public static final int MAX_Y = FIELD_Y * SIZE;

    /**
     * Metoda wyświetlająca tło gry
     * @param g
     */
    public static void draw(Graphics g) {
        g.drawImage(background.getImage(), 0, 0, MAX_X, MAX_Y, observer);
    }

}
