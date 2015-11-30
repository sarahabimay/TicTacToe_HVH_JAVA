package jttt.Core.Board;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BoardStyleTest {

    private Mark X = Mark.X;
    private Mark O = Mark.O;
    private Mark E = Mark.EMPTY;

    @Test
    public void boardIsDisplayed() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, X, E,
                E, E, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        DisplayStyler styler = new DisplayStyler();
        System.out.println(styler.displayBoard(board));
        assertEquals("" +
                "[1]\t\t[2]\t\t[3]\t\t[4]\n" +
                "[5]\t\t[6]\t\t[7]\t\t[8]\n" +
                "[9]\t\t[10]\t[X]\t\t[12]\n" +
                "[13]\t[14]\t[O]\t\t[X]\n", styler.displayBoard(board));
    }

    @Test
    public void alternateMarkColours() {
        Mark currentBoard[] = {
                X, O, E, E,
                E, X, E, E,
                E, O, X, E,
                E, E, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        DisplayStyler styler = new DisplayStyler();
        String cellForDisplay = board.findMarkAtIndex(0).markOrPositionForDisplay(0);
        System.out.println(styler.colourCell(cellForDisplay));
        String cell = styler.colourCell(cellForDisplay);
        assertThat(cell, is("\n\u001B[31mX\u001B[0m\n"));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
