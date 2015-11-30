package jttt.Core.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.DisplayStyler;
import jttt.Core.Game;
import jttt.Core.Board.Mark;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.CommandLineUI;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {

    private int DEFAULT_GAMETYPE = 1;
    private final int DEFAULT_DIMENSION = 3;
    private OutputStream output;
    private PrintStream printStream;
    private Writer writer;
    private CommandLineUI cli;
    private Game game;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
        writer = new OutputStreamWriter(output);
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION),
                        DEFAULT_GAMETYPE,
                        new PlayerFactory()),
                new DisplayStyler(),
                inputStream,
                writer);
        game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAMETYPE, new PlayerFactory());
    }

    @Test
    public void displayOpeningMessage() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION),
                        DEFAULT_DIMENSION,
                        new PlayerFactory()),
                new DisplayStyler(),
                inputStream,
                writer);
        cli.displayGreetingRequest();
        assertThat(output.toString(), containsString(cli.GREETING));
    }

    @Test
    @Ignore
    public void empty3x3BoardIsDisplayedCorrectly() {
        String ANSI_CLEAR = "\n" + "\033[H\033[2J" + "\n";
        cli.createNewGame(DEFAULT_GAMETYPE, DEFAULT_DIMENSION);
        cli.displayBoard();
        assertThat(output.toString(),
                containsString(
                        ANSI_CLEAR +
                                "[1]    [2]     [3]\n" +
                                "[4]    [5]     [6]\n" +
                                "[7]    [8]     [9]\n"));
    }

    @Test
    @Ignore
    public void empty4x4BoardIsDisplayedCorrectly() {
        String ANSI_CLEAR = "\n" + "\033[H\033[2J" + "\n";
        cli.createNewGame(DEFAULT_GAMETYPE, 4);
        cli.displayBoard();
        assertThat(output.toString(), containsString(
                ANSI_CLEAR +
                "[1]    [2]     [3]     [4]\n" +
                "[5]    [6]     [7]     [8]\n" +
                "[9]    [10]    [11]    [12]\n" +
                "[13]   [14]    [15]    [16]\n"));
    }

    @Test
    public void userChoosesToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), 3, new PlayerFactory()),
                new DisplayStyler(),
                inputStream,
                writer);
        assertEquals(false, cli.requestPlayAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void userChoosesToReplay() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), 3, new PlayerFactory()),
                new DisplayStyler(),
                inputStream,
                writer);
        assertEquals(true, cli.playAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void requestBoardSizeCalled() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), 3, new PlayerFactory()),
                new DisplayStyler(),
                inputStream,
                writer);
        assertEquals(3, cli.requestBoardDimension());
        assertThat(output.toString(), containsString(cli.DIMENSION_REQUEST));
    }

    @Test
    public void requestGameTypeCalled() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                game,
                new DisplayStyler(),
                inputStream,
                writer);
        assertEquals(1, cli.requestGameType());
        assertThat(output.toString(), containsString(cli.GAME_TYPE_REQUEST));
    }

    @Test
    public void requestNextMoveCalled() {
        InputStream inputStream = new ByteArrayInputStream("9\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                game,
                new DisplayStyler(),
                inputStream,
                writer);
        assertEquals(9, cli.requestNextPosition());
        assertThat(output.toString(), containsString(cli.POSITION_REQUEST));
    }

    @Test
    public void winningResultDisplayed() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                game,
                new DisplayStyler(),
                inputStream,
                writer);
        cli.displayResult(Mark.X);
        String expected = String.format(cli.WINNER_ANNOUNCE, Mark.X);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void drawResultDisplayed() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                game,
                new DisplayStyler(),
                inputStream,
                writer);
        cli.displayResult(Mark.EMPTY);
        assertThat(output.toString(), containsString(cli.DRAW_ANNOUNCE));
    }

    @Test
    public void playEntireHvHGame() {
        byte[] buf = "1\n1\n3\n1\n2\n5\n3\n9\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        CommandLineUI cli = new CommandLineUI(
                game,
                new DisplayStyler(),
                inputStream,
                writer);
        cli.start();
        assertThat(output.toString(), containsString(cli.GREETING));
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }
}