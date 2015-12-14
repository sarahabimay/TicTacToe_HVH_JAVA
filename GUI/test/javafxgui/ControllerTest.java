package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int HVH_GAMETYPE = 1;
    private GUIDisplayViewSpy gameView;
    private TTTController controller;

    @Before
    public void setUp() {
        Board defaultBoard = new Board(DEFAULT_BOARD_DIMENSION);
        gameView = new GUIDisplayViewSpy(new BoardDisplay(defaultBoard));
        controller = new TTTController(gameView,new EventRegister(),
                new Game(defaultBoard, HVH_GAMETYPE, new PlayerFactory()));
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
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameView,
                new Game(board, 1, new PlayerFactory()));
        assertEquals(true, controller.foundWinOrDraw());
    }

    @Test
    public void tellGUIDisplayToDisableBoard() {
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameView,
                new Game(board, 1, new PlayerFactory()));
        controller.displayGUI();
        controller.displayResult();
        assertEquals(true, gameView.hasBoardBeenDisabled());
        assertEquals(true, gameView.hasResultBeenAnnounced());
    }

    @Test
    public void handleReplayEvent() {
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameView,
                new Game(board, 1, new PlayerFactory()));
        controller.playMoveAtPosition("9");
        assertEquals(true, gameView.hasReplayButtonBeenDisplayed());
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
        private boolean hasReplayButtonBeenDisplayed = false;

        public GUIDisplayViewSpy(BoardDisplay boardDisplay) {
            super(boardDisplay);
        }

        public Scene displayGUI() {
            hasLandingPageBeenRendered = true;
            StackPane root = new StackPane();
            return new Scene(root, 700, 675);
        }

        public void displayResult(Mark winner) {
            hasResultBeenAnnounced = true;
            disableBoard();
        }

        public GridPane displayBoard(Board board) {
            hasBoardBeenReDisplayed = true;
            return new GridPane();
        }

        public void disableBoard() {
            hasBoardBeenDisabled = true;
        }

        public void makePlayAgainVisible(){
            hasReplayButtonBeenDisplayed = true;
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

        public boolean hasReplayButtonBeenDisplayed() {
            return hasReplayButtonBeenDisplayed;
        }
    }
}
