package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import javafxgui.view.BoardDisplay;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardDisplayTest {

    private Board defaultBoard;

    @Before
    public void setUp() {
        new JFXPanel();
        defaultBoard = new Board(3);
    }

    @Test
    public void getBoardForDisplay() {
        BoardDisplay boardDisplay = new BoardDisplay();
        GridPane grid = boardDisplay.getBoardForDisplay(defaultBoard);
        assertEquals(9, grid.getChildren().size());
    }

    @Test
    public void displayDisabledBoard() {
        BoardDisplay boardDisplay = new BoardDisplay();
        GridPane disabledBoard = boardDisplay.getDisabledBoard(defaultBoard);
        assertEquals(true, disabledBoard.getChildren().get(0).isDisabled());
    }
}
