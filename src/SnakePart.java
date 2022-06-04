/**
 * Klasa SnakePart tworząca obiekty które są fragmentami ciała Sanke'a
 */
public class SnakePart {
    /**
     * Deklaracja współrzędnej X punktu w którym znajduje się fragment ciała
     */
    public int x;
    /**
     * Deklaracja współrzędnej Y punktu w którym znajduje się fragment ciała
     */
    public int y;
    /**
     * Deklaracja kierunku w którym porusza się fragment ciała
     */
    public Direction direction;

    /**
     * Konstruktor parametryczny inicjalizujący fragment ciała
     * @param x współrzędna X w której ma być fragment ciała
     * @param y współrzędna Y w której ma być fragment ciała
     * @param direction kierunek w którym ma być fragment ciała
     */
    public SnakePart(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
