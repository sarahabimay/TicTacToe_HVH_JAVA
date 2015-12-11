package javafxgui;

import javafx.scene.Scene;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class TTTController implements Controller {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int HVH_GAMETYPE = 1;
    private GUIDisplay gameView;
    private Game game;

    public TTTController(GUIDisplay guiDisplay, Game game) {
        this.gameView = guiDisplay;
        gameView.setController(this);
        this.game = game;
    }

    public Scene displayGUI() {
        return gameView.displayGUI();
    }

    public void displayBoard() {
        gameView.displayBoard(game.getBoard());
    }

    public void displayResult() {
        if (foundWinOrDraw()){
            gameView.disableBoard();
            gameView.displayResult(game.findWinner());
        }
    }

    public void playMoveAtPosition(String id) {
        playMoveOnGameBoard(id);
        displayBoard();
        displayResult();
        displayPlayAgain();
    }

    public void createNewGame() {
        clearGameBoard();
        displayBoard();
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    private void playMoveOnGameBoard(String id) {
        game.playMove(Integer.parseInt(id));
    }

    private void displayPlayAgain() {
        if (foundWinOrDraw()){
            gameView.makePlayAgainVisible();
        }
    }

    private void clearGameBoard() {
        game = new Game(new Board(DEFAULT_BOARD_DIMENSION), HVH_GAMETYPE, new PlayerFactory());
    }
}
