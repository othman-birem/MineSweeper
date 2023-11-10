import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Game {
    private Board layout;

    public static final String NORMAL_STATE_IMAGE = "Images/MineSweeperimgs/facingDown.png";
    public static final int MINE_WIDTH = 30;
    public static final int MINE_HEIGHT = 30;
    public static final int X = 12;
    public static final int Y = 15;
    protected JFrame window;

    public static final int BOMB_COUNT = 50;
    /*
     * private int sb_width = 100;
     * private boolean win = false;
     * private int flags;
     */

    public Game() throws IOException {
        window = new JFrame();
        layout = new Board();
        window.setTitle("MyMineSweeper");
        window.setIconImage(ImageIO.read(new File("Images/MineSweeperimgs/bomb.png")));
        window.setSize(X * MINE_WIDTH, Y * MINE_HEIGHT);
        window.setLayout(new BorderLayout());
        // flags = BOMB_COUNT;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(layout, BorderLayout.CENTER);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static BufferedImage resizeImage(BufferedImage original, int width, int height) throws IOException {
        Image resultingImage = original.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage outPutImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        outPutImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outPutImage;
    }

    public void EndGame() {
        window.setVisible(false);
    }

    public void Close() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
