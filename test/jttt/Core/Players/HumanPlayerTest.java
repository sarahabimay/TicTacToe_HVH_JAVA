package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.UI.FakeCommandLineUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanPlayerTest {

    public FakeCommandLineUI fakeUI;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void createHumanPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        HumanPlayer human = new HumanPlayer(Mark.X, fakeUI);
        Assert.assertEquals(HumanPlayer.class, human.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player player1 = new HumanPlayer(Mark.X, fakeUI);
        Assert.assertEquals(Mark.O, player1.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(10, 1));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        Player humanPlayer = new HumanPlayer(Mark.X, fakeUI);
        Board board = new Board(3);
        int nextMove = humanPlayer.getNextPosition(board);
        board = board.playCounterInPosition(nextMove, Mark.X);
        Assert.assertEquals(Mark.X, board.findMarkAtDisplayPosition(nextMove));
    }
}