package jttt.Core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LineTest {

    private Line fourElements;
    private Line emptyThreeElements;

    @Before
    public void setUp() throws Exception {
        fourElements = new Line(new ArrayList<>(Arrays.asList(Mark.X, Mark.O, Mark.EMPTY, Mark.X)));
        emptyThreeElements = new Line(3);
    }

    @Test
    public void aLineShouldHave3Elements() {
        Line line = new Line(Arrays.asList(Mark.X, Mark.X, Mark.X));
        Assert.assertEquals(3, line.getElements().size());
    }

    @Test
    public void aLineShouldHave4Elements() {
        Assert.assertEquals(4, fourElements.getElements().size());
    }

    @Test
    public void createEmptyLine() {
        Assert.assertEquals(3, emptyThreeElements.getElements().size());
    }

    @Test
    public void counterCanBeAddedToLineAtIndex() {
        emptyThreeElements.addElementAtIndex(0, Mark.X);
        Assert.assertEquals(Mark.X, emptyThreeElements.getElements().get(0));
    }

    @Test
    public void findWinForCounter() {
        Line winning = new Line(Arrays.asList(Mark.X, Mark.X, Mark.X));
        Assert.assertEquals(true, winning.hasAWinner());
    }

    @Test
    public void doNotFindWinForCounter() {
        Line line = new Line(Arrays.asList(Mark.O, Mark.X, Mark.X));
        Assert.assertEquals(false, line.hasAWinner());
    }

    @Test
    public void doNotFindWinForAllEmptyElements() {
        Line line = new Line(Arrays.asList(Mark.EMPTY, Mark.EMPTY, Mark.EMPTY));
        Assert.assertEquals(false, line.hasAWinner());
    }

    @Test
    public void searchForWinForACounter() {
        Line winning = new Line(Arrays.asList(Mark.X, Mark.X, Mark.X));
        Assert.assertEquals(true, winning.hasCounterWin(Mark.X));
    }

    @Test
    public void doNotFindWinForACounter() {
        Line line = new Line(Arrays.asList(Mark.EMPTY, Mark.X, Mark.X));
        Assert.assertEquals(false, line.hasCounterWin(Mark.X));
    }

    @Test
    public void findXCounterForWinner() {
        Line winning = new Line(Arrays.asList(Mark.X, Mark.X, Mark.X));
        Assert.assertEquals(Mark.X, winning.findWinner());
    }

    @Test
    public void findOCounterForWinner() {
        Line winning = new Line(Arrays.asList(Mark.O, Mark.O, Mark.O));
        Assert.assertEquals(Mark.O, winning.findWinner());
    }

    @Test
    public void dontFindAWinningCounter() {
        Line winning = new Line(Arrays.asList(Mark.EMPTY, Mark.O, Mark.O));
        Assert.assertEquals(Mark.EMPTY, winning.findWinner());
    }

    @Test
    public void dontFindAWinningCounter2() {
        Line winning = new Line(Arrays.asList(Mark.O, Mark.O, Mark.X));
        Assert.assertEquals(Mark.EMPTY, winning.findWinner());
    }
}
