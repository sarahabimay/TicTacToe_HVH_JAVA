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

    public void requestBoardSize() {
        board = new Board(userInterface.requestBoardSize());
    }

    private void executeAllPlayersMoves() {
        Counter currentCounter = Counter.X;
        while (!isGameOver()) {
            userInterface.displayBoard(board);
            nextPlayerMakesMove(currentCounter);
            currentCounter = getNextCounter(currentCounter);
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

    public Board nextPlayerMakesMove(Counter nextCounter) {
        Player currentPlayer = players.get(nextCounter);
        board = currentPlayer.playTurn(board);
        return board;
    }

    public Counter getNextCounter(Counter counter) {
        return counter.opponentCounter();
    }
}
