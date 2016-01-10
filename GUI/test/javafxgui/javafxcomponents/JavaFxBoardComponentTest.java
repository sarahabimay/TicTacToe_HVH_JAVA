package javafxgui.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafxgui.GUIViewSpy;
import javafxgui.NewPlayerMoveEventHandlerSpy;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

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
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH), null, null);
        eventHandlerSpy = new NewPlayerMoveEventHandlerSpy(guiViewSpy);
        javaFxBoardComponent = new JavaFxBoardComponent(
                new Board(DEFAULT_BOARD_DIMENSION),
                eventHandlerSpy);
    }

    @Test
    public void checkBoardHasCorrectId() {
        assertEquals("gameBoard", javaFxBoardComponent.getId());
    }

    @Test
    public void checkBoardHasNineChildren() {
        assertEquals(9, javaFxBoardComponent.getChildren().size());
    }

    @Test
    public void checkButtonHasIdAsExpected() {
        Button button = (Button)javaFxBoardComponent.getChildren().get(0);
        assertEquals("1", button.getId());
    }

    @Test
    public void checkEventRegistration() {
        Button button = (Button)javaFxBoardComponent.getChildren().get(0);
        button.fire();
        assertEquals(true, eventHandlerSpy.hasBeenClicked());
    }
}
