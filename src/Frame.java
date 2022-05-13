import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public static JLabel scoreLabel;
    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake");

        scoreLabel = new JLabel("Your Score: 0", SwingConstants.CENTER);
        //gameOverLabel.setFont(new Font());
        scoreLabel.setVisible(true);


        add(new GamePanel(), BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        //JLabel label = new JLabel("Hello StackOverflow!");//Make a label
        //GamePanel.add(label);//Add it to the panel (which is on the frame)

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
