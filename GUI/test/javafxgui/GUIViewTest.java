package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.GUIHumanPlayer;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static jttt.Core.Board.Mark.O;
import static jttt.Core.Board.Mark.X;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GUIViewTest {

    private final int WINDOW_HEIGHT = 700;
    private final int WINDOW_WIDTH = 600;
    private GUIView guiView;
    private Scene scene;
    private Board defaultBoard;
    private TTTControllerStub controllerStub;

    @Before
    public void setUp() {
        new JFXPanel();
        defaultBoard = new Board(3);
        scene = new Scene(new StackPane(), WINDOW_HEIGHT, WINDOW_WIDTH);
        guiView = new GUIView(scene);
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
        BorderPane borderPane = (BorderPane) scene.getRoot();
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
}
