package jttt.UI;

import jttt.core.displayer.Displayer;
import jttt.core.board.Board;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class UIDisplayerTest {

    private FakeCommandLineUI uiSpy;
    private DisplayStylerSpy stylerSpy;
    private Displayer uiDisplayer;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        uiSpy = new FakeCommandLineUI(null, inputStream, writer);
        stylerSpy = new DisplayStylerSpy();
        uiDisplayer = new UIDisplayer(uiSpy, stylerSpy);
    }

    @Test
    public void uiDisplayerIsAKindOfDisplayer() {
        assertEquals(true, uiDisplayer instanceof Displayer);
    }

    @Test
    public void displayBoardToUI() {
        uiDisplayer.updateBoardDisplay(new Board(3));
        assertEquals(true, uiSpy.hasBoardBeenDisplayedToUI());
    }

    @Test
    public void displayerShouldGetTheBoardStyled() {
        uiDisplayer.updateBoardDisplay(new Board(3));
        assertEquals(true, stylerSpy.hasBoardBeenStyledForDisplay());
    }
}
