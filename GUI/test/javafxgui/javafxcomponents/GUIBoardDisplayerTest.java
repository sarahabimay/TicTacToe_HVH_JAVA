package javafxgui.javafxcomponents;

import jttt.core.boarddisplayer.BoardDisplayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GUIBoardDisplayerTest {
    @Test
    public void displayerIsAKnownKindOfDisplayer() {
        BoardDisplayer boardDisplayer = new GUIBoardDisplayer();
        assertEquals(true, boardDisplayer instanceof BoardDisplayer);
    }
}
