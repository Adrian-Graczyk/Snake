import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Math.*;

/**
 * Klasa GamePanel dziedzicząca po JLabel, klasa odpowiada za wyświetlanie i sterowanie rozgrywką
 */
public class GamePanel extends JPanel implements ActionListener{
    /**
     * Inicjalizacja obiektu typu Snake
     */
    private Snake snake = new Snake();
    /**
     * Inicjalizacja obiektu typu ComputerSnake
     */
    private ComputerSnake computerSnake = new ComputerSnake();
    /**
     * Inicjalizacja obiektu typu Mouse
     */
    private Mouse mouse = new Mouse();
    /**
     * Inicjalizacja obiektu typu Food
     */
    private Food food = new Food();
    /**
     * Inicjalizacja obiektu typu Rocks
     */
    private Rocks rocks = new Rocks();
    /**
     * Deklaracja pola tekstowego do którego gracz wpusze nazwe
     */
    private JTextField userNameField;
    /**
     * Deklaracja przycisku rozpoczynającego grę
     */
    private JButton startButton;
    /**
     * Zmienna bool ustawiająca wartość informującą o końcu gry gdy przegra gracz
     */
    public boolean gameOver;
    /**
     * Zmienna bool ustawiająca wartość informującą o końcu gry gdy przegra komputer
     */
    public boolean gameOverComputer;
    /**
     * Zmienna bool ustawiająca wartość informującą o wyświetlaniu planszy początkowej
     */
    public boolean startBoardActive;
    /**
     * Deklaracja zmiennej przechowującej nazwę gracza
     */
    public String name;
    /**
     * Inicjalizacja zmiennej przechowującej opóźnienie ruchu
     */
    private final int DELAY = 100;
    /**
     * Inicjalizacja zmiennej przechowującej opóźnienie pojawiania się jabłek
     */
    private final int FOOD_DELAY = 200;
    /**
     * Inicjalizacja zmiennej timer do obsługi ruchu w grze
     */
    private Timer timer = new Timer();

    /**
     * Konstruktor klasy GamePanel odpowiadający za uruchomienie gry
     */
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

    /**
     * Metoda rysująca elementy planszy podczas gry oraz rysująca plansze początkową i końcową
     * @param g the <code>Graphics</code> object to protect
     */
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
            this.add(GameOverBoard.topScore(snake.getBody().size(), name));
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

    /**
     * Metoda obsługująca resetowanie gry przypisane do klawisza
     */
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

    /**
     * Metoda reagująca na wciśniecie przycisku startu gry
     * @param e the event to be processed
     */
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

    /**
     * Obsługa sterowania za pomocą klawiszy przez gracza
     */
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

    /**
     * Metoda sprawdza kolizje między wężami
     * @return
     */
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

    /**
     * Metoda timer uruchamia wątki odpowiadające za węże, jabłka oraz mysz,
     * a także wywołuje sprawdzenie kolizji między wężami
     */
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
                        mouseThread.join();
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

    /**
     * Metoda uruchamiająca wątek Snake'a, wywołująca ruch, sprawdzająca kolizje,
     * reagująca na zjedzenie jabłka oraz myszy
     */
    public class SnakeThread extends Thread {
        public void run() {
            snake.move();
            if (snake.collisionCheck(rocks)) {
                gameOver = true;
            }
            if (mouse.getX() == snake.getHead().x && mouse.getY() == snake.getHead().y) {
                var tail = snake.getTail();
                if(tail.direction == Direction.RIGHT || tail.direction == Direction.LEFT) {
                    snake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                    snake.setTail(new SnakePart((tail.x + 1), (tail.y), tail.direction));
                    snake.setTail(new SnakePart((tail.x + 2), (tail.y), tail.direction));
                }
                else {
                    snake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                    snake.setTail(new SnakePart((tail.x), (tail.y + 1), tail.direction));
                    snake.setTail(new SnakePart((tail.x), (tail.y + 2), tail.direction));
                }
                mouse.setX((int) (Math.random() * 48 + 1));
                mouse.setY((int) (Math.random() * 48 + 1));
                Frame.scoreLabel.setText("Your Score: " + snake.getBody().size());
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
    /**
     * Metoda uruchamiająca wątek ComputerSnake'a, sprawdzająca kolizje, wywołująca ruch,
     * reagująca na zjedzenie jabłka oraz myszy, oraz wybór jabłka do którego wąż zostanie skierownay
     */
    public class ComputerSnakeThread extends Thread {
        public void run() {
            computerSnake.computer_move();
            if (computerSnake.collisionCheck(rocks)) {
                gameOverComputer = true;
            }
            for (Point point : food.getApples()) {
                var distance = sqrt((pow((point.x-computerSnake.getHead().x),2)+pow((point.y-computerSnake.getHead().y),2)));
                double max = 10000;
                if ((max + 10) > distance && point.x != -1 && point.y != -1) {
                    max = distance;
                    computerSnake.setTarget(point);
                }
            }
            if (mouse.getX() == computerSnake.getHead().x && mouse.getY() == computerSnake.getHead().y) {
                var tail = computerSnake.getTail();
                if(tail.direction == Direction.RIGHT || tail.direction == Direction.LEFT) {
                    computerSnake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                    computerSnake.setTail(new SnakePart((tail.x + 1), (tail.y), tail.direction));
                    computerSnake.setTail(new SnakePart((tail.x + 2), (tail.y), tail.direction));
                }
                else {
                    computerSnake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                    computerSnake.setTail(new SnakePart((tail.x), (tail.y + 1), tail.direction));
                    computerSnake.setTail(new SnakePart((tail.x), (tail.y + 2), tail.direction));
                }
                mouse.setX((int) (Math.random() * 48 + 1));
                mouse.setY((int) (Math.random() * 48 + 1));
            }
            for (Point point : food.getApples()) {
                if (point.x == computerSnake.getHead().x && point.y == computerSnake.getHead().y) {
                    point.setLocation(-1, -1);
                    var tail = computerSnake.getTail();
                    computerSnake.setTail(new SnakePart(tail.x, tail.y, tail.direction));
                }
            }
        }
    }
    /**
     * Metoda uruchamiająca wątek Jabłek oraz losująca punkty w których zostaną one wyświetlone na planszy
     */
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

    /**
     * Metoda uruchamiająca wątek myszy oraz wywołująca ruch myszy
     */
    public class MouseThread extends Thread {
        public void run(){
            //if(mouse.getMovementDelay()>2)
            //{
                mouse.move(snake, computerSnake);
               //mouse.setMovementDelay(0);
            //}
            //else
                //mouse.setMovementDelay(mouse.getMovementDelay()+1);
        }
    }
}
