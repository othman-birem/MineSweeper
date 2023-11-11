import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Board extends Container implements MouseListener {
    private Space[][] board = new Space[Game.Y][Game.X];
    private int ClearedSpaces = 0;
    // private JButton[][] layout = new JButton[Game.Y][Game.X];

    public Board() {
        int reminder = Game.BOMB_COUNT;
        double probability = (double) Game.BOMB_COUNT / (Game.X * Game.Y);
        this.setLayout(new GridLayout(Game.Y, Game.X));
        try {
            for (int i = 0; i < Game.Y; i++) {
                for (int j = 0; j < Game.X; j++) {
                    if (reminder > 0 && Math.random() < probability) {
                        board[i][j] = new Space(true);
                        reminder--;
                    } else {
                        board[i][j] = new Space(false);
                    }
                    board[i][j].addMouseListener(this);
                    this.add(board[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        calculate();
    }

    private void calculate() {
        for (int y = 0; y < Game.Y; y++) {
            for (int x = 0; x < Game.X; x++) {
                if (!board[y][x].hasBomb()) {
                    int count = 0;

                    if (y > 0 && board[y - 1][x].hasBomb())
                        count++;

                    if (y < Game.Y - 1 && board[y + 1][x].hasBomb())
                        count++;

                    if (x > 0 && board[y][x - 1].hasBomb())
                        count++;

                    if (x < Game.X - 1 && board[y][x + 1].hasBomb())
                        count++;

                    if (x < Game.X - 1 && y > 0 && board[y - 1][x + 1].hasBomb())
                        count++;

                    if (x < Game.X - 1 && y < Game.Y - 1 && board[y + 1][x + 1].hasBomb())
                        count++;

                    if (x > 0 && y < Game.Y - 1 && board[y + 1][x - 1].hasBomb())
                        count++;
                    if (x > 0 && y > 0 && board[y - 1][x - 1].hasBomb())
                        count++;

                    board[y][x].setBombNearBy(count);
                }
            }
        }
    }

    public Space[][] getBoard() {
        return board;
    }

    public void NormalizeEmptySpaces(Space INITIAL_CELL, int[] Coordinations) throws IOException {
        INITIAL_CELL.setImage("Images/MineSweeperimgs/" + INITIAL_CELL.getBombNearBy() + ".png");
        INITIAL_CELL.Clear();
        ClearedSpaces++;
        if (Coordinations[0] > 0 && !board[Coordinations[0] - 1][Coordinations[1]].isCleared()) {
            board[Coordinations[0] - 1][Coordinations[1]]
                    .setImage("Images/MineSweeperimgs/" + board[Coordinations[0] - 1][Coordinations[1]].getBombNearBy()
                            + ".png");
            board[Coordinations[0] - 1][Coordinations[1]].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] - 1][Coordinations[1]].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] - 1][Coordinations[1]],
                        new int[] { Coordinations[0] - 1, Coordinations[1] });
        }

        if (Coordinations[0] < Game.Y - 1 && !board[Coordinations[0] + 1][Coordinations[1]].isCleared()) {
            board[Coordinations[0] + 1][Coordinations[1]]
                    .setImage("Images/MineSweeperimgs/" + board[Coordinations[0] + 1][Coordinations[1]].getBombNearBy()
                            + ".png");
            board[Coordinations[0] + 1][Coordinations[1]].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] + 1][Coordinations[1]].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] + 1][Coordinations[1]],
                        new int[] { Coordinations[0] + 1, Coordinations[1] });
        }

        if (Coordinations[1] > 0
                && !board[Coordinations[0]][Coordinations[1] - 1].isCleared()) {
            board[Coordinations[0]][Coordinations[1] - 1]
                    .setImage("Images/MineSweeperimgs/" + board[Coordinations[0]][Coordinations[1] - 1].getBombNearBy()
                            + ".png");
            board[Coordinations[0]][Coordinations[1] - 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0]][Coordinations[1] - 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0]][Coordinations[1] - 1],
                        new int[] { Coordinations[0], Coordinations[1] - 1 });
        }

        if (Coordinations[1] < Game.X - 1 && !board[Coordinations[0]][Coordinations[1] + 1].isCleared()) {
            board[Coordinations[0]][Coordinations[1] + 1]
                    .setImage("Images/MineSweeperimgs/" + board[Coordinations[0]][Coordinations[1] + 1].getBombNearBy()
                            + ".png");
            board[Coordinations[0]][Coordinations[1] + 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0]][Coordinations[1] + 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0]][Coordinations[1] + 1],
                        new int[] { Coordinations[0], Coordinations[1] + 1 });
        }

        if (Coordinations[1] < Game.X - 1 && Coordinations[0] > 0
                && !board[Coordinations[0] - 1][Coordinations[1] + 1].isCleared()) {
            board[Coordinations[0] - 1][Coordinations[1] + 1]
                    .setImage("Images/MineSweeperimgs/"
                            + board[Coordinations[0] - 1][Coordinations[1] + 1].getBombNearBy() + ".png");
            board[Coordinations[0] - 1][Coordinations[1] + 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] - 1][Coordinations[1] + 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] - 1][Coordinations[1] + 1],
                        new int[] { Coordinations[0] - 1, Coordinations[1] + 1 });
        }

        if (Coordinations[1] < Game.X - 1 && Coordinations[0] < Game.Y - 1
                && !board[Coordinations[0] + 1][Coordinations[1] + 1].isCleared()) {
            board[Coordinations[0] + 1][Coordinations[1] + 1]
                    .setImage("Images/MineSweeperimgs/"
                            + board[Coordinations[0] + 1][Coordinations[1] + 1].getBombNearBy() + ".png");
            board[Coordinations[0] + 1][Coordinations[1] + 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] + 1][Coordinations[1] + 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] + 1][Coordinations[1] + 1],
                        new int[] { Coordinations[0] + 1, Coordinations[1] + 1 });
        }

        if (Coordinations[1] > 0 && Coordinations[0] < Game.Y - 1
                && !board[Coordinations[0] + 1][Coordinations[1] - 1].isCleared()) {
            board[Coordinations[0] + 1][Coordinations[1] - 1]
                    .setImage("Images/MineSweeperimgs/"
                            + board[Coordinations[0] + 1][Coordinations[1] - 1].getBombNearBy() + ".png");
            board[Coordinations[0] + 1][Coordinations[1] - 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] + 1][Coordinations[1] - 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] + 1][Coordinations[1] - 1],
                        new int[] { Coordinations[0] + 1, Coordinations[1] - 1 });
        }

        if (Coordinations[1] > 0 && Coordinations[0] > 0

                && !board[Coordinations[0] - 1][Coordinations[1] - 1].isCleared()) {
            board[Coordinations[0] - 1][Coordinations[1] - 1]
                    .setImage("Images/MineSweeperimgs/"
                            + board[Coordinations[0] - 1][Coordinations[1] - 1].getBombNearBy() + ".png");
            board[Coordinations[0] - 1][Coordinations[1] - 1].Clear();
            ClearedSpaces++;
            if (board[Coordinations[0] - 1][Coordinations[1] - 1].getBombNearBy() == 0)
                NormalizeEmptySpaces(board[Coordinations[0] - 1][Coordinations[1] - 1],
                        new int[] { Coordinations[0] - 1, Coordinations[1] - 1 });
        }
    }

    private int[] GetClickedCoordinations(Space Cell) {

        int[] Coordinations = new int[2];
        for (int y = 0; y < Game.Y; y++) {
            for (int x = 0; x < Game.X; x++) {
                if (board[y][x] == Cell) {
                    Coordinations[0] = y;
                    Coordinations[1] = x;
                    break;
                }
            }
        }
        return Coordinations;
    }

    private boolean hasPlayerWon() {
        if (Game.X * Game.Y - ClearedSpaces == Game.BOMB_COUNT)
            return true;
        return false;
    }
    // #region Events

    @Override
    public void mouseClicked(MouseEvent e) {
        Space Source = (Space) e.getSource();
        try {
            if (e.getButton() == 1) {
                if (Source.isFlagged() || Source.isCleared())
                    return;

                if (Source.hasBomb()) {
                    Source.setImage("Images/MineSweeperimgs/bomb.png");
                    Source.Clear();
                    int Option = JOptionPane.showOptionDialog(null,
                            "You Have lost !\n\nWould you like to start a new game ?", "New Game",
                            0, 0, null, null, null);
                    if (Option == 0)
                        Main.NewGame();
                } else {
                    if (Source.getBombNearBy() > 0) {
                        Source.setImage("Images/MineSweeperimgs/" + Source.getBombNearBy() + ".png");
                        Source.Clear();
                        ClearedSpaces++;
                        if (hasPlayerWon()) {
                            int Option01 = JOptionPane.showOptionDialog(null,
                                    "You Have Won !\n\nWould you like to start a new game ?", "New Game",
                                    0, 0, null, null, null);
                            if (Option01 == 0)
                                Main.NewGame();
                        }
                    } else
                        NormalizeEmptySpaces(Source, GetClickedCoordinations(Source));
                }
            } else if (e.getButton() == 3) {
                if (!Source.isFlagged()) {
                    Source.setImage("Images/MineSweeperimgs/flagged.png");
                    Source.setFlagged(true);
                } else {
                    Source.setImage(Game.NORMAL_STATE_IMAGE);
                    Source.setFlagged(false);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    // #endregion
}
