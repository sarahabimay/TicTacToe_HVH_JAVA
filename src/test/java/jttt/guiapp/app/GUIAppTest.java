package jttt.guiapp.app;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.players.Player;
import jttt.guiapp.gamemaker.GUIGameMaker;
import jttt.guiapp.gamemaker.GUIGameMakerFake;
import jttt.guiapp.javafxcomponents.GUIViewSpy;
import jttt.guiapp.players.GUIHumanPlayer;
import java.util.ArrayList;
import java.util.List;

import static jttt.core.board.Mark.*;
import static jttt.core.game.GameType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GUIAppTest {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy gameViewSpy;
    private GUIApp guiApp;
    private GUIGameMaker gameMaker;
    private GUIGameMakerFake fakeGameMaker;
    private GUIApp guiAppWithFakeDependencies;

    @Before
    public void setUp() {
        gameViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        gameMaker = new GUIGameMaker();
        fakeGameMaker = new GUIGameMakerFake();
        guiApp = new GUIApp(gameMaker, gameViewSpy);
        new JFXPanel();
        guiAppWithFakeDependencies = new GUIApp(fakeGameMaker, gameViewSpy);
    }

    @Test
    public void startGameWithGameTypeAndDimension() {
        GUIApp guiApp = new GUIApp(new GUIGameMaker(), gameViewSpy);
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        Assert.assertEquals(Player.Type.GUI, guiApp.getCurrentPlayer().getType());
    }

    @Test
    public void presentGameOptions() {
        guiApp.displayGameOptions();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void displayGUI() {
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
    }

    @Test
    public void noWinnerFound() {
        guiApp.initializeGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(false, guiApp.foundWinOrDraw());
    }

    @Test
    public void moveMadeByBoardClickStoredWithGUIHumanPlayer() {
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        guiApp.registerPlayerMove("1");
        GUIHumanPlayer humanPlayer = guiApp.getCurrentPlayer();
        assertEquals(1, humanPlayer.getNextPosition(new Board(DEFAULT_BOARD_DIMENSION)));
    }

    @Test
    public void resultIsAWin() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        GUIApp guiApp = new GUIApp(fakeGameMaker, gameViewSpy);
        guiApp.initializeGame(GUI_CVH, DEFAULT_BOARD_DIMENSION);
        assertTrue(guiApp.foundWinOrDraw());
    }

    @Test
    public void announceResultOnUI() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        GUIApp guiApp = new GUIApp(fakeGameMaker, gameViewSpy);
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertTrue(gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void tellGUIDisplayToDisableBoard() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        guiAppWithFakeDependencies.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertTrue(gameViewSpy.hasBoardBeenDisabled());
    }

    @Test
    public void handleReplayEvent() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, EMPTY
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        guiAppWithFakeDependencies.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertTrue(gameViewSpy.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        guiApp.displayGameOptions();
        assertTrue(gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void playAIVersusGUIHumanPlayer() {
        guiApp.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertThat(guiApp.foundWinOrDraw(), is(equalTo(false)));
    }

    @Test
    public void playGUIHumanVersusComputerPlayer() {
        guiAppWithFakeDependencies.initializeGame(GUI_HVC, DEFAULT_BOARD_DIMENSION);
        assertTrue(fakeGameMaker.hasInitializedGame());
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}
