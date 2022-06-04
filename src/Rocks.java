import java.awt.*;

/**
 * Klasa Rocks dziedziczy po Sprites, odpowiada za kamienie (przeszkody) na planszy
 */
public class Rocks extends Sprites{
    /**
     * Inicjalizacja zmiennej odpowiadającej za wielkość kamieni,
     * w współrzędnej Y
     */
    private final int sizeY = 4;
    /**
     * Inicjalizacja zmiennej odpowiadającej za wielkość kamieni,
     * w współrzędnej X
     */
    private final int sizeX = 16;
    /**
     * Inicjalizacja tablicy dwuwymairowej typu Point przechowującej punkty w których będą kamienie
     */
    private Point[][] rocks = new Point[sizeX][sizeY];

    /**
     * Metoda usatwiająca współrzędne kamieni w tablicy
     */
    public Rocks() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                rocks[i][j] = new Point(i+16,j+20);
            }
        }
    }

    /**
     * Metoda rysująca kamienie na planszy
     * @param g
     */
    public void draw(Graphics g) {
        for (int i = 0; i < sizeX; i=i+4) {
            for (int j = 0; j < sizeY; j=j+4) {
                g.drawImage(rocksImage.getImage(),rocks[i][j].x * Board.SIZE, rocks[i][j].y * Board.SIZE, Board.SIZE*4, Board.SIZE*4, observer);
            }
        }
    }

    /**
     * Metoda zwracająca tablice kamieni
     * @return tablica punktów w których są kamienie
     */
    public Point[][] getRocks() {
        return rocks;
    }

    /**
     * Metoda zwraca rozmiar na osi Y
     * @return rozmiar kamieni na osi Y
     */
    public int getSizeY() {
        return sizeY;
    }
    /**
     * Metoda zwraca rozmiar na osi X
     * @return rozmiar kamieni na osi X
     */
    public int getSizeX() {
        return sizeX;
    }

}
