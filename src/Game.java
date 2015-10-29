import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private Board board = new Board(new ArrayList<>(Arrays.asList()));
    ;
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(UserInterface clUI, Player player1, Player player2) {
        this.userInterface = clUI;
        this.players.put(player1.getCounter(), player1);
        this.players.put(player2.getCounter(), player2);
    }

    public void play() {
        requestBoardSize();
        executeAllPlayersMoves();
        displayResult();
        playAgain();
    }

    private void requestBoardSize() {
        board = new Board(userInterface.requestBoardSize());
    }

    private void executeAllPlayersMoves() {
        Player currentPlayer = players.get(Counter.X);
        while (!isGameOver()) {
            userInterface.displayBoard(board);
            board = currentPlayer.playTurn(board);
            Player nextPlayer = players.get(currentPlayer.opponentMarker());
            currentPlayer = nextPlayer;
        }
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    private void displayResult() {
        userInterface.displayBoard(board);
        userInterface.displayResult(board.getWinner());
    }

    private void playAgain() {
        if (userInterface.requestPlayAgain()) {
            board.resetBoard();
            play();
        }
    }
}
