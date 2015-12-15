package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class TTTController implements Controller {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int HVH_GAMETYPE = 1;
    private EventRegister eventRegister;
    private GUIDisplay gameView;
    private Game game;

    public TTTController(GUIDisplay gameView, EventRegister eventRegister, Game game) {
        this.gameView = gameView;
        this.eventRegister = eventRegister;
        this.game = game;;
    }

    public Scene displayGUI() {
        Scene scene = gameView.displayGUI(game.getBoard());
        registerEventHandlers(scene);
        return scene;
    }

    public GridPane displayBoard() {
        return gameView.displayBoard(game.getBoard());
    }

    public void displayResult() {
        if (foundWinOrDraw()){
            gameView.disableBoard(game.getBoard());
            gameView.displayResult(game.findWinner());
        }
    }

    public void playMoveAtPosition(String id) {
        playMoveOnGameBoard(id);
        registerBoardEventHandlers(displayBoard());
        displayResult();
        displayPlayAgain();
    }

    public void createNewGame() {
        clearGameBoard();
        registerEventHandlers(gameView.displayGUI(game.getBoard()));
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    public void displayPlayAgain() {
        if (foundWinOrDraw()){
            gameView.makePlayAgainVisible();
        }
    }

    private void registerEventHandlers(Scene scene) {
        eventRegister.registerAllClickableElementsWithHandler(scene, this);
    }

    private void registerBoardEventHandlers(GridPane board) {
        eventRegister.registerAllBoardButtonsWithHandler(board, this);
    }

    private void playMoveOnGameBoard(String id) {
        game.playMove(Integer.parseInt(id));
    }

    private void clearGameBoard() {
        game = new Game(new Board(DEFAULT_BOARD_DIMENSION), HVH_GAMETYPE, new PlayerFactory());
        gameView = new GUIDisplay(gameView.getScene(), new BoardDisplay());
    }
}
