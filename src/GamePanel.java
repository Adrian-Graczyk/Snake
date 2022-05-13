import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private Snake snake = new Snake();
    private Food food = new Food();
    public boolean gameOver;
    private final int DELAY = 100;
    private final int FOOD_DELAY = 2000;
    private Timer timer = new Timer();
    private FoodTimer foodTimer = new FoodTimer();

    public GamePanel() {
        setPreferredSize(new Dimension(Board.MAX_X, Board.MAX_Y));
        gameOver = false;

        addKeyListener(new GameKeyAdapter());
        setFocusable(true);

        foodTimer.start();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!gameOver) {
            Board.draw(g);
            snake.draw(g);
            food.draw(g);
        } else GameOverBoard.draw(g);

    }

    public void ResetPanel() {
        timer.stop();
        gameOver = false;
        food = new Food();
        snake = new Snake();
        timer = new Timer();
        timer.start();
    }

    private class GameKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN)
                        snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP)
                        snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT)
                        snake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT)
                        snake.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_R:
                    ResetPanel();
            }
        }
    }

    public class Timer extends javax.swing.Timer {
        public Timer() {
            super(DELAY, e -> {
                if (!gameOver) {
                    snake.move();
                    if (snake.collisionCheck()) gameOver = true;

                    for (Point point : food.getApples()) {
                        if (point.x == snake.getHead().x && point.y == snake.getHead().y) {
                            point.setLocation(-1, -1);
                            var tail = snake.getTail();
                            snake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                            /*switch(snake.getDirection()){
                                case UP:
                                    snake.setTail(new Point(tail.x, tail.y));
                                    break;
                                case DOWN:
                                    snake.setTail(new Point(tail.x, tail.y));
                                    break;
                                case LEFT:
                                    snake.setTail(new Point(tail.x, tail.y));
                                    break;
                                case RIGHT:
                                    snake.setTail(new Point(tail.x, tail.y));
                                    break;
                            }*/
                        }
                    }
                    repaint();
                }
            });
        }
    }

    public class FoodTimer extends javax.swing.Timer {
        public FoodTimer() {
            super(FOOD_DELAY, e -> {
                if (!gameOver) {
                    for (Point point : food.getApples())
                        if (point.x == -1 || point.y == -1) {
                            point.setLocation((int) (Math.random() * 50 + 1), (int) (Math.random() * 50 + 1));
                            break;
                        }
                }
            });
        }
    }
}
