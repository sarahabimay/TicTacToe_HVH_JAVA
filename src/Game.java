import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    private UserInterface userInterface;
    private String gameType;
    private Board board = new Board(new ArrayList<>(Arrays.asList()));
    private HashMap<Counter, Player> players = new HashMap<>();

    public Game(UserInterface clUI, Player player1, Player player2) {
        this.userInterface = clUI;
        this.players.put(player1.getCounter(), player1);
        this.players.put(player2.getCounter(), player2);
    }

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public String typeOfGame() {
        return gameType;
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

    public String displayBoard() {
        return userInterface.displayBoard(board);
    }

    public void requestBoardSize() {
        board = new Board(userInterface.requestBoardSize());
    }

    public void play() {
        requestBoardSize();
        createPlayers(requestGameType());
        executeAllPlayersMoves();
        displayResult();
        playAgain();
    }

    private void createPlayers(Integer newGameType) {

        if (gameType == "HVH") {
            this.players.put(Counter.X, new HumanPlayer(Counter.X, Player.Type.Human, userInterface));
            this.players.put(Counter.O, new HumanPlayer(Counter.O, Player.Type.Human, userInterface));
        } else if (gameType == "HVC") {
            this.players.put(Counter.X, new HumanPlayer(Counter.X, Player.Type.Human, userInterface));
            this.players.put(Counter.O, new ComputerPlayer(Counter.O, Player.Type.Computer, userInterface));
        } else if (gameType == "CVH") {
            System.out.println("CVH");
            this.players.put(Counter.X, new ComputerPlayer(Counter.X, Player.Type.Computer, userInterface));
            this.players.put(Counter.O, new HumanPlayer(Counter.O, Player.Type.Human, userInterface));
        }
    }

    private Integer requestGameType() {
        Integer gameTypeOption = userInterface.requestGameType();
        if (gameTypeOption == 1) {
            gameType = "HVH";
        } else if (gameTypeOption == 2) {
            gameType = "HVC";
        } else if (gameTypeOption == 3) {
            gameType = "CVH";
        }
        return gameTypeOption;
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
