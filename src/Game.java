import java.util.HashMap;
import java.util.Map;

public class Game {
    private Board board;
    private static final int POSITIVE_OFFSET = 1;
    private static final int NEGATIVE_OFFSET = -1;
    private Counter currentMarker;
    private Map<Counter, Player> players = new HashMap<Counter, Player>();

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.currentMarker = Counter.X;
        this.players.put(Counter.X, player1);
        this.players.put(Counter.O, player2);
    }

    public void resetBoard() {
        board.resetBoard();
    }

    public String displayBoard() {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i));
        }
        return output;
    }

    private String convertRowToString(int index, Counter cellValue) {
        String cellValueAsString = cellValue.isEmpty() ? String.valueOf(index + POSITIVE_OFFSET) : cellValue.name();
        String output = String.format("[%s]", cellValueAsString);
        if (isEndOfRow(index)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index) {
        return (index + POSITIVE_OFFSET) % board.getDimension() == 0;
    }

    public void playNextMove(int position) {
        board.playCounterInPosition(position, currentMarker);
        currentMarker = opponentMarker(currentMarker);
    }

    private Counter opponentMarker(Counter currentMarker) {
        return currentMarker == Counter.X ? Counter.O : Counter.X;
    }

    public String requestNextMove() {
        return String.format("Player(%s) please choose a position:\n", currentMarker);
    }

    public Counter getCurrentMarker() {
        return currentMarker;
    }

    public boolean foundWin() {
        return board.findWin();
    }

    public void play() {
        playUntilGameOver();
        displayBoard();
//        displayResult();
    }

    private void playUntilGameOver() {
        Player currentPlayer = players.get(currentMarker);
        System.out.println("Board before move: "+ displayBoard());
        while(!board.findWin()){
            board = currentPlayer.playTurn(board);
            System.out.println("board: "+ displayBoard());
            currentPlayer = players.get(currentPlayer.opponentMarker());
        }
    }
}
