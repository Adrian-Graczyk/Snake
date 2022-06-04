import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Klasa Sprites zawiera zmienne definiujące rozmiar planszy do gry oraz rozmiar okna
 */
public class Board extends Sprites{
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
}
