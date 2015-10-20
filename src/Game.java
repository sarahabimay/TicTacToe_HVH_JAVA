import java.util.HashMap;
import java.util.Map;

public class Game {
    private Board board;
    private static final int POSITIVE_OFFSET = 1;
    private Counter currentMarker;
    private Map<Counter, Player> players = new HashMap<Counter, Player>();

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.currentMarker = Counter.X;
        this.players.put(Counter.X, player1);
        this.players.put(Counter.O, player2);
    }

    public Counter getCurrentMarker() {
        return currentMarker;
    }

    public void resetBoard() {
        board.resetBoard();
    }

    public boolean foundWin() {
        return board.findWin();
    }

    public String requestNextMove() {
        return String.format("Player(%s) please choose a position:\n", currentMarker);
    }

    public String displayBoard() {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i));
        }
        return output;
    }

    public void playNextMove(int position) {
        Player currentPlayer = players.get(currentMarker);
        board = currentPlayer.playTurn(board, position);
        currentMarker = currentPlayer.opponentMarker();
    }

    public void play() {
        playUntilGameOver();
        displayBoard();
//        displayResult();
    }

    private void playUntilGameOver() {
        Player currentPlayer = players.get(Counter.X);
        while (!board.findWin()) {
            board = currentPlayer.playTurn(board);
            currentPlayer = players.get(currentPlayer.opponentMarker());
        }
    }

    private String convertRowToString(int index, Counter cellValue) {
        String cellForDisplay = cellValue.counterForDisplay(index);
        String output = String.format("[%s]", cellForDisplay);
        if (isEndOfRow(index)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index) {
        return (index + POSITIVE_OFFSET) % board.getDimension() == 0;
    }


}
