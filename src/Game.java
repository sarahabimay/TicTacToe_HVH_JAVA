import java.util.HashMap;
import java.util.Map;

public class Game {
    private UserInterface userInterface;
    private Board board;
    private Map<Counter, Player> players = new HashMap<Counter, Player>();

    public Game(UserInterface userInterface, Board board, Player player1, Player player2) {
        this.userInterface = userInterface;
        this.board = board;
        this.players.put(Counter.X, player1);
        this.players.put(Counter.O, player2);
    }

    public void play() {
        do {
            clearBoard();
            playUntilGameOver();
            displayBoard();
            displayResult();
        } while (playAgain());
    }

    private void clearBoard() {
        board.clearBoard();
    }

    private boolean playAgain() {
        return userInterface.requestPlayAgain();
    }

    public String displayBoard() {
        return userInterface.displayBoard(board);
    }

    private void displayResult() {
        userInterface.displayResult(getWinner());
    }

    private Counter getWinner() {
        return board.getWinner();
    }

    private void playUntilGameOver() {
        Player currentPlayer = players.get(Counter.X);
        while (!board.gameOver()) {
            userInterface.displayBoard(board);
            board = currentPlayer.playTurn(board);
            currentPlayer = players.get(currentPlayer.opponentMarker());
        }
    }
}
