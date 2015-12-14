package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardDisplayTest {

    @Before
    public void setUp() {
        new JFXPanel();
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
}
