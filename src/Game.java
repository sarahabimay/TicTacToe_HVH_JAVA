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
        this.playerFactory = new PlayerFactory(userInterface);
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

    public void play() {
        requestBoardSize();
        selectPlayers(requestPlayersType());
        executeAllPlayersMoves();
        displayResult();
        playAgain();
    }

    void selectPlayers(String newPlayersType) {
        ArrayList<Player> bothPlayers = playerFactory.generatePlayersFor(newPlayersType);
        this.players.put(Counter.X, bothPlayers.get(0));
        this.players.put(Counter.O, bothPlayers.get(1));
    }

    String requestPlayersType() {
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
