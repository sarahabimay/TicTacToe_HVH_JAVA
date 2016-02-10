package javafxgui.javafxcomponents;

import javafxgui.app.GUIAppSpy;
import javafxgui.gamemaker.GUIGameMakerFake;
import jttt.core.board.Board;
import jttt.core.boarddisplayer.BoardDisplayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GUIBoardDisplayerTest {
    private int DEFAULT_BOARD_SIZE = 3;

    @Test
    public void displayBoardToUI() {
        GUIAppSpy uiSpy = new GUIAppSpy(new GUIGameMakerFake(), new GUIViewSpy(null));
        BoardDisplayer boardDisplayer = new GUIBoardDisplayer(uiSpy);
        boardDisplayer.updateBoardDisplay(new Board(DEFAULT_BOARD_SIZE));
        assertEquals(true, uiSpy.hasBoardBeenDisplayedToUI());
    }
}
