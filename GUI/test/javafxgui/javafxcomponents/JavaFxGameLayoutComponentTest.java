package javafxgui.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafxgui.GUIViewSpy;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFxGameLayoutComponentTest {
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy guiViewSpy;
    private JavaFxGameLayoutComponent layout;

    @Before
    public void setUp() {
        new JFXPanel();
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH), null, null);
        layout = new JavaFxGameLayoutComponent(new Board(DEFAULT_BOARD_DIMENSION), guiViewSpy);
    }

    @Test
    public void checkLayoutHasThreePanels() {
        assertEquals(3, layout.getChildren().size());
    }

    @Test
    public void checkThereIsATitleBar() {
        assertEquals("titleBar", layout.getTop().getId());
    }

    @Test
    public void checkThereIsAFooter() {
        assertEquals("footer", layout.getBottom().getId());
    }

    @Test
    public void checkTheFooterHasTwoChildren() {
        VBox results = (VBox)layout.getBottom();
        assertEquals(2, results.getChildren().size());
        assertEquals("resultTarget", results.getChildren().get(0).getId());
        assertEquals("playAgain", results.getChildren().get(1).getId());
    }

    @Test
    public void checkThePlayAgainButtonHasEventHandler() {
        VBox results = (VBox)layout.getBottom();
        Button button = (Button)results.getChildren().get(1);
        assertEquals("playAgain", button.getId());
        button.fire();
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }

    @Test
    public void checkThereIsAGridPaneInCenter() {
        assertEquals("gameBoard", layout.getCenter().getId());
    }
}
