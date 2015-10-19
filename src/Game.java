import java.util.ArrayList;

public class Game {
    private Board board;
    public Game(Board board) {
        this.board = board;
    }

    public String startGame() {
        return "[1][2][3]\n" +
                "[4][5][6]\n" +
                "[7][8][9]\n\n" +
        "Player 1 (X) please enter your position [1-9]:";
    }
}
