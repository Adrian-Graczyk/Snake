import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameOverBoard extends Board{


    public static JLabel getPressRLabel()
    {
        JLabel pressRLabel = new JLabel("Press \"R\" to start again");
        pressRLabel.setFont(new Font("Arial",0,25));
        pressRLabel.setBounds(670,220, 400, 50);
        return pressRLabel;
    }

    public static JLabel getFinalScoreLabel(int score, int winner, String name)
    {
        JLabel scoreLabel = new JLabel();
        if(winner == 1)
            scoreLabel =  new JLabel("<html> Computer win! <br/> Your final score: " + score +"</html>");
        if(winner == 2)
            scoreLabel = new JLabel(name + "<html> win! <br/> Your final score: " + score + "</html>");
        scoreLabel.setFont(new Font("Arial",0,25));
        scoreLabel.setBounds(670,280, 400, 50);
        return scoreLabel;
    }
    public static JLabel topScore(int score, String name)
    {
        JLabel scoreLabel = new JLabel();
        String bestplayername = "";
        int bestplayerscore = 0;
        File file = new File("Score.txt");
        try {
            Scanner in = new Scanner(file);
            bestplayername = in.hasNextLine() ? in.next() : null;
            bestplayerscore = in.hasNextLine() ? Integer.parseInt(in.next()) : 0;
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        if(score >= bestplayerscore)
        {
            try {
                PrintWriter write = new PrintWriter("Score.txt");
                write.println(name);
                write.println(score);
                write.close();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
            scoreLabel = new JLabel("<html> Best Player <br/>" + name + " = "+ score + " pkt!" +"</html>");
        }
        else {
            scoreLabel = new JLabel("<html> Best Player <br/>" + bestplayername + " = "+ bestplayerscore +" pkt!" + "</html>");
        }

        scoreLabel.setFont(new Font("Arial",0,25));
        scoreLabel.setBounds(670,340, 400, 70);
        return scoreLabel;
    }

    public static void draw(Graphics g) {
        g.drawImage(gameOverBackground.getImage(), 0, 0, MAX_X+50, MAX_Y+50, observer);
        g.drawImage(sadSnakeImage.getImage(), 200, 700, 200, 200, observer);
        g.drawImage(sadSnakeImage2.getImage(), 700, 700, 150, 150, observer);
        g.drawImage(gameOverLabel.getImage(), 600, -100, 400, 400, observer);
    }
}
