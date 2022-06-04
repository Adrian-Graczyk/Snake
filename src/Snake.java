import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Sanke dziedzicząca po Sprites
 */
public class Snake extends Sprites{
    /**
     * Lista części ciała Snake'a
     */
    protected List<SnakePart> body;
    /**
     * Kierunek poruszania się Snake'a
     */
    protected Direction direction;
    /**
     * Konstruktor klasy Snake ustawiający kierunek oraz miejsce pojawienia się węża oraz inicujący jego ciało
     */
    public Snake() {
        direction = Direction.RIGHT;
        body = new ArrayList<>(); 
        body.add(new SnakePart(5, 5, direction));
        body.add(new SnakePart(6, 5, direction));
    }
    /**
     * Metoda rysująca Snake'a sterowanego przez gracza
     * @param g
     */
    public void draw(Graphics g) {
         var BodyTMP = getBody();
        for ( int i = 0; i<BodyTMP.size(); i++) {
            var snakePart = BodyTMP.get(i);
            switch (snakePart.direction) {
                case UP:
                    if(body.get(i+2).x > snakePart.x)
                        g.drawImage(turnUP_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(body.get(i+2).x < snakePart.x)
                        g.drawImage(turnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case DOWN:
                    if(body.get(i+2).x > snakePart.x)
                        g.drawImage(turnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(body.get(i+2).x < snakePart.x)
                        g.drawImage(turnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case LEFT:
                    if(body.get(i+2).y > snakePart.y)
                        g.drawImage(turnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(body.get(i+2).y < snakePart.y)
                        g.drawImage(turnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageX.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case RIGHT:
                    if(body.get(i+2).y > snakePart.y)
                        g.drawImage(turnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(body.get(i+2).y < snakePart.y)
                        g.drawImage(turnUP_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageX.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
            }
        }

        switch (direction) {
            case UP -> g.drawImage(headImageUP.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(headImageDOWN.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(headImageLEFT.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(headImageRIGHT.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }

        switch (getTail().direction) {
            case UP -> g.drawImage(tailImageUP.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(tailImageDOWN.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(tailImageLEFT.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(tailImageRIGHT.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    /**
     * Metoda zwracająca współrzędne głowy Snake'a
     * @return punkt w którym znajduje się głowa
     */
    public SnakePart getHead() {
        return body.get(0);
    }
    /**
     * Metoda zwracająca liste elementów ciała Snake'a
     * @return lista ciała Snake'a
     */
    public List<SnakePart> getBody() {
        return body.subList(1, body.size()-1);
    }
    /**
     * Metoda zwracająca współrzędne ogona Snake'a
     * @return punkt w którym znajduje się ogon Snake'a
     */
    public SnakePart getTail() {
        return body.get(body.size()-1);
    }
    /**
     * Metoda ustawiająca pozycje ogonu Snake'a na przekazany punkt
     * @param snakePart część która ma zostać dodana do Snake'a
     */
    public void setTail(SnakePart snakePart) {
        body.add(snakePart);
    }
    /**
     * Metoda zwracająca kierunek Snake'a
     * @return kierunek w którym porusza sie Snake
     */
    public Direction getDirection() {
        return direction;
    }
    /**Metoda ustawiająca kierunek poruszania się Snake na przekazany w parametrze
     *
     * @param direction kierunek w którym Snake będzie się poruszał po ustawieniu
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Metoda odpowiadająca za ruch Snake
     */
    public void move() {
        getHead().direction=this.direction;
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

    /**
     * Metoda sprawdza kolizje Snake z kamieniami, końcem planszy oraz sobą samym
     * @param rocks tablica współrzędnych w których są kamienie
     * @return kolizja lub jej brak
     */
    public boolean collisionCheck(Rocks rocks) {
        var head = getHead();
        if(head.x < 0 || head.y < 0 || head.x >= Board.FIELD_X || head.y >= Board.FIELD_Y){
            return true;
        }
        for (SnakePart snakePart : getBody()) {
            if(snakePart.x == head.x && snakePart.y == head.y)
                return true;
        }
        for (int i = 0; i < rocks.getSizeX(); i++) {
            for (int j = 0; j < rocks.getSizeY(); j++) {
                if(rocks.getRocks()[i][j].x == head.x && rocks.getRocks()[i][j].y == head.y)
                    return true;
            }
        }

        return false;
    }
}
