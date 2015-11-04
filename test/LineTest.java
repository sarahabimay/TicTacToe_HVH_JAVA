import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LineTest {

    public Line line;
    public Line emptyLine;

    @Before
    public void setUp() throws Exception {
        line = new Line(new ArrayList<>(Arrays.asList(Counter.X, Counter.O, Counter.EMPTY, Counter.X)));
        emptyLine = new Line(3);
    }

    @Test
    public void aLineShouldHave3Elements() {
        Line line = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(3, line.getElements().size());
    }

    @Test
    public void aLineShouldHave4Elements() {
        assertEquals(4, line.getElements().size());
    }

    @Test
    public void createEmptyLine() {
        assertEquals(3, emptyLine.getElements().size());
    }

    @Test
    public void counterCanBeAddedToLineAtIndex() {
        emptyLine.addElementAtIndex(0, Counter.X);
        assertEquals(Counter.X, emptyLine.getElements().get(0));
    }

    @Test
    public void findWinForCounter() {
        Line winning = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(true, winning.hasAWinner());
    }

    @Test
    public void doNotFindWinForCounter() {
        Line winning = new Line(Arrays.asList(Counter.O, Counter.X, Counter.X));
        assertEquals(false, winning.hasAWinner());
    }
    @Test
    public void doNotFindWinForAllEmptyElements() {
        Line winning = new Line(Arrays.asList(Counter.EMPTY, Counter.EMPTY, Counter.EMPTY));
        assertEquals(false, winning.hasAWinner());
    }

    @Test
    public void searchForWinForACounter() {
        Line winning = new Line(Arrays.asList(Counter.X, Counter.X, Counter.X));
        assertEquals(true, winning.hasCounterWin(Counter.X));
    }
}
