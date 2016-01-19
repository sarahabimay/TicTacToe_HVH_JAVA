package javafxgui.controller;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafxgui.gamemaker.GUIGameMaker;
import javafxgui.gamemaker.GUIGameMakerFake;
import javafxgui.players.GUIPlayerFactory;
import javafxgui.players.GUIPlayerFactoryFake;
import javafxgui.javafxcomponents.GUIViewSpy;
import jttt.core.board.Mark;
import jttt.core.players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jttt.core.board.Mark.*;
import static jttt.core.game.GameType.*;
import static org.junit.Assert.assertEquals;

public class TTTControllerTest {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy gameViewSpy;
    private TTTController controller;
    private GUIGameMaker gameMaker;

    @Before
    public void setUp() {
        gameViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        gameMaker = new GUIGameMaker(new GUIPlayerFactory());
        controller = new TTTController(gameViewSpy, gameMaker);
        new JFXPanel();
    }

    @Test
    public void startGameWithGameTypeAndDimension() {
        Controller controller = new TTTController(gameViewSpy, new GUIGameMaker(new GUIPlayerFactory()));
        controller.startGame(GUI_HVC, DEFAULT_BOARD_DIMENSION);
        assertEquals(Player.Type.GUI, controller.getCurrentPlayer().getType());
        assertEquals(false, gameViewSpy.hasResultBeenAnnounced());
    }

    @Test
    public void presentGameOptions() {
        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.presentGameOptions();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void displayGUI() {
        controller.initializeGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        controller.displayGameLayout();
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
    }

    @Test
    public void checkForResultUnsuccessful() {
        controller.initializeGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void resultIsAWin() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        GUIGameMakerFake gameMaker = new GUIGameMakerFake(new GUIPlayerFactory());
        gameMaker.setDummyBoard(arrayToList(currentBoard));

        Controller controller = new TTTController(gameViewSpy, gameMaker);
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

        GUIGameMakerFake gameMaker = new GUIGameMakerFake(new GUIPlayerFactory());
        gameMaker.setDummyBoard(arrayToList(currentBoard));

        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.initializeGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
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

        GUIGameMakerFake gameMaker = new GUIGameMakerFake(new GUIPlayerFactory());
        gameMaker.setDummyBoard(arrayToList(currentBoard));
        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.startGame(GUI_HVH, 9);
        assertEquals(true, gameViewSpy.hasReplayButtonBeenDisplayed());
    }

    @Test
    public void clickingReplayButtonGeneratesANewGame() {
        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.startGame(GUI_HVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameViewSpy.hasGameLayoutBeenRendered());
        assertEquals(false, gameViewSpy.hasGameOptionsBeenPresented());
        controller.createNewGame();
        assertEquals(true, gameViewSpy.hasGameOptionsBeenPresented());
    }

    @Test
    public void playAIVersusGUIHumanPlayer() {
        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.startGame(GUI_CVH, DEFAULT_BOARD_DIMENSION);
        assertEquals(false, controller.foundWinOrDraw());
    }

    @Test
    public void playGUIHumanVersusComputerPlayer() {
        GUIPlayerFactoryFake playerFactory = new GUIPlayerFactoryFake();
        String guiMove = "3";
        playerFactory.setNextGUIHumanMove(guiMove);
        GUIGameMakerFake gameMaker = new GUIGameMakerFake(playerFactory);
        Controller controller = new TTTController(gameViewSpy, gameMaker);
        controller.initializeGame(GUI_HVC, DEFAULT_BOARD_DIMENSION);
        assertEquals(true, gameMaker.hasInitializedGame());
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}
