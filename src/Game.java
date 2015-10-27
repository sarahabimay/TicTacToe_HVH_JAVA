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
        playUntilGameOver();
        displayResult();
        playAgain();
    }

//    private void restart() {
//        UserInterface ui = new UserInterface();
//        new Game(ui, new Board(ui.requestBoardSize()), new PlayerFactory(ui.getPlayersChoice));
//    }

    private boolean playAgain() {
        if (userInterface.requestPlayAgain()) {
            UserInterface ui = new CommandLineUI();
            Game game = new Game(ui, new Board(ui.requestBoardSize()), new Player(Counter.X, ui), new Player(Counter.O, ui));
            game.play();
        }
        return true;
    }

    private void displayResult() {
        userInterface.displayResult(getWinner());
    }

    private Counter getWinner() {
        return board.getWinner();
    }

    private void playUntilGameOver() {
        Player currentPlayer = players.get(Counter.X);
        while (!board.isGameOver()) {
            userInterface.displayBoard(board);
            Player nextPlayer = playTurn(currentPlayer);
            currentPlayer = nextPlayer;
        }
        userInterface.displayBoard(board);
    }

    Player playTurn(Player currentPlayer) {
        currentPlayer.playTurn(board);
        return players.get(currentPlayer.opponentMarker());
    }
}
