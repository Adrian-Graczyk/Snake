import javax.swing.*;
import java.awt.*;

public class StartBoard extends Board{
    public static final int FIELD_X = 50;
    public static final int FIELD_Y = 50;
    public static final int SIZE = 20;
    public static final int MAX_X = FIELD_X * SIZE;
    public static final int MAX_Y = FIELD_Y * SIZE;


    public static JLabel getWelcomeLabel()
    {
        JLabel WelcomeLabel = new JLabel("Put your name on the board below and have fun!");
        WelcomeLabel.setFont(new Font("Arial",0,20));
        WelcomeLabel.setBounds(290,220, 500, 50);
        return WelcomeLabel;
    }


    public static JButton getStartButton(){
        JButton changePlayerButton = new JButton();
        changePlayerButton.setBounds(300,850,400,50);
        changePlayerButton.setText("Press to start");
        changePlayerButton.setVisible(false);
        changePlayerButton.setBackground(Color.green);
        return changePlayerButton;
    }

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

    public static void draw(Graphics g) {
        //g.setColor(Color.WHITE);
        //g.fillRect(0,0,MAX_X,MAX_Y);
        g.drawImage(startBackground.getImage(), 0, 0, MAX_X, MAX_Y, observer);
        g.drawImage(logoImage.getImage(), 200, 10, 600, 300, observer);
        g.drawImage(startSnakeImage.getImage(), 300, 300, 500, 500, observer);
    }

}
