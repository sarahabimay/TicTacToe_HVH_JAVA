import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private Board board;
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(FakeCommandLineUI clUI) {
        this.userInterface = clUI;
        this.board = new Board(new ArrayList<>(Arrays.asList()));
        this.players.put(Counter.X, new Player(Counter.X, userInterface));
        this.players.put(Counter.O, new Player(Counter.O, userInterface));
    }

    public void play() {
        board = new Board(userInterface.requestBoardSize());
        userInterface.displayBoard(board);
        Player currentPlayer = players.get(Counter.X);
        while (!userInterface.requestToContinueGame() && !board.isGameOver()) {
            board = currentPlayer.playTurn(board);
            userInterface.displayBoard(board);
            Player nextPlayer = players.get(currentPlayer.opponentMarker());
            currentPlayer = nextPlayer;
        }

        userInterface.displayResult(board.getWinner());
    }
}
