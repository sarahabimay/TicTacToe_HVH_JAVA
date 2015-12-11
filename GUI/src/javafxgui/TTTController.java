package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import jttt.Core.Game;

public class TTTController implements Controller {
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
