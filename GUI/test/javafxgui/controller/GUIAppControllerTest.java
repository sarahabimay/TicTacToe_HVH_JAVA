package javafxgui.controller;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafxgui.gamemaker.GUIGameMaker;
import javafxgui.gamemaker.GUIGameMakerFake;
import javafxgui.javafxcomponents.GUIViewSpy;
import javafxgui.players.GUIHumanPlayer;
import javafxgui.players.GUIPlayerFactoryFake;
import jttt.core.board.Mark;
import jttt.core.players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jttt.core.board.Mark.*;
import static jttt.core.game.GameType.*;
import static org.junit.Assert.assertEquals;

public class GUIAppControllerTest {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy gameViewSpy;
    private GUIAppController controller;
    private GUIGameMaker gameMaker;
    private GUIGameMakerFake fakeGameMaker;

    @Before
    public void setUp() {
        gameViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        gameMaker = new GUIGameMaker();
        fakeGameMaker = new GUIGameMakerFake();
        controller = new GUIAppController(gameMaker, gameViewSpy);
        new JFXPanel();
    }

    @Test
    public void startGameWithGameTypeAndDimension() {
        GUIAppController controller = new GUIAppController(new GUIGameMaker(), gameViewSpy);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(Player.Type.GUI, controller.getCurrentPlayer().getType());
        assertEquals(false, gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void presentGameOptions() {
        GUIAppController controller = new GUIAppController(gameMaker, gameViewSpy);
        controller.displayGameOptions();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void displayGUI() {
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
    }

    @Test
    public void noWinnerFound() {
        controller.initializeGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void moveMadeByBoardClickStoredWithGUIHumanPlayer() {
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        controller.registerPlayerMove("1");
        GUIHumanPlayer humanPlayer = (GUIHumanPlayer)controller.getCurrentPlayer();
        assertEquals(true, humanPlayer.hasNewMove());
    }

    @Test
    public void resultIsAWin() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        GUIAppController controller = new GUIAppController(fakeGameMaker, gameViewSpy);
        controller.initializeGame(GUI_CVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, controller.foundWinOrDraw());
    }

    @Test
    public void tellGUIDisplayToDisableBoard() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));

        GUIAppController controller = new GUIAppController(fakeGameMaker, gameViewSpy);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
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

        fakeGameMaker.setDummyBoard(arrayToList(currentBoard));
        GUIAppController controller = new GUIAppController(fakeGameMaker, gameViewSpy);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameViewSpy.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        GUIAppController controller = new GUIAppController(gameMaker, gameViewSpy);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
        assertEquals(false, gameViewSpy.hasGameOptionsBeenPresented());
        controller.displayGameOptions();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void playAIVersusGUIHumanPlayer() {
        GUIAppController controller = new GUIAppController(gameMaker, gameViewSpy);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void playGUIHumanVersusComputerPlayer() {
        GUIPlayerFactoryFake playerFactory = new GUIPlayerFactoryFake();
        String guiMove = "3";
        playerFactory.setNextGUIHumanMove(guiMove);
        GUIAppController controller = new GUIAppController(fakeGameMaker, gameViewSpy);
        controller.initializeGame(GUI_HVC, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, fakeGameMaker.hasInitializedGame());
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}
