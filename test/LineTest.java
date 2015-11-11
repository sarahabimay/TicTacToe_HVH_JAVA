import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LineTest {

    public Line fourElements;
    public Line emptyThreeElements;

    @Before
    public void setUp() throws Exception {
        fourElements = new Line(new ArrayList<>(Arrays.asList(Counter.X, Counter.O, Counter.EMPTY, Counter.X)));
        emptyThreeElements = new Line(3);
    }

    @Test
    public void aLineShouldHave3Elements() {
        Line line = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(3, line.getElements().size());
    }

    @Test
    public void aLineShouldHave4Elements() {
        assertEquals(4, fourElements.getElements().size());
    }

    @Test
    public void createEmptyLine() {
        assertEquals(3, emptyThreeElements.getElements().size());
    }

    @Test
    public void counterCanBeAddedToLineAtIndex() {
        emptyThreeElements.addElementAtIndex(0, Counter.X);
        assertEquals(Counter.X, emptyThreeElements.getElements().get(0));
    }

    @Test
    public void findWinForCounter() {
        Line winning = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(true, winning.hasAWinner());
    }

    @Test
    public void doNotFindWinForCounter() {
        Line line = new Line(Arrays.asList(Counter.O, Counter.X, Counter.X));
        assertEquals(false, line.hasAWinner());
    }

    @Test
    public void doNotFindWinForAllEmptyElements() {
        Line line = new Line(Arrays.asList(Counter.EMPTY, Counter.EMPTY, Counter.EMPTY));
        assertEquals(false, line.hasAWinner());
    }

    @Test
    public void searchForWinForACounter() {
        Line winning = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(true, winning.hasCounterWin(Counter.X));
    }

    @Test
    public void doNotFindWinForACounter() {
        Line line = new Line(Arrays.asList(Counter.EMPTY, Counter.X, Counter.X));
        assertEquals(false, line.hasCounterWin(Counter.X));
    }

    @Test
    public void findXCounterForWinner() {
        Line winning = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(Counter.X, winning.findWinner());
    }

    @Test
    public void findOCounterForWinner() {
        Line winning = new Line(Arrays.asList(Counter.O, Counter.O, Counter.O));
        assertEquals(Counter.O, winning.findWinner());
    }

    @Test
    public void dontFindAWinningCounter() {
        Line winning = new Line(Arrays.asList(Counter.EMPTY, Counter.O, Counter.O));
        assertEquals(Counter.EMPTY, winning.findWinner());
    }

    @Test
    public void dontFindAWinningCounter2() {
        Line winning = new Line(Arrays.asList(Counter.O, Counter.O, Counter.X));
        assertEquals(Counter.EMPTY, winning.findWinner());
    }
}
