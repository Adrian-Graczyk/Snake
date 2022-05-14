
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Sprites{

    private List<SnakePart> body;
    private Direction direction;
    public Snake() {
        direction = Direction.RIGHT;
        body = new ArrayList<>();
        body.add(new SnakePart(20, 20, direction));
        body.add(new SnakePart(21, 20, direction));
    }

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

    public SnakePart getHead() {
        return body.get(0);
    }

    public List<SnakePart> getBody() {
        return body.subList(1, body.size()-1);
    }

    public SnakePart getTail() {
        return body.get(body.size()-1);
    }

    public void setTail(SnakePart snakePart) {
        body.add(snakePart);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


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

    public boolean collisionCheck() {
        var head = getHead();
        if(head.x < 0 || head.y < 0 || head.x >= Board.FIELD_X || head.y >= Board.FIELD_Y){
            return true;
        }
        for (SnakePart snakePart : getBody()) {
            if(snakePart.x == head.x && snakePart.y == head.y)
                return true;
        }
        return false;
    }
}
