package jttt.Core.Board;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static jttt.Core.Board.Mark.EMPTY;
import static jttt.Core.Board.Mark.O;
import static jttt.Core.Board.Mark.X;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DisplayStylerTest {

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";

    @Test
    public void emptyElementDisplayed() {
        Mark currentBoard[] = {
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, X,     EMPTY,
                EMPTY, EMPTY, EMPTY, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        DisplayStyler styler = new DisplayStyler();
        String expected = "[1]";
        assertEquals(expected, styler.formatMarkForDisplay(0, board));
        assertEquals(2, styler.appendSpaces(0, board).length());
        expected = ANSI_GREEN + "|  " + ANSI_RESET;
        assertEquals(expected, styler.appendDivider(0, board));
        assertEquals("", styler.appendNewLine(0, board));
        assertEquals("", styler.addBottomLine(0, board));
    }

    @Test
    public void alternateMarkColours() {
        Mark currentBoard[] = {
                X,      O,      EMPTY,  EMPTY,
                X,      O,      EMPTY,  EMPTY,
                EMPTY,  X,      EMPTY,  EMPTY,
                EMPTY,  O,      X,      EMPTY,
                EMPTY,  EMPTY,  O,      X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        DisplayStyler styler = new DisplayStyler();
        String cellForDisplay = board.findMarkAtIndex(0).markOrPositionForDisplay(0);
        String cell = styler.setDisplayColor(cellForDisplay);
        assertThat(cell, is(String.format("%s%s%s", ANSI_RED, "X", ANSI_RESET)));
        cellForDisplay = board.findMarkAtIndex(1).markOrPositionForDisplay(1);
        cell = styler.setDisplayColor(cellForDisplay);
        assertThat(cell, is(String.format("%s%s%s", ANSI_BLUE, "O", ANSI_RESET)));
    }

    @Test
    public void markElementDisplayed() {
        Mark currentBoard[] = {
                EMPTY, EMPTY, EMPTY,    EMPTY,
                EMPTY, EMPTY, EMPTY,    EMPTY,
                EMPTY, EMPTY, X,        O,
                EMPTY, EMPTY, O,        X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        DisplayStyler styler = new DisplayStyler();
        String expected = String.format("[%s]", styler.setDisplayColor("O"));
        assertEquals(expected, styler.formatMarkForDisplay(11, board));
        assertEquals(0, styler.appendSpaces(11, board).length());
        assertEquals("", styler.appendDivider(11, board));
        assertEquals("\n", styler.appendNewLine(11, board));
        expected = ANSI_GREEN + "_________" + ANSI_RESET;
        assertThat(styler.addBottomLine(11, board), containsString(expected));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
