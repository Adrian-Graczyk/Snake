import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Klasa Food dziedziczy po klasie Sprites oraz odpowiada za jabłka pojawiające sie na planszy
 */
public class Food extends Sprites{
    /**
     * Zmienna przechowująca tablice punktów w których będa przecgowywane współrzędne jabłek
     */
    private Point[] apples = new Point[10];
    /**
     * Zmienna przechowuje wartość opóźnienia z jakim będą generowane jabłka
     */
    private int iterationDelay;

    /**
     * Konstruktor ustawiający wszystkie jabłka w pozycji niewidocznej na planszy oraz ustawienie opóźnienia na 0
     */
    public Food() {
        for (int i = 0; i < apples.length; i++) {
            apples[i] = new Point(-1, -1);
        }
        iterationDelay = 0;
    }

    /**
     * Metoda rysująca jabłka na planszy
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        for (Point point : getApples()) {
            g.drawImage(appleImage.getImage(),point.x * Board.SIZE, point.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    /**
     * Metoda zwracająca tablicę punktów w których są jabłka
     * @return tablica punktów
     */
    public Point[] getApples() {
        return apples;
    }

    /**
     * Metoda zwraca wartość opóżnienia
     * @return opóźnienie jako int
     */
    public int getIterationDelay() {
        return iterationDelay;
    }

    /**
     * Zmienna ustawiająca delay na wartość przekazaną jej
     * @param iterationDelay wartość na którą opóżnienie ma zostać usawtione
     */
    public void setIterationDelay(int iterationDelay) {
        this.iterationDelay = iterationDelay;
    }
}
