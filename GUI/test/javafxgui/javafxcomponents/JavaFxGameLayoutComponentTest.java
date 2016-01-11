package javafxgui.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxgui.GUIViewSpy;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static javafxgui.javafxcomponents.JavaFxBoardComponent.GAME_BOARD_ID;
import static javafxgui.javafxcomponents.JavaFxGameLayoutComponent.*;
import static jttt.Core.Board.Mark.EMPTY;
import static jttt.Core.Board.Mark.X;
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
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH));
        layout = new JavaFxGameLayoutComponent(new Board(DEFAULT_BOARD_DIMENSION), guiViewSpy);
    }

    @Test
    public void checkLayoutHasThreePanels() {
        assertEquals(3, layout.getChildren().size());
    }

    @Test
    public void checkThereIsATitleBar() {
        assertEquals(TITLE_BAR_ID, layout.getTop().getId());
    }

    @Test
    public void checkThereIsAFooter() {
        assertEquals(FOOTER_ID, layout.getBottom().getId());
    }

    @Test
    public void checkTheFooterHasTwoChildren() {
        VBox results = (VBox) layout.getBottom();
        assertEquals(2, results.getChildren().size());
        assertEquals(RESULTS_TARGET_ID, results.getChildren().get(0).getId());
        assertEquals(PLAY_AGAIN_ID, results.getChildren().get(1).getId());
    }

    @Test
    public void disableBoard() {
        layout.disableGameBoard();
        GridPane gameBoard = (GridPane)layout.getCenter();
        assertEquals(true, gameBoard.isDisabled());
    }

    @Test
    public void checkPlayAgainButtonIsDisplayed() {
        layout.displayPlayAgainButton();
        VBox results = (VBox) layout.getBottom();
        Button playAgain = (Button) results.getChildren().get(1);
        assertEquals(true, playAgain.isVisible());
    }

    @Test
    public void checkThePlayAgainButtonHasEventHandler() {
        VBox results = (VBox) layout.getBottom();
        Button button = (Button) results.getChildren().get(1);
        assertEquals(PLAY_AGAIN_ID, button.getId());
        button.fire();
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }

    @Test
    public void checkThereIsAGridPaneInCenter() {
        assertEquals(GAME_BOARD_ID, layout.getCenter().getId());
    }

    @Test
    public void displayWinningResult() {
        layout.displayResult(X);
        VBox results = (VBox) layout.getBottom();
        Text result = (Text) results.getChildren().get(0);
        assertEquals(String.format(layout.WINNER_ANNOUNCEMENT, X.toString()), result.getText());
    }

    @Test
    public void displayDrawResult() {
        layout.displayResult(EMPTY);
        VBox results = (VBox) layout.getBottom();
        Text result = (Text) results.getChildren().get(0);
        assertEquals(layout.DRAW_ANNOUNCEMENT, result.getText());
    }
}
