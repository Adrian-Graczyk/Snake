import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.*;

public class GamePanel extends JPanel implements ActionListener{

    private Snake snake = new Snake();
    private ComputerSnake computerSnake = new ComputerSnake();
    private Mouse mouse = new Mouse();
    private Food food = new Food();
    private Rocks rocks = new Rocks();
    private JTextField userNameField;
    private JButton startButton;
    public boolean gameOver;
    public boolean gameOverComputer;
    public boolean startBoardActive;
    public String name;
    private final int DELAY = 100;
    private final int FOOD_DELAY = 200;
    private Timer timer = new Timer();


    public GamePanel() {
        setPreferredSize(new Dimension(Board.MAX_X, Board.MAX_Y));
        setBounds(0,0,Board.MAX_X, Board.MAX_Y);
        gameOver = false;
        gameOverComputer = false;
        startBoardActive=true;
        setLayout(null);
        addKeyListener(new GameKeyAdapter());
        setFocusable(true);

        startButton = StartBoard.getStartButton();
        startButton.addActionListener(this);
        startButton.setVisible(true);
        this.add(startButton);

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!gameOver && !startBoardActive && !gameOverComputer) {
            GameBoard.draw(g);
            snake.draw(g);
            computerSnake.computer_draw(g);
            food.draw(g);
            rocks.draw(g);
            mouse.draw(g);
            //add(Frame.scoreLabel);
        }
        if (gameOver || gameOverComputer) {
            GameOverBoard.draw(g);
            this.add(GameOverBoard.getPressRLabel());
            if(gameOver)
                this.add(GameOverBoard.getFinalScoreLabel(snake.getBody().size(), 1, name));
            if(gameOverComputer)
                this.add(GameOverBoard.getFinalScoreLabel(snake.getBody().size(), 2, name));
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
        gameOverComputer = false;
        food = new Food();
        snake = new Snake();
        computerSnake = new ComputerSnake();
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
                    name = ((JTextField) component).getText();
                    break;
                }
            }
            startBoardActive=false;
            gameOver=false;
            gameOverComputer = false;
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
    public boolean snakesCollisionCheck()
    {
        var cshead = computerSnake.getHead();
        for (SnakePart snakePart : snake.getBody()) {
            if(snakePart.x == cshead.x && snakePart.y == cshead.y) {
                gameOverComputer = true;
                return true;
            }
        }
        var shead = snake.getHead();
        for (SnakePart snakePart : computerSnake.getBody()) {
            if(snakePart.x == shead.x && snakePart.y == shead.y) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }
    public class Timer extends javax.swing.Timer {
        public Timer() {
            super(DELAY, e -> {
                if (!gameOver && !startBoardActive && !gameOverComputer) {
                    SnakeThread snakeThread = new SnakeThread();
                    ComputerSnakeThread computerSnakeThread = new ComputerSnakeThread();
                    ApplesThread applesThread = new ApplesThread();
                    MouseThread mouseThread = new MouseThread();

                    snakeThread.start();
                    computerSnakeThread.start();
                    mouseThread.start();
                    applesThread.start();


                    try {
                        snakeThread.join();
                        computerSnakeThread.join();
                        applesThread.join();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (snakesCollisionCheck()) {
                        System.out.println(snake.getBody().size());
                    }
                    repaint();
                }
            });
        }
    }

    public class SnakeThread extends Thread {
        public void run() {
            snake.move();
            if (snake.collisionCheck(rocks)) {
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
        }
    }
    public class ComputerSnakeThread extends Thread {
        public void run() {
            /*Point tmp = food.getApples()[];
                    if (tmp.x != -1 && tmp.y != -1) {
                        computer_snake.setTarget(tmp);
                    }*/

            computerSnake.computer_move();
            if (computerSnake.collisionCheck()) {
                gameOver = true;
            }
            for (Point point : food.getApples()) {
                var distance = sqrt((pow((point.x-computerSnake.getHead().x),2)+pow((point.y-computerSnake.getHead().y),2)));
                double max = 10000;
                if ((max + 10) > distance && point.x != -1 && point.y != -1) {
                    max = distance;
                    computerSnake.setTarget(point);
                }
            }
            for (Point point : food.getApples()) {
                if (point.x == computerSnake.getHead().x && point.y == computerSnake.getHead().y) {
                    point.setLocation(-1, -1);
                    var tail = computerSnake.getTail();
                    computerSnake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                    //Frame.scoreLabel.setText("Your Score: " + snake.getBody().size());
                }
            }
        }
    }

    public class ApplesThread extends Thread {
        public void run(){
            if(food.getIterationDelay()>10)
            {
                for (Point point : food.getApples()) {
                    if (point.x == -1 || point.y == -1) {
                        point.setLocation((int) (Math.random() * 48 + 1), (int) (Math.random() * 48 + 1));
                        break;
                    }
                }
                food.setIterationDelay(0);
            }
            else
                food.setIterationDelay(food.getIterationDelay()+1);
        }
    }

    public class MouseThread extends Thread {
        public void run(){
            if(mouse.getMovementDelay()>5)
            {
                mouse.move();
                mouse.setMovementDelay(0);
            }
            else
                mouse.setMovementDelay(mouse.getMovementDelay()+1);
        }
    }
}
