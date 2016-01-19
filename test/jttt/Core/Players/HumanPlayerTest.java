package jttt.core.players;

import jttt.UI.HumanPlayer;
import jttt.UI.FakeCommandLineUI;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    public FakeCommandLineUI fakeUI;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        fakeUI = new FakeCommandLineUI(null, inputStream, writer);
    }

    @Test
    public void createHumanPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        HumanPlayer human = new HumanPlayer(Mark.X, fakeUI);
        assertEquals(HumanPlayer.class, human.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player player1 = new HumanPlayer(Mark.X, fakeUI);
        assertEquals(Mark.O, player1.opponentCounter());
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
        assertEquals(Mark.X, board.findMarkAtDisplayPosition(nextMove));
    }
}