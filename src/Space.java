import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Space extends JPanel {
    private boolean bomb;
    private boolean flagged;
    private boolean cleared;
    private int bombNearBy;
    public BufferedImage image;

    public Space(boolean bomb) throws IOException {
        this.bomb = bomb;
        flagged = false;
        cleared = false;
        bombNearBy = 0;
        BufferedImage temp = ImageIO.read(new File(Game.NORMAL_STATE_IMAGE));
        this.image = Game.resizeImage(temp, Game.MINE_WIDTH - 4, Game.MINE_HEIGHT - 4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public void setFlagged(boolean f) {
        flagged = f;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void clear() {
        cleared = true;
    }

    public boolean isCleared() {
        return cleared;
    }

    public boolean hasBomb() {
        return bomb;
    }

    public int getBombNearBy() {
        return bombNearBy;
    }

    public void setBombNearBy(int x) {
        bombNearBy = x;
    }

    public void setImage(String fileName) throws IOException {
        BufferedImage temp = ImageIO.read(new File(fileName));
        image = Game.resizeImage(temp, Game.MINE_WIDTH - 4, Game.MINE_HEIGHT - 4);
        getGraphics().drawImage(temp, 0, 0, 27, 27, null);
    }

    public BufferedImage getImage() {
        return image;
    }
}
