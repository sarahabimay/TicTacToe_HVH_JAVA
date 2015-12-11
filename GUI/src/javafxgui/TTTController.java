package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

    public void createNewGame() {
        game = new Game(new Board(DEFAULT_BOARD_DIMENSION), HVH_GAMETYPE, new PlayerFactory());
        displayBoard();
    }

    public void playMoveAtPosition(String id) {
        game.playMove(Integer.parseInt(id));
        displayBoard();
        displayResult();
        playAgain();
    }

    private void playAgain() {
        if (foundWinOrDraw()){
            gameView.makePlayAgainVisible();
        }
    }

    public void displayResult() {
        if (foundWinOrDraw()){
            gameView.disableBoard();
            gameView.displayResult(game.findWinner());
        }
    }

    public GridPane displayBoard() {
        return gameView.displayBoard(game.getBoard());
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }
}
