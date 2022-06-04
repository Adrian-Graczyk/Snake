import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadająca za plansze przed rozpoczęciem gry
 */
public class StartBoard extends Board{
    /**
     * Zmienna określająca rozmiar na osi X
     */
    public static final int FIELD_X = 50;
    /**
     * Zmienna określająca rozmiar na osi Y
     */
    public static final int FIELD_Y = 50;
    /**
     * Zmienna określająca rozmiar planszy
     */
    public static final int SIZE = 20;
    /**
     * Zmienna określająca maksymalny rozmiar planszy na osi X
     */
    public static final int MAX_X = FIELD_X * SIZE;
    /**
     * Zmienna określająca maksymalny rozmiar planszy na osi Y
     */
    public static final int MAX_Y = FIELD_Y * SIZE;

    /**
     * Metoda inicjalizuje pole w którym wyświetlana jest informacja
     * aby podać imię
     * @return pole typu JLabel w którym wyświetlany jest tekst
     */
    public static JLabel getWelcomeLabel()
    {
        JLabel WelcomeLabel = new JLabel("Put your name on the board below and have fun!");
        WelcomeLabel.setFont(new Font("Arial",0,20));
        WelcomeLabel.setBounds(290,220, 500, 50);
        return WelcomeLabel;
    }
    /**
     * Metoda inicjalizuje przycisk rozpoczynający grę
     * @return przycisk typu JButton
     */
    public static JButton getStartButton(){
        JButton changePlayerButton = new JButton();
        changePlayerButton.setBounds(300,880,400,50);
        changePlayerButton.setText("Press to start");
        changePlayerButton.setVisible(false);
        changePlayerButton.setBackground(new Color(220,172,114));
        return changePlayerButton;
    }
    /**
     * Metoda inicjalizuje pole w którym wpisana będzie nazwa gracza
     * @return pole typu JTexTField z nazwą gracza
     */
    public static JTextField getUserNameField()
    {
        JTextField userNameField = new JTextField();
        userNameField.setFont(new Font("Arial",0,25));
        userNameField.setBounds(400,420, 200, 120);
        userNameField.setBackground(new Color(250,216,175));
        userNameField.setHorizontalAlignment(SwingConstants.CENTER);
        userNameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        return userNameField;
    }

    /**
     * Metoda rysująca elementy graficzne planszy przed rozpoczęciem gry
     * @param g
     */
    public static void draw(Graphics g) {
        //g.setColor(Color.WHITE);
        //g.fillRect(0,0,MAX_X,MAX_Y);
        g.drawImage(startBackground.getImage(), 0, 0, MAX_X, MAX_Y, observer);
        g.drawImage(logoImage.getImage(), 200, 10, 600, 300, observer);
        g.drawImage(startSnakeImage.getImage(), 300, 300, 500, 500, observer);
    }

}
