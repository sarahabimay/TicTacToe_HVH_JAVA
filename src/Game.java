import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private PlayerFactory playerFactory;
    private Board board = new Board(new ArrayList<>(Arrays.asList()));
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.playerFactory = new PlayerFactory();
    }

    public Game(FakeCommandLineUI fakeUI, FakePlayerFactory fakePlayerFactory) {
        this.userInterface = fakeUI;
        this.playerFactory = fakePlayerFactory;
    }

    public Player.Type getPlayerType(Counter counter) {
        return players.get(counter).getPlayerType();
    }

    public Board nextPlayerMakesMove(Counter nextCounter) {
        Player currentPlayer = players.get(nextCounter);
        board = currentPlayer != null ? currentPlayer.playTurn(board) : board;
        return board;
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public Counter getNextCounter(Counter counter) {
        return counter.opponentCounter();
    }

    public void requestBoardSize() {
        board = new Board(userInterface.requestBoardSize());
    }

    public String displayBoard() {
        return userInterface.displayBoard(board);
    }

    public void play() {
        requestBoardSize();
        selectPlayers(requestGameType());
        executeAllPlayersMoves();
        displayResult();
        playAgain();
    }

    public void selectPlayers(String newGameType) {
        ArrayList<Player> bothPlayers = playerFactory.generatePlayersFor(newGameType, userInterface);
        this.players.put(Counter.X, bothPlayers.get(0));
        this.players.put(Counter.O, bothPlayers.get(1));
    }

    public String requestGameType() {
        return userInterface.requestGameType();
    }

    private void executeAllPlayersMoves() {
        Counter currentCounter = Counter.X;
        while (!isGameOver()) {
            userInterface.displayBoard(board);
            nextPlayerMakesMove(currentCounter);
            currentCounter = getNextCounter(currentCounter);
        }
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
