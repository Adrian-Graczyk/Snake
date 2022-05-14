import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

    private Snake snake = new Snake();
    private Food food = new Food();
    private JTextField userNameField;
    private JButton startButton;
    public boolean gameOver;
    public boolean startBoardActive;
    private final int DELAY = 100;
    private final int FOOD_DELAY = 2000;
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
            food.draw(g);
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
                    if (snake.collisionCheck()) {
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
                            point.setLocation((int) (Math.random() * 50 + 1), (int) (Math.random() * 50 + 1));
                            break;
                        }
                }
            });
        }
    }
}
