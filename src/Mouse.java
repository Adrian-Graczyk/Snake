
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Mouse extends Sprites{

    private int x;
    private int y;
    private Direction direction;

    private int movementDelay;

    public Mouse(){
        x=40;
        y=40;
        direction = Direction.UP;
        movementDelay=0;
    }

    public void draw(Graphics g) {
        switch (direction) {
            case UP -> g.drawImage(mouseImageUP.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(mouseImageDOWN.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(mouseImageLEFT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(mouseImageRIGHT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

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
    public void move(Snake snake, ComputerSnake computersnake) {

        choose_direction(snake, computersnake);
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }




    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setMovementDelay(int movementDelay) { this.movementDelay = movementDelay; }

    public int getMovementDelay() {return movementDelay; }

}
