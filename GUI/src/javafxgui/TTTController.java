package javafxgui;

import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class TTTController implements Controller {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int HVH_GAMETYPE = 1;
    private GUIView guiView;
    private Game game;

    public TTTController(GUIView guiView, Game game) {
        this.guiView = guiView;
        guiView.setController(this);
        this.game = game;
    }

    public void displayGUI() {
        guiView.displayGUI(game.getBoard());
    }

    public void displayBoard() {
        guiView.displayBoard(game.getBoard());
    }

    public void displayResult() {
        if (foundWinOrDraw()){
            guiView.disableBoard(game.getBoard());
            guiView.displayResult(game.findWinner());
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
        guiView.displayGUI(game.getBoard());
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    public void displayPlayAgain() {
        if (foundWinOrDraw()){
            guiView.makePlayAgainVisible();
        }
    }

    private void playMoveOnGameBoard(String id) {
        game.playMove(Integer.parseInt(id));
    }

    private void clearGameBoard() {
        game = new Game(new Board(DEFAULT_BOARD_DIMENSION), HVH_GAMETYPE, new PlayerFactory());
//        guiView = new GUIView(guiView.getScene(), new BoardDisplay(), new EventRegister());
    }
}
