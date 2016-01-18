package javafxgui.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafxgui.view.GUIViewSpy;
import javafxgui.event.NewPlayerMoveEventHandlerSpy;
import jttt.core.board.Board;
import org.junit.Before;
import org.junit.Test;
import static javafxgui.javafxcomponents.JavaFxBoardComponent.GAME_BOARD_ID;
import static org.junit.Assert.assertEquals;

public class JavaFxBoardComponentTest {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private JavaFxBoardComponent javaFxBoardComponent;
    private GUIViewSpy guiViewSpy;
    private NewPlayerMoveEventHandlerSpy eventHandlerSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        eventHandlerSpy = new NewPlayerMoveEventHandlerSpy(guiViewSpy);
        javaFxBoardComponent = new JavaFxBoardComponent(
                new Board(DEFAULT_BOARD_DIMENSION),
                eventHandlerSpy);
    }

    @Test
    public void boardHasCorrectId() {
        assertEquals(GAME_BOARD_ID, javaFxBoardComponent.getId());
    }

    @Test
    public void boardHasNineChildren() {
        assertEquals(9, javaFxBoardComponent.getChildren().size());
    }

    @Test
    public void buttonHasIdAsExpected() {
        Button button = (Button) javaFxBoardComponent.getChildren().get(0);
        assertEquals("1", button.getId());
    }

    @Test
    public void eventRegistration() {
        Button button = (Button) javaFxBoardComponent.getChildren().get(0);
        button.fire();
        assertEquals(true, eventHandlerSpy.hasBeenClicked());
    }

    @Test
    public void createADisabledBoard() {
        JavaFxBoardComponent javaFxBoardComponent = new JavaFxBoardComponent(
                new Board(DEFAULT_BOARD_DIMENSION),
                eventHandlerSpy);
        javaFxBoardComponent.setDisable(true);
        Button button = (Button) javaFxBoardComponent.getChildren().get(0);
        assertEquals(true, button.isDisabled());
    }
}
