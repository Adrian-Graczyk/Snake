import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class ComputerSnake extends Sprites{

    private Point target = new Point(0,0);
    private List<SnakePart> computer_body;
    private Direction direction;

    public ComputerSnake() {
        direction = Direction.UP;
        computer_body = new ArrayList<>();
        computer_body.add(new SnakePart(15, 17, direction));
        computer_body.add(new SnakePart(16, 17, direction));
    }
    public void computer_draw(Graphics g) {
        var computer_bodyTMP = getBody();
        for ( int i = 0; i<computer_bodyTMP.size(); i++) {
            var snakePart = computer_bodyTMP.get(i);
            switch (snakePart.direction) {
                case UP:
                    if(computer_body.get(i+2).x > snakePart.x)
                        g.drawImage(turnUP_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).x < snakePart.x)
                        g.drawImage(turnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case DOWN:
                    if(computer_body.get(i+2).x > snakePart.x)
                        g.drawImage(turnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).x < snakePart.x)
                        g.drawImage(turnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case LEFT:
                    if(computer_body.get(i+2).y > snakePart.y)
                        g.drawImage(turnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).y < snakePart.y)
                        g.drawImage(turnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(bodyImageX.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case RIGHT:
                    if(computer_body.get(i+2).y > snakePart.y)
                        g.drawImage(turnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).y < snakePart.y)
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

    public void setTarget(Point point) {this.target = point;}
    public SnakePart getHead() {
        return computer_body.get(0);
    }

    public List<SnakePart> getBody() {
        return computer_body.subList(1, computer_body.size()-1);
    }

    public SnakePart getTail() {
        return computer_body.get(computer_body.size()-1);
    }

    public void setTail(SnakePart snakePart) {
        computer_body.add(snakePart);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void choose_apple() {
        var head = getHead();
        if (head.x > 0 && head.y > 0 && head.x < ((Board.FIELD_X - 1)) && head.y < ((Board.FIELD_Y - 1))){
            if (target.x > getHead().x && this.getDirection() != Direction.LEFT) {
                this.setDirection(Direction.RIGHT);
            }
            else if (target.x < getHead().x && this.getDirection() != Direction.RIGHT) {
                this.setDirection(Direction.LEFT);
            }
            else if (target.x == getHead().x && target.y > getHead().y &&this.getDirection() != Direction.UP ) {
                this.setDirection(Direction.DOWN);
            }
            else if (target.x == getHead().x && target.y < getHead().y && this.getDirection() != Direction.DOWN) {
                this.setDirection(Direction.UP);
            }
            else if (target.x == NULL && target.y == NULL && this.getDirection() != Direction.RIGHT) {
                this.setDirection(Direction.DOWN);
            }
        }
        else if (head.x == 0 && head.y == ((Board.FIELD_Y - 1))) {
            this.setDirection(Direction.RIGHT);
        }
        else if (head.x == 0) {
            if(getDirection() == Direction.DOWN) {
                this.setDirection(Direction.RIGHT);
            }
            else {
                this.setDirection(Direction.DOWN);
            }
        }
        else if (head.y == Board.FIELD_Y - 1 && head.x == ((Board.FIELD_X - 1))) {
            this.setDirection(Direction.UP);
        }
        else if (head.y == Board.FIELD_Y - 1) {
            if(getDirection() == Direction.RIGHT) {
                this.setDirection(Direction.UP);
            }
            else {
                this.setDirection(Direction.RIGHT);
            }
        }
        else if (head.y == 0) {
            if(getDirection() == Direction.LEFT) {
                this.setDirection(Direction.DOWN);
            }
            else {
                this.setDirection(Direction.LEFT);
            }
        }
        else if (head.x == (Board.FIELD_X - 1)) {
            if(getDirection() == Direction.UP) {
                this.setDirection(Direction.LEFT);
            }
            else {
                this.setDirection(Direction.UP);
            }
        }
    }
    public void computer_move() {

        choose_apple();
        getHead().direction=this.direction;
        for (int i = computer_body.size() - 1; i > 0; i--) {
            computer_body.get(i).x = computer_body.get(i - 1).x;
            computer_body.get(i).y = computer_body.get(i - 1).y;
            computer_body.get(i).direction = computer_body.get(i - 1).direction;
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
        for (SnakePart snakePart : getBody()) {
            if(snakePart.x == head.x && snakePart.y == head.y)
                return true;
        }
        return false;
    }


}
