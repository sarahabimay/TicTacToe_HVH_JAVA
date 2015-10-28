import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private Board board = new Board(new ArrayList<>(Arrays.asList()));;
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(FakeCommandLineUI clUI, Player player1, Player player2) {
        this.userInterface = clUI;
        this.players.put(player1.getCounter(), player1);
        this.players.put(player2.getCounter(), player2);
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
