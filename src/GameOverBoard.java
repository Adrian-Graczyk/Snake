import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameOverBoard extends Board{


    public static JLabel getPressRLabel()
    {
        JLabel pressRLabel = new JLabel("Press \"R\" to start again");
        pressRLabel.setFont(new Font("Arial",0,25));
        pressRLabel.setBounds(670,220, 400, 50);
        return pressRLabel;
    }

    public static JLabel getScoreLabel(int score)
    {
        JLabel scoreLabel = new JLabel("Your final score: " + score);
        scoreLabel.setFont(new Font("Arial",0,25));
        scoreLabel.setBounds(670,280, 400, 50);
        return scoreLabel;
    }

    public static void draw(Graphics g) {
        g.drawImage(gameOverBackground.getImage(), 0, 0, MAX_X+50, MAX_Y+50, observer);
        g.drawImage(sadSnakeImage.getImage(), 200, 700, 200, 200, observer);
        g.drawImage(sadSnakeImage2.getImage(), 700, 700, 150, 150, observer);
        g.drawImage(gameOverLabel.getImage(), 600, -100, 400, 400, observer);
    }
}
