package javafxgui;

import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

public class TTTController implements Controller {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private GUIView guiView;
    private Game game;

    public TTTController(GUIView guiView, Game game) {
        this.guiView = guiView;
        guiView.setController(this);
        this.game = game;
    }

    public Player getCurrentPlayer() {
        return game.getNextPlayer();
    }

    public void displayGUI() {
        guiView.displayGUI(game.getBoard());
    }

    public void displayBoard() {
        guiView.displayBoard(game.getBoard());
    }

    public void displayResult() {
        if (foundWinOrDraw()) {
            guiView.disableBoard(game.getBoard());
            guiView.displayResult(game.findWinner());
        }
    }

    public void playGame() {
        playMoveOnGameBoard();
        displayBoard();
        displayResult();
        displayPlayAgain();
    }

    public void createNewGame() {
        clearGameBoard();
        guiView.displayGUI(game.getBoard());
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    public void displayPlayAgain() {
        if (foundWinOrDraw()) {
            guiView.makePlayAgainVisible();
        }
    }

    private void playMoveOnGameBoard() {
        game.playCurrentPlayerMove();
    }

    private void clearGameBoard() {
        game = new Game(new Board(DEFAULT_BOARD_DIMENSION), GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory());
    }
}
