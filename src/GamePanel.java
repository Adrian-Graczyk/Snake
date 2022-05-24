import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.*;

public class GamePanel extends JPanel implements ActionListener {

    private Snake snake = new Snake();
    private ComputerSnake computer_snake = new ComputerSnake();
    private Mouse mouse = new Mouse();
    private Food food = new Food();
    private Rocks rocks = new Rocks();
    private JTextField userNameField;
    private JButton startButton;
    public boolean gameOver;
    public boolean startBoardActive;
    private final int DELAY = 100;
    private final int FOOD_DELAY = 200;
    private Timer timer = new Timer();
    private FoodTimer foodTimer = new FoodTimer();

    public GamePanel() {
        setPreferredSize(new Dimension(Board.MAX_X, Board.MAX_Y));
        setBounds(0,0,Board.MAX_X, Board.MAX_Y);
        gameOver = false;
        startBoardActive=true;
        setLayout(null);
        addKeyListener(new GameKeyAdapter());
        setFocusable(true);

        startButton = StartBoard.getStartButton();
        startButton.addActionListener(this);
        startButton.setVisible(true);
        this.add(startButton);

        foodTimer.start();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!gameOver && !startBoardActive) {
            GameBoard.draw(g);
            snake.draw(g);
            computer_snake.computer_draw(g);
            food.draw(g);
            rocks.draw(g);
            mouse.draw(g);
            //add(Frame.scoreLabel);
        }
        if (gameOver) {
            GameOverBoard.draw(g);
            this.add(GameOverBoard.getPressRLabel());
            this.add(GameOverBoard.getFinalScoreLabel(snake.getBody().size()));
        }
        if (startBoardActive){
            StartBoard.draw(g);
            this.add(StartBoard.getWelcomeLabel());
            userNameField = StartBoard.getUserNameField();
            this.add(userNameField);
            startButton.setVisible(true);
        }
    }

    public void ResetPanel() {
        removeAll();
        timer.stop();
        gameOver = false;
        food = new Food();
        snake = new Snake();
        computer_snake = new ComputerSnake();
        timer = new Timer();
        timer.start();
        Frame.scoreLabel.setText("Your Score: 0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton) {
            Component[] components = this.getComponents();
            Component component = null;
            for (int i = 0; i < components.length; i++) {
                component = components[i];
                if (component instanceof JTextField) {
                    System.out.println(((JTextField) component).getText());
                    System.out.println(Board.FIELD_X - 1);
                    break;
                }
            }
            startBoardActive=false;
            gameOver=false;
            this.ResetPanel();
        }
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
                    break;
            }
        }
    }

    public class Timer extends javax.swing.Timer {
        public Timer() {
            super(DELAY, e -> {
                if (!gameOver && !startBoardActive) {
                    snake.move();
                    computer_snake.computer_move();
                    if (snake.collisionCheck(rocks)) {
                        System.out.println(snake.getBody().size());
                        gameOver = true;
                    }
                    if (computer_snake.collisionCheck()) {
                        System.out.println(snake.getBody().size());
                        gameOver = true;
                    }
                    for (Point point : food.getApples()) {
                        if (point.x == snake.getHead().x && point.y == snake.getHead().y) {
                            point.setLocation(-1, -1);
                            var tail = snake.getTail();
                            snake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                            Frame.scoreLabel.setText("Your Score: " + snake.getBody().size());
                        }
                    }
                    for (Point point : food.getApples()) {
                        var distance = sqrt((pow((point.x-computer_snake.getHead().x),2)+pow((point.y-computer_snake.getHead().y),2)));
                        double max = 10000;
                        if ((max + 10) > distance && point.x != -1 && point.y != -1) {
                            max = distance;
                            computer_snake.setTarget(point);
                        }
                    }
                    for (Point point : food.getApples()) {
                        if (point.x == computer_snake.getHead().x && point.y == computer_snake.getHead().y) {
                            point.setLocation(-1, -1);
                            var tail = computer_snake.getTail();
                            computer_snake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                            //Frame.scoreLabel.setText("Your Score: " + snake.getBody().size());
                        }
                    }
                   /*Point tmp = food.getApples()[];
                    if (tmp.x != -1 && tmp.y != -1) {
                        computer_snake.setTarget(tmp);
                    }*/
                    mouse.move();
                    repaint();
                }
            });
        }
    }

    public class FoodTimer extends javax.swing.Timer {
        public FoodTimer() {
            super(FOOD_DELAY, e -> {
                if (!gameOver && !startBoardActive) {
                    for (Point point : food.getApples())
                        if (point.x == -1 || point.y == -1) {
                            point.setLocation((int) (Math.random() * 48 + 1), (int) (Math.random() * 48 + 1));
                            break;
                        }
                }
            });
        }
    }
}
