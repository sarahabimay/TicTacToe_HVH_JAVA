package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafxgui.event.EventRegister;
import javafxgui.view.BoardDisplay;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.GUIHumanPlayer;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static jttt.Core.Board.Mark.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GUIViewTest {

    private final int WINDOW_HEIGHT = 700;
    private final int WINDOW_WIDTH = 600;
    private GUIView guiView;
    private Scene scene;
    private Board defaultBoard;
    private EventRegisterSpy eventRegisterSpy;
    private TTTControllerStub controllerStub;

    @Before
    public void setUp() {
        new JFXPanel();
        defaultBoard = new Board(3);
        eventRegisterSpy = new EventRegisterSpy();
        scene = new Scene(new StackPane(), WINDOW_HEIGHT, WINDOW_WIDTH);
        guiView = new GUIView(scene, new BoardDisplay(), eventRegisterSpy);
//        this.scene = guiView.displayGUI(defaultBoard);
        controllerStub = new TTTControllerStub(guiView);
    }

    @Test
    public void displayGameOptions() {
        guiView.displayGameOptions();
        assertEquals("gameOptions", scene.getRoot().getId());
    }

    @Test
    public void getBoardComponentForDisplay() {
        guiView.prepareGameForStart(GameType.GUI_HVC);
        BorderPane borderPane = (BorderPane)scene.getRoot();
        assertEquals("gameBoard", borderPane.getCenter().getId());
        assertEquals(true, controllerStub.hasGameStartBeenCalled());
    }

    @Test
    public void initialDisplay() {
        guiView.displayGameLayoutComponent(defaultBoard);
        BorderPane borderPane = (BorderPane) scene.getRoot();
        assertEquals("borderPane", borderPane.getId());
    }

    @Test
    public void displayHasTitle() {
        HBox titleBar = guiView.titleHeader();
        assertEquals("titleBar", titleBar.getId());

        Text gameTitle = (Text) titleBar.getChildren().get(0);
        assertEquals(guiView.GAME_HEADER, gameTitle.getText());
    }

    @Test
    public void displayFooterBar() {
        VBox footer = guiView.resultFooter();
        assertEquals("footer", footer.getId());
        assertEquals("resultTarget", footer.getChildren().get(0).getId());
    }

    @Test
    public void displayWinningResult() {
        guiView.displayGameLayoutComponent(defaultBoard);
        guiView.displayResult(X);
        assertEquals(String.format(guiView.WINNER_ANNOUNCEMENT, "X"), guiView.announceWinner(X));
        assertEquals(String.format(guiView.WINNER_ANNOUNCEMENT, "X"), guiView.createResultAnnouncement(X));
    }

    @Test
    public void displayResult() {
        guiView.displayGameLayoutComponent(defaultBoard);
        guiView.displayResult(EMPTY);
        assertEquals(guiView.DRAW_ANNOUNCEMENT, guiView.announceDraw());
        assertEquals(guiView.DRAW_ANNOUNCEMENT, guiView.createResultAnnouncement(EMPTY));
    }

    @Test
    public void createPlayAgainButtonTarget() {
        Button playAgain = guiView.createPlayAgainButtonTarget();
        assertEquals("playAgain", playAgain.getId());
        assertEquals(false, playAgain.isVisible());
    }

    @Test
    public void displayPlayAgainButton() {
        guiView.displayGameLayoutComponent(defaultBoard);
        Button replayButton = (Button) guiView.lookup("#playAgain");
        assertEquals(false, replayButton.isVisible());
        guiView.makePlayAgainVisible();
        assertThat(replayButton, notNullValue());
        assertEquals(true, replayButton.isVisible());
    }

    @Test
    public void getCurrentGUIHumanPlayer() {
        guiView.setController(new TTTControllerStub(guiView,
                new Game(new Board(3),
                        GameType.GUI_HVH.getNumericGameType(),
                        new PlayerFactory())));
        Player currentPlayer = guiView.getCurrentPlayer();
        assertEquals(GUIHumanPlayer.class, currentPlayer.getClass());
        assertEquals(X, currentPlayer.getMark());
        assertEquals(O, currentPlayer.opponentCounter());
    }

    @Test
    public void registerAllElementsWithEventHandler() {
        guiView.displayGameLayoutComponent(defaultBoard);
        assertEquals(true, eventRegisterSpy.hasAllElementsBeenRegistered());
    }

    private class EventRegisterSpy extends EventRegister {
        private boolean hasRegisteredAllBoardButtons = false;
        private boolean hasRegisteredAllClickableElements = false;

        @Override
        public void registerAllBoardButtonsWithHandler(GridPane board, GUIView guiView) {
            hasRegisteredAllBoardButtons = true;
        }

        @Override
        public void registerAllClickableElementsWithHandler(Scene scene, GUIView guiView) {
            hasRegisteredAllClickableElements = true;
        }

        public boolean haveBoardButtonsBeenRegistered() {
            return hasRegisteredAllBoardButtons;
        }

        public boolean hasAllElementsBeenRegistered() {
            return hasRegisteredAllClickableElements;
        }
    }
}
