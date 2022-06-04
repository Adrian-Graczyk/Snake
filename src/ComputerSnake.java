import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.sql.Types.NULL;

/**
 * Klasa ComputerSnake dziedzicząca po klasie Snake odpowiada za rysowanie i ruch snake'a sterowanego przez komputer
 */
public class ComputerSnake extends Snake {

    /**
     * Zmienna przechowująca współrzędne jabłka do którego aktualnie kieruje się wąż
     */
    private Point target = new Point(0, 0);

    /**
     * Konstruktor klasy ComputerSnake ustawiający kierunek oraz miejsce pojawienia się węża oraz inicujący jego ciało
     */
    public ComputerSnake() {
        direction = Direction.UP;
        body = new ArrayList<>();
        body.add(new SnakePart(20, 25, direction));
        body.add(new SnakePart(20, 26, direction));
    }

    /**
     * Metoda rysująca Snake'a sterowanego przez komputer
     * @param g
     */
    public void computer_draw(Graphics g) {
        var bodyTMP = getBody();
        for (int i = 0; i < bodyTMP.size(); i++) {
            var snakePart = bodyTMP.get(i);
            switch (snakePart.direction) {
                case UP:
                    if (body.get(i + 2).x > snakePart.x)
                        g.drawImage(AIturnUP_RIGHT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if (body.get(i + 2).x < snakePart.x)
                        g.drawImage(AIturnUP_LEFT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageY.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case DOWN:
                    if (body.get(i + 2).x > snakePart.x)
                        g.drawImage(AIturnDOWN_RIGHT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if (body.get(i + 2).x < snakePart.x)
                        g.drawImage(AIturnDOWN_LEFT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageY.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case LEFT:
                    if (body.get(i + 2).y > snakePart.y)
                        g.drawImage(AIturnDOWN_LEFT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if (body.get(i + 2).y < snakePart.y)
                        g.drawImage(AIturnUP_LEFT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageX.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case RIGHT:
                    if (body.get(i + 2).y > snakePart.y)
                        g.drawImage(AIturnDOWN_RIGHT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if (body.get(i + 2).y < snakePart.y)
                        g.drawImage(AIturnUP_RIGHT.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageX.getImage(), snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
            }
        }

        switch (direction) {
            case UP ->
                    g.drawImage(AIheadImageUP.getImage(), getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN ->
                    g.drawImage(AIheadImageDOWN.getImage(), getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT ->
                    g.drawImage(AIheadImageLEFT.getImage(), getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT ->
                    g.drawImage(AIheadImageRIGHT.getImage(), getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }

        switch (getTail().direction) {
            case UP ->
                    g.drawImage(AItailImageUP.getImage(), getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN ->
                    g.drawImage(AItailImageDOWN.getImage(), getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT ->
                    g.drawImage(AItailImageLEFT.getImage(), getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT ->
                    g.drawImage(AItailImageRIGHT.getImage(), getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    /**
     * Metoda usatwiająca jabłko do którego kieruje sie wąż
     * @param point parametr typu Point który przechowuje współrzedne wybranego jabłka
     */
    public void setTarget(Point point) {
        this.target = point;
    }

    /**
     * Metoda odpowiadająca za ruch komputera w kierunku jabłek,
     * unikanie końca planszy oraz przeszkód
     */
    public void choose_direction() {
        var head = getHead();
        if (head.x > 15 && head.x < 32 && head.y == 19 && this.getDirection() != Direction.RIGHT) {
            this.setDirection(Direction.LEFT);
            Point tmp = new Point(10, 40);
            this.setTarget(tmp);
        } else if (head.x > 15 && head.x < 32 && head.y == 24 && this.getDirection() != Direction.LEFT) {
            this.setDirection(Direction.RIGHT);
        } else if (head.y > 19 && head.y < 24 && head.x == 15) {
            int tmp = (int) (Math.random() * 2);
            if (tmp == 1 && this.getDirection() != Direction.UP) {
                if (head.x == 15) {
                    this.setDirection(Direction.UP);
                }
            } else {
                this.setDirection(Direction.DOWN);
            }
        } else if (head.y > 19 && head.y < 24 && head.x == 32) {
            int tmp = (int) (Math.random() * 2);
            if (tmp == 1 && this.getDirection() != Direction.UP) {
                if (head.x == 32)
                    this.setDirection(Direction.DOWN);
            } else {
                this.setDirection(Direction.UP);
            }
        } else if (head.x > 0 && head.y > 0 && head.x < ((Board.FIELD_X - 1)) && head.y < ((Board.FIELD_Y - 1))) {
            if (target.x > getHead().x && this.getDirection() != Direction.LEFT) {
                this.setDirection(Direction.RIGHT);
            } else if (target.x < getHead().x && this.getDirection() != Direction.RIGHT) {
                this.setDirection(Direction.LEFT);
            } else if (target.x == getHead().x && target.y > getHead().y && this.getDirection() != Direction.UP) {
                this.setDirection(Direction.DOWN);
            } else if (target.x == getHead().x && target.y < getHead().y && this.getDirection() != Direction.DOWN) {
                this.setDirection(Direction.UP);
            } else if (target.x == NULL && target.y == NULL && this.getDirection() != Direction.RIGHT) {
                this.setDirection(Direction.DOWN);
            }
        } else if (head.x == 0 && head.y == ((Board.FIELD_Y - 1))) {
            this.setDirection(Direction.RIGHT);
        } else if (head.x == 0) {
            if (getDirection() == Direction.DOWN) {
                this.setDirection(Direction.RIGHT);
            } else {
                this.setDirection(Direction.DOWN);
            }
        } else if (head.y == Board.FIELD_Y - 1 && head.x == ((Board.FIELD_X - 1))) {
            this.setDirection(Direction.UP);
        } else if (head.y == Board.FIELD_Y - 1) {
            if (getDirection() == Direction.RIGHT) {
                this.setDirection(Direction.UP);
            } else {
                this.setDirection(Direction.RIGHT);
            }
        } else if (head.y == 0) {
            if (getDirection() == Direction.LEFT) {
                this.setDirection(Direction.DOWN);
            } else {
                this.setDirection(Direction.LEFT);
            }
        } else if (head.x == (Board.FIELD_X - 1)) {
            if (getDirection() == Direction.UP) {
                this.setDirection(Direction.LEFT);
            } else {
                this.setDirection(Direction.UP);
            }
        }
    }

    /**
     * Metoda odpowiada za wybór kierunku w którym wąż jedzie oraz za przemieszczanie węża na planszy
     */
    public void computer_move() {

        choose_direction();
        getHead().direction = this.direction;
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
            body.get(i).direction = body.get(i - 1).direction;
        }

        switch (direction) {
            case UP -> getHead().y--;
            case DOWN -> getHead().y++;
            case LEFT -> getHead().x--;
            case RIGHT -> getHead().x++;
        }
    }
}
