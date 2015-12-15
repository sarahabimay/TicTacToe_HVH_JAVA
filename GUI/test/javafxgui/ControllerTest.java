package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        controller = new TTTController(gameView, new EventRegister(),
                new Game(defaultBoard, HVH_GAMETYPE, new PlayerFactory()));
        new JFXPanel();
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
                gameView, new EventRegister(),
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
                gameView, new EventRegister(),
                new Game(board, 1, new PlayerFactory()));
        controller.displayGUI();
        controller.displayResult();
        assertEquals(true, gameView.hasBoardBeenDisabled());
        assertEquals(true, gameView.hasResultBeenAnnounced());
    }

    @Test
    public void registerAllBoardButtonsWithEventHandler() {
        new JFXPanel();
        EventRegisterSpy eventRegisterSpy = new EventRegisterSpy();
        Board board = new Board(3);
        Controller controller = new TTTController(
                new GUIDisplay(new BoardDisplay(board)), eventRegisterSpy,
                new Game(board, 1, new PlayerFactory()));
        controller.displayGUI();
        assertEquals(true, eventRegisterSpy.haveBoardButtonsBeenRegistered());
        assertEquals(true, eventRegisterSpy.hasAllElementsBeenRegistered());
        assertEquals(10, eventRegisterSpy.howManyButtonsRegistered());
    }

    @Test
    public void registerReplayButtonWithEventHandler() {
        new JFXPanel();
        EventRegisterSpy eventRegisterSpy = new EventRegisterSpy();
        Board board = new Board(3);
        Controller controller = new TTTController(
                new GUIDisplay(new BoardDisplay(board)), eventRegisterSpy,
                new Game(board, 1, new PlayerFactory()));
        controller.displayGUI();
        assertEquals(true, eventRegisterSpy.hasReplayButtonBeenRegistered());
        assertEquals(true, eventRegisterSpy.hasAllElementsBeenRegistered());
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
                new EventRegister(),
                new Game(board, 1, new PlayerFactory()));
        controller.playMoveAtPosition("9");
        assertEquals(true, gameView.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Game game = new Game(board, 1, new PlayerFactory());
        Controller controller = new TTTController(
                gameView,
                new EventRegister(),
                game);
        controller.playMoveAtPosition("9");
        assertEquals(true, controller.foundWinOrDraw());
        controller.createNewGame();
        assertEquals(false, controller.foundWinOrDraw());
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
        private Scene scene;
        private StackPane root;

        public GUIDisplayViewSpy(BoardDisplay boardDisplay) {
            super(boardDisplay);
            this.root = new StackPane();
            this.scene = new Scene(root, 700, 675);
            scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        }

        public Scene displayGUI() {
            hasLandingPageBeenRendered = true;
            root.getChildren().add(generateBorderLayout());
            return scene;
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

        public void makePlayAgainVisible() {
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

    private class EventRegisterSpy extends EventRegister {
        private boolean hasRegisteredAllBoardButtons = false;
        private boolean hasRegisteredAllClickableElements = false;
        private int countOfBoardButtonsRegistered = 0;
        private boolean hasReplayButtonBeenRegistered= false;

        @Override
        public void registerClickableElementWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
            countOfBoardButtonsRegistered++;
            super.registerClickableElementWithHandler(clickableElement, eventHandler);
        }

        @Override
        public void registerAllBoardButtonsWithHandler(GridPane board, Controller controller) {
            hasRegisteredAllBoardButtons = true;
            super.registerAllBoardButtonsWithHandler(board, controller);
        }

        @Override
        public void registerAllClickableElementsWithHandler(Scene scene, Controller controller) {
            hasRegisteredAllClickableElements = true;
            super.registerAllClickableElementsWithHandler(scene, controller);
        }

        @Override
        public void registerReplayButtonWithHandler(Button replayButton, Controller controller) {
            hasReplayButtonBeenRegistered = true;
            super.registerReplayButtonWithHandler(replayButton, controller);
        }

        public boolean haveBoardButtonsBeenRegistered() {
            return hasRegisteredAllBoardButtons;
        }

        public int howManyButtonsRegistered() {
            return countOfBoardButtonsRegistered;
        }

        public boolean hasAllElementsBeenRegistered() {
            return hasRegisteredAllClickableElements;
        }

        public boolean hasReplayButtonBeenRegistered() {
            return hasReplayButtonBeenRegistered;
        }
    }
}
