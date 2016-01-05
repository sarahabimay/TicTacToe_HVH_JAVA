package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TTTControllerTest {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private GUIViewSpy gameViewSpy;
    private TTTController controller;

    @Before
    public void setUp() {
        gameViewSpy = new GUIViewSpy(new Scene(new StackPane(), 700, 600), new BoardDisplay(), null);
        controller = new TTTController(gameViewSpy,
                new Game(new Board(DEFAULT_BOARD_DIMENSION), GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory()));
        new JFXPanel();
    }

    @Test
    public void displayBoard() {
        controller.displayGUI();
        assertEquals(true, gameViewSpy.hasLandingPageBeenRendered());
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
                gameViewSpy,
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

        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);

        controller.displayGUI();
        controller.displayResult();
        assertEquals(true, gameViewSpy.hasBoardBeenDisabled());
        assertEquals(true, gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void handleReplayEvent() {
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.EMPTY
        };

        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);

        controller.playGame();
        assertEquals(true, gameViewSpy.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        Mark currentBoard[] = {
                Mark.X, Mark.O, Mark.O,
                Mark.X, Mark.O, Mark.X,
                Mark.X, Mark.X, Mark.EMPTY
        };
        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);
        controller.playGame();
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

}
