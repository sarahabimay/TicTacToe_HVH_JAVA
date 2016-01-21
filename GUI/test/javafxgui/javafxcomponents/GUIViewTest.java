package javafxgui.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafxgui.controller.GUIAppControllerStub;
import javafxgui.gamemaker.GUIGameMaker;
import jttt.core.board.Board;
import jttt.core.game.GameType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GUIViewTest {

    private final int WINDOW_HEIGHT = 700;
    private final int WINDOW_WIDTH = 600;
    private GUIView guiView;
    private Scene scene;
    private Board defaultBoard;
    private GUIAppControllerStub controllerStub;

    @Before
    public void setUp() {
        new JFXPanel();
        defaultBoard = new Board(3);
        scene = new Scene(new StackPane(), WINDOW_HEIGHT, WINDOW_WIDTH);
        guiView = new GUIView(scene);
        controllerStub = new GUIAppControllerStub(guiView, new GUIGameMaker());
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
}
