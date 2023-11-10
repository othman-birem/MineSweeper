
import java.io.IOException;

public class Main {
    private static Game CurrentGame;

    public static void main(String[] args) throws Exception {
        CurrentGame = new Game();
    }

    public static void NewGame() throws IOException {
        CurrentGame.EndGame();
        Game NewGame = new Game();
        CurrentGame = NewGame;
    }
}
