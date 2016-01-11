package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;
import jttt.Core.Players.PlayerFactoryFake;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jttt.Core.Board.Mark.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TTTControllerTest {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy gameViewSpy;
    private TTTController controller;

    @Before
    public void setUp() {
        gameViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        controller = new TTTController(gameViewSpy,
                new Game(new Board(DEFAULT_BOARD_DIMENSION), GameType.GUI_HVH.getNumericGameType(), new PlayerFactory()));
        new JFXPanel();
    }

    @Test
    public void startGameWithGameTypeAndDimension() {
        Controller controller = new TTTController(gameViewSpy);
        controller.startGame(GameType.GUI_HVC, DEFAULT_BOARD_DIMENSION);
        assertEquals(Player.Type.GUI, controller.getCurrentPlayer().getType());
        assertEquals(false, gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void presentGameOptions() {
        Controller controller = new TTTController(gameViewSpy);
        controller.presentGameOptions();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void displayGUI() {
        controller.displayGameLayout();
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
    }

    @Test
    public void checkForResultUnsuccessful() {
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void resultIsAWin() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Controller controller = new TTTController(
                gameViewSpy,
                new Game(board, 1, new PlayerFactory()));
        assertEquals(true, controller.foundWinOrDraw());
    }

    @Test
    public void tellGUIDisplayToDisableBoard() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getNumericGameType(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);

        controller.displayGameLayout();
        controller.displayResult();
        assertEquals(true, gameViewSpy.hasBoardBeenDisabled());
        assertEquals(true, gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void handleReplayEvent() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, EMPTY
        };

        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getNumericGameType(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);

        controller.playGame();
        assertEquals(true, gameViewSpy.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, EMPTY
        };
        Board board = new Board(DEFAULT_BOARD_DIMENSION, arrayToList(currentBoard));
        Game game = new Game(board, GameType.GUI_HVH.getNumericGameType(), new PlayerFactory());
        Controller controller = new TTTController(gameViewSpy, game);
        controller.playGame();
        assertEquals(true, controller.foundWinOrDraw());
        controller.createNewGame();
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void playAIVersusGUIHumanPlayer() {
        Board board = new Board(DEFAULT_BOARD_DIMENSION);
        PlayerFactoryFake playerFactoryFake = new PlayerFactoryFake();
        String guiMove = "3";
        playerFactoryFake.setNextGUIHumanMove(guiMove);
        Game game = new Game(board, GameType.GUI_CVH.getNumericGameType(), playerFactoryFake);
        Controller controller = new TTTController(gameViewSpy, game);
        controller.playGame();
        assertEquals(false, controller.foundWinOrDraw());
        assertEquals(2, game.getBoard().findPositions(X).size());
        assertThat(game.getBoard().findPositions(O), contains(zeroIndexed(guiMove)));
    }

    @Test
    public void playGUIHumanVersusComputerPlayer() {
        PlayerFactoryFake playerFactoryFake = new PlayerFactoryFake();
        String guiMove = "3";
        playerFactoryFake.setNextGUIHumanMove(guiMove);

        Game game = new Game(
                new Board(DEFAULT_BOARD_DIMENSION),
                GameType.GUI_HVC.getNumericGameType(),
                playerFactoryFake);
        Controller controller = new TTTController(gameViewSpy, game);
        controller.playGame();
        assertEquals(false, controller.foundWinOrDraw());
        assertThat(game.getBoard().findPositions(X), contains(zeroIndexed(guiMove)));
        assertThat(game.getBoard().findPositions(O), contains(zeroIndexed("5")));
    }

    private int zeroIndexed(String guiMove) {
        return Integer.parseInt(guiMove) - 1;
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}
