import java.awt.*;

/**
 * Klasa game wywołuje main i uruchamia grę
 */
public class Game {
    /**
     * main uruchamia grę
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }
}
