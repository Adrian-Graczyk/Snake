import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.sql.Types.NULL;

public class ComputerSnake extends Sprites{

    private Point target = new Point(0,0);
    private List<SnakePart> computer_body;
    private Direction direction;

    public ComputerSnake() {
        direction = Direction.UP;
        computer_body = new ArrayList<>();
        computer_body.add(new SnakePart(20, 25, direction));
        computer_body.add(new SnakePart(20, 26, direction));
    }
    public void computer_draw(Graphics g) {
        var computer_bodyTMP = getBody();
        for ( int i = 0; i<computer_bodyTMP.size(); i++) {
            var snakePart = computer_bodyTMP.get(i);
            switch (snakePart.direction) {
                case UP:
                    if(computer_body.get(i+2).x > snakePart.x)
                        g.drawImage(AIturnUP_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).x < snakePart.x)
                        g.drawImage(AIturnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case DOWN:
                    if(computer_body.get(i+2).x > snakePart.x)
                        g.drawImage(AIturnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).x < snakePart.x)
                        g.drawImage(AIturnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageY.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case LEFT:
                    if(computer_body.get(i+2).y > snakePart.y)
                        g.drawImage(AIturnDOWN_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).y < snakePart.y)
                        g.drawImage(AIturnUP_LEFT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageX.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
                case RIGHT:
                    if(computer_body.get(i+2).y > snakePart.y)
                        g.drawImage(AIturnDOWN_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else if(computer_body.get(i+2).y < snakePart.y)
                        g.drawImage(AIturnUP_RIGHT.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    else
                        g.drawImage(AIbodyImageX.getImage(),snakePart.x * Board.SIZE, snakePart.y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
                    break;
            }
        }

        switch (direction) {
            case UP -> g.drawImage(AIheadImageUP.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(AIheadImageDOWN.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(AIheadImageLEFT.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(AIheadImageRIGHT.getImage(),getHead().x * Board.SIZE, getHead().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }

        switch (getTail().direction) {
            case UP -> g.drawImage(AItailImageUP.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(AItailImageDOWN.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(AItailImageLEFT.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(AItailImageRIGHT.getImage(),getTail().x * Board.SIZE, getTail().y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
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
    public void choose_direction() {
        var head = getHead();
        if(head.x > 15 && head.x < 32 && head.y == 19 && this.getDirection() != Direction.RIGHT){
            this.setDirection(Direction.LEFT);
            Point tmp = new Point(10,40);
            this.setTarget(tmp);
            System.out.println("Tutaj1");
        }
        else if(head.x > 15 && head.x < 32 && head.y == 24 && this.getDirection() != Direction.LEFT){
            this.setDirection(Direction.RIGHT);
            System.out.println("Tutaj2");
        }
        else if(head.y > 19 && head.y < 24 && head.x == 15){
            int tmp = (int)(Math.random() * 2);
            if(tmp == 1 && this.getDirection() != Direction.UP) {
                if(head.x == 15) {
                    this.setDirection(Direction.UP);
                }
            } else {
                this.setDirection(Direction.DOWN);
            }
            System.out.println("Tutaj3 x: " + head.x + " y:" + head.y);
        }
        else if(head.y > 19 && head.y < 24 && head.x == 32){
            int tmp = (int)(Math.random() * 2);
            if(tmp == 1 && this.getDirection() != Direction.UP) {
                if(head.x == 32)
                this.setDirection(Direction.DOWN);
            } else {
                this.setDirection(Direction.UP);
            }
            System.out.println("Tutaj4 x:" + head.x + " y:" + head.y);
        }
        else if(head.x > 0 && head.y > 0 && head.x < ((Board.FIELD_X - 1)) && head.y < ((Board.FIELD_Y - 1))){
            if (target.x > getHead().x && this.getDirection() != Direction.LEFT) {
                this.setDirection(Direction.RIGHT);
            }
            else if (target.x < getHead().x && this.getDirection() != Direction.RIGHT) {
                this.setDirection(Direction.LEFT);
            }
            else if (target.x == getHead().x && target.y > getHead().y && this.getDirection() != Direction.UP ) {
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

        choose_direction();
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
