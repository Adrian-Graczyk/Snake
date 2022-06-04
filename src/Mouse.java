
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Klasa mysz dziedzicząca po klasie Sprites
 */
public class Mouse extends Sprites{
    /**
     * Deklaracja zmiennej przechowującej współrzedną X
     */
    private int x;
    /**
     * Deklaracja zmiennej przechowującej współrzedną Y
     */
    private int y;
    /**
     * Deklaracja zmiennej przechowującej kierunek
     */
    private Direction direction;
    /**
     * Deklaracja zmiennej przechowującej opóźnienie
     */
    private int movementDelay;

    /**
     * Konstruktor klasy Mouse ustawiający kierunek poruszania oraz współrzędne startu myszy
     */
    public Mouse(){
        x=40;
        y=40;
        direction = Direction.UP;
        movementDelay=0;
    }

    /**
     * Funkcja rysujaca mysz w róznych kierunkach
     * @param g
     */
    public void draw(Graphics g) {
        switch (direction) {
            case UP -> g.drawImage(mouseImageUP.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(mouseImageDOWN.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(mouseImageLEFT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(mouseImageRIGHT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    /**
     * Metoda odpowiadająca za uciekanie myszy przed wężami oraz
     * unikanie końca planszy
     * @param snake obiekt Snake
     * @param computersnake obiekt ComputerSnake
     */
    public void choose_direction(Snake snake, ComputerSnake computersnake)
    {
        var shead = snake.getHead();
        var cshead = computersnake.getHead();
        var sdirection = snake.getDirection();
        var csdirection = computersnake.getDirection();
        var csdistance = sqrt((pow((getX()-cshead.x),2)+pow((getY()-cshead.y),2)));
        var sdistance = sqrt((pow((getX()-shead.x),2)+pow((getY()-shead.y),2)));
        if(sdistance >= csdistance && getX() > 0 && getY() > 0 && getX() < ((Board.FIELD_X - 1)) && getY() < ((Board.FIELD_Y - 1)))
        {
            if(csdirection == getDirection()) {
                if (getDirection() != Direction.LEFT) {
                    this.setDirection(Direction.RIGHT);
                } else if (getDirection() != Direction.DOWN) {
                    this.setDirection(Direction.UP);
                } else if (getDirection() != Direction.RIGHT) {
                    this.setDirection(Direction.LEFT);
                } else if (getDirection() != Direction.UP) {
                    this.setDirection(Direction.DOWN);
                }
            }
        }
        if(sdistance <= csdistance && getX() > 0 && getY() > 0 && getX() < ((Board.FIELD_X - 1)) && getY() < ((Board.FIELD_Y - 1)))
        {
            if(sdirection == getDirection()) {
                if (getDirection() != Direction.LEFT) {
                    this.setDirection(Direction.RIGHT);
                } else if (getDirection() != Direction.DOWN) {
                    this.setDirection(Direction.UP);
                } else if (getDirection() != Direction.RIGHT) {
                    this.setDirection(Direction.LEFT);
                } else if (getDirection() != Direction.UP) {
                    this.setDirection(Direction.DOWN);
                }
            }
        }
        else if (getX() == 0 && getY() == ((Board.FIELD_Y - 1))) {
            this.setDirection(Direction.RIGHT);
        }
        else if (getX() == 0) {
            if(getDirection() == Direction.DOWN) {
                this.setDirection(Direction.RIGHT);
            }
            else {
                this.setDirection(Direction.DOWN);
            }
        }
        else if (getY() == Board.FIELD_Y - 1 && getX() == ((Board.FIELD_X - 1))) {
            this.setDirection(Direction.UP);
        }
        else if (getY() == Board.FIELD_Y - 1) {
            if(getDirection() == Direction.RIGHT) {
                this.setDirection(Direction.UP);
            }
            else {
                this.setDirection(Direction.RIGHT);
            }
        }
        else if (getY() == 0) {
            if(getDirection() == Direction.LEFT) {
                this.setDirection(Direction.DOWN);
            }
            else {
                this.setDirection(Direction.LEFT);
            }
        }
        else if (getX() == (Board.FIELD_X - 1)) {
            if(getDirection() == Direction.UP) {
                this.setDirection(Direction.LEFT);
            }
            else {
                this.setDirection(Direction.UP);
            }
        }
    }

    /**
     * Mertoda odpowiada za ruch myszy
     * @param snake obiekt Snake
     * @param computersnake obiekt ComputerSnake
     */
    public void move(Snake snake, ComputerSnake computersnake) {

        choose_direction(snake, computersnake);
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }

    /**
     * Metoda odpowiada za ustawienie współrzędnej X na wartość przekazaną w parametrze
     * @param x wartość na którą usatwiamy współrzędną X
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Metoda odpowiada za ustawienie współrzędnej Y na wartość przekazaną w parametrze
     * @param y wartość na którą usatwiamy współrzędną Y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Metoda odpowiada za ustawienie kierunku poruszania się na wartość przekazaną w parametrze
     * @param direction kierunek w którym ustawimay poruszanie się myszy
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Metoda odpowiada za pobranie wartości współrzędnej X w której znajduje się mysz
     * @return współrzędna X myszy
     */
    public int getX() {
        return x;
    }
    /**
     * Metoda odpowiada za pobranie wartości współrzędnej Y w której znajduje się mysz
     * @return współrzędna Y myszy
     */
    public int getY() {
        return y;
    }
    /**
     * Metoda odpowiada za pobranie kierunku w którym porusza się się mysz
     * @return kierunek myszy
     */
    public Direction getDirection() {
        return direction;
    }

    public void setMovementDelay(int movementDelay) { this.movementDelay = movementDelay; }

    public int getMovementDelay() {return movementDelay; }

}
