package javafxgui.players;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GUIHumanPlayerTest {

    private GUIHumanPlayer guiHuman;

    @Before
    public void setUp() {
        guiHuman = new GUIHumanPlayer(Mark.X);
    }

    @Test
    public void createGuiHumanPlayer() {
        guiHuman = new GUIHumanPlayer(Mark.X);
        assertEquals(GUIHumanPlayer.class, guiHuman.getClass());
    }

    @Test
    public void playerDoesNotHaveAMove() {
        assertEquals(false, guiHuman.hasNewMove());
    }

    @Test
    public void setNextUserMove() {
        guiHuman.setNextUserMove("1");
        assertEquals(true, guiHuman.hasNewMove());
    }

    @Test
    public void takeNextMoveFromGuiHuman() {
        guiHuman.setNextUserMove("2");
        int nextMove = guiHuman.getNextPosition(new Board(3));
        assertEquals(2, nextMove);
    }

    @Test
    public void checkMoveHasBeenRemovedFromGUIHumanPlayer() {
        guiHuman.setNextUserMove("2");
        guiHuman.getNextPosition(new Board(3));
        assertEquals(false, guiHuman.hasNewMove());
    }
    @Test
    public void getPlayersOpponent() {
        assertEquals(Mark.O, guiHuman.opponentCounter());
    }
}
