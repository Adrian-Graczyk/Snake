import java.awt.*;

public class Rocks extends Sprites{
    private final int sizeY = 4;
    private final int sizeX = 16;
    private Point[][] rocks = new Point[sizeX][sizeY];

    public Rocks() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                rocks[i][j] = new Point(i+16,j+20);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < sizeX; i=i+4) {
            for (int j = 0; j < sizeY; j=j+4) {
                g.drawImage(rocksImage.getImage(),rocks[i][j].x * Board.SIZE, rocks[i][j].y * Board.SIZE, Board.SIZE*4, Board.SIZE*4, observer);
            }
        }
    }

    public Point[][] getRocks() {
        return rocks;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

}
