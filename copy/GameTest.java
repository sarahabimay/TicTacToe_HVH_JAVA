import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    FakeCommandLineUI fakeUI;

    @Before
    public void setUp() {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void playWinningMove() {
        fakeUI.addDummyInputs(fakeUI.aListOfMoves(new Integer[]{1, 4, 2, 5, 3}));
        Game game = new Game(fakeUI,
                new Board(3),
                new Player(Counter.X, fakeUI),
                new Player(Counter.O, fakeUI));
        game.play();
        assertEquals(Counter.X, fakeUI.getWinner());
    }

    @Test
    public void gameIsADraw() {
        fakeUI.addDummyInputs(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 5, 6, 9, 8, 7, 4}));
        Game game = new Game(fakeUI, new Board(3),
                new Player(Counter.X, fakeUI),
                new Player(Counter.O, fakeUI));
        game.play();
        assertEquals(true, fakeUI.isDraw());
    }
}