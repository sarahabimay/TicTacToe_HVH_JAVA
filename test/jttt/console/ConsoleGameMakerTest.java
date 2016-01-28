package jttt.console;

import jttt.core.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static jttt.core.board.Mark.O;
import static jttt.core.board.Mark.X;
import static jttt.core.game.GameType.HVH;
import static org.junit.Assert.assertEquals;

public class ConsoleGameMakerTest {
    private static final int DEFAULT_BOARD_DIMENSION = 3;
    private FakeConsoleApp uiSpy;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        uiSpy = new FakeConsoleApp(null, inputStream, writer);
    }

    @Test
    public void createAThreeByThreeHVHGame() {
        ConsoleGameMaker gameMaker = new ConsoleGameMaker();
        Game game = gameMaker.initializeGame(
                DEFAULT_BOARD_DIMENSION,
                HVH.getNumericGameType(),
                new ConsolePlayerFactory(uiSpy),
                new ConsoleBoardDisplayer(uiSpy));
        assertEquals(9, game.getBoardSize());
        assertEquals(HumanPlayer.class, game.getPlayer(X).getClass());
        assertEquals(HumanPlayer.class, game.getPlayer(O).getClass());
    }
}
