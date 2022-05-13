import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake");
        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
