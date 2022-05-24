
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Mouse extends Sprites{

    private int x;
    private int y;
    private Direction direction;

    public Mouse(){
        x=40;
        y=40;
        direction = Direction.UP;
    }

    public void draw(Graphics g) {
        switch (direction) {
            case UP -> g.drawImage(mouseImageUP.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case DOWN -> g.drawImage(mouseImageDOWN.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case LEFT -> g.drawImage(mouseImageLEFT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
            case RIGHT -> g.drawImage(mouseImageRIGHT.getImage(), x * Board.SIZE, y * Board.SIZE, Board.SIZE, Board.SIZE, observer);
        }
    }

    public void move() {
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

}
