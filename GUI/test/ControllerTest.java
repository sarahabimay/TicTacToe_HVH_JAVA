import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafxgui.Controller;
import javafxgui.GUIDisplay;
import javafxgui.TTTController;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private GUIDisplayViewSpy gameView;
    private TTTController controller;

    @Before
    public void setUp() {
        gameView = new GUIDisplayViewSpy();
        controller = new TTTController(gameView, new Game(new Board(3), 1, new PlayerFactory()));
    }

    @Test
    public void displayBoard() {
        controller.displayGUI();
        assertEquals(true, gameView.hasLandingPageBeenRendered());
    }

    @Test
    public void playMoveSelectedByHuman() {
        controller.playMoveAtPosition("1");
        assertEquals(true, gameView.hasBoardBeenReDisplayed());
    }

    @Test
    public void checkForResultUnsuccessful() {
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void resultIsAWin() {
        Mark X = Mark.X;
        Mark O = Mark.O;
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameView,
                new GameFake(board, 1, new PlayerFactory()));
        assertEquals(true, controller.foundWinOrDraw());
    }

    @Test
    public void tellGUIDisplayToDisableBoard() {
        Mark X = Mark.X;
        Mark O = Mark.O;
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameView,
                new GameFake(board, 1, new PlayerFactory()));
        controller.displayGUI();
        controller.displayResult();
        assertEquals(true, gameView.hasBoardBeenDisabled());
        assertEquals(true, gameView.hasResultBeenAnnounced());
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

    private class GUIDisplayViewSpy extends GUIDisplay {
        private boolean hasLandingPageBeenRendered = false;
        private boolean hasBoardBeenReDisplayed = false;
        private boolean hasBoardBeenDisabled = false;
        private boolean hasResultBeenAnnounced = false;

        public GUIDisplayViewSpy() {
            super();
        }

        public Scene displayGUI() {
            hasLandingPageBeenRendered = true;
            StackPane root = new StackPane();
            return new Scene(root, 700, 675);
        }

        public void displayResult(Mark winner) {
            hasResultBeenAnnounced = true;
        }

        public GridPane displayBoard(Board board) {
            hasBoardBeenReDisplayed = true;
            return new GridPane();
        }

        public void disableBoard() {
            hasBoardBeenDisabled = true;
        }

        public boolean hasBoardBeenReDisplayed() {
            return hasBoardBeenReDisplayed;
        }

        public boolean hasLandingPageBeenRendered() {
            return hasLandingPageBeenRendered;
        }

        public boolean hasBoardBeenDisabled() {
            return hasBoardBeenDisabled;
        }

        public boolean hasResultBeenAnnounced() {
            return hasResultBeenAnnounced;
        }
    }

    private class GameFake extends Game {
        public GameFake(Board board, int gameType, PlayerFactory playerFactory) {
            super(board, gameType, playerFactory);
        }

    }
}
