import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    MockUserInterface mockUI;

    @Before
    public void setUp() {
        mockUI = new MockUserInterface();
    }

    @Test
    public void playWinningMove() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{1, 4, 2, 5, 3}));
        Game game = new Game(mockUI,
                new Board(3),
                new Player(Counter.X, mockUI),
                new Player(Counter.O, mockUI));
        game.play();
        assertEquals(Counter.X, mockUI.getWinner());
    }

    @Test
    public void gameIsADraw() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{1, 2, 3, 5, 6, 9, 8, 7, 4}));
        Game game = new Game(mockUI, new Board(3),
                new Player(Counter.X, mockUI),
                new Player(Counter.O, mockUI));
        game.play();
        assertEquals(true, mockUI.isDraw());
    }
}