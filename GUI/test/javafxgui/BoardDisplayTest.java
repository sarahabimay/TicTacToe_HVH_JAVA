package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardDisplayTest {

    private ControllerSpy controllerSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        controllerSpy = new ControllerSpy();
    }

    @Test
    public void getBoardForDisplay() {
        BoardDisplay boardDisplay = new BoardDisplay(new Board(3));
        GridPane grid = boardDisplay.getBoardForDisplay();
        assertEquals(9, grid.getChildren().size());
    }
    @Test
    public void displayDisabledBoard() {
        BoardDisplay boardDisplay = new BoardDisplay(new Board(3));
        GridPane disabledBoard = boardDisplay.getDisabledBoard();
        assertEquals(true, disabledBoard.getChildren().get(0).isDisabled());
    }
    @Test
    public void buttonRegisteredWithHandler() {
        Button button = new Button();
        JavaFxButtonSpy buttonTest = new JavaFxButtonSpy(button);
        BoardDisplay boardDisplay = new BoardDisplay(new Board(3));
//        boardDisplay.registerBoardButtonWithHandler(buttonTest);
        button.fire();
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }
}
