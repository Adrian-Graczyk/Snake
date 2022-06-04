import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa incjalizuje pole w którym będzie wyświetlana gra i plansze początkowa i końcowa
 */
public class Frame extends JFrame implements ActionListener {
    /**
     * Pole statyczne w którym wyświetlany będzie wynik gracza
     */
    public static JLabel scoreLabel;
    /**
     * Przycisk statyczny do zmiany gracza
     */
    public static JButton changePlayerButton;

    /**
     * Konstruktor klasy Frame wyświetlający pola tekstowe, przyciski,
     * inicjalizujący panel gry i obsługujący przycisk zamknięcia gry
     */
    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake");
        //setLayout(BorderLayout());
       // setSize(Board.MAX_X,Board.MAX_Y);

        scoreLabel = new JLabel("Your Score: 0", SwingConstants.CENTER);
        scoreLabel.setVisible(true);
        //scoreLabel.setBounds(300,500,400,100);


        changePlayerButton = new JButton();
        changePlayerButton.setBounds(200,100,100,50);
        changePlayerButton.addActionListener(this);
        changePlayerButton.setVisible(false);

        add(new GamePanel(), BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);
        //add(changePlayerButton);


        pack();

        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
