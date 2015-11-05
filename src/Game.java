import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private PlayerFactory playerFactory;
    private Board board = new Board(new ArrayList<>());
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.playerFactory = new PlayerFactory(userInterface);
    }

    public Player getPlayer(Counter counter) {
        return players.get(counter);
    }

    public Board nextPlayerMakesMove(Counter nextCounter) {
        Player currentPlayer = players.get(nextCounter);
        return currentPlayer.playTurn(board);
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

    public void selectPlayers(Integer newGameType) {
        ArrayList<Player> bothPlayers = playerFactory.generatePlayersFor(newGameType);
        this.players.put(Counter.X, bothPlayers.get(0));
        this.players.put(Counter.O, bothPlayers.get(1));
    }

    public Integer requestGameType() {
        return userInterface.requestGameType();
    }

    private void executeAllPlayersMoves() {
        Counter currentCounter = Counter.X;
        while (!isGameOver()) {
            userInterface.displayBoard(board);
            board = nextPlayerMakesMove(currentCounter);
            userInterface.printCurrentCounter(currentCounter);
            currentCounter = getNextCounter(currentCounter);
        }
    }

    private void displayResult() {
        userInterface.displayBoard(board);
        userInterface.displayResult(board.findWinner());
    }

    private void playAgain() {
        if (userInterface.requestPlayAgain()) {
            board.resetBoard();
            play();
        }
    }
}
