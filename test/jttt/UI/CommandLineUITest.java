package jttt.UI;

import jttt.core.board.Mark;
import jttt.core.game.Game;
import jttt.core.game.GameMakerSpy;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static jttt.core.board.Mark.X;
import static jttt.core.game.GameType.HVH;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {

    private final int DEFAULT_DIMENSION = 3;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;
    private CommandLineUI cli;
    private Game game;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
    }

    @Test
    public void constructCommandLineUsingGameMaker() {
        GameMakerSpy gameMakerSpy = new GameMakerSpy();
        CommandLineUI cli = new CommandLineUI(gameMakerSpy, inputStream, writer);
        cli.createNewGameFromOptions(HVH.getNumericGameType(), DEFAULT_DIMENSION);
        assertEquals(true, gameMakerSpy.hasGameBeenInitialized());
    }

    @Test
    public void displayOpeningMessage() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
        cli.displayGreetingRequest();
        assertThat(output.toString(), containsString(cli.GREETING));
    }

    @Test
    public void userChoosesToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
        assertEquals(BinaryChoice.NO.getChoiceOption(), cli.requestPlayAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void userChoosesToReplay() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);

        assertEquals(true, cli.playAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void requestBoardSizeCalled() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
        assertEquals(3, cli.requestBoardDimension());
        assertThat(output.toString(), containsString(cli.DIMENSION_REQUEST));
    }

    @Test
    public void requestGameTypeCalled() {
        assertEquals(1, cli.requestGameType());
        assertThat(output.toString(), containsString(cli.GAME_TYPE_REQUEST));
    }

    @Test
    public void requestNextMoveCalled() {
        InputStream inputStream = new ByteArrayInputStream("9\n".getBytes());
        ConsoleGameMaker gameMaker = new ConsoleGameMaker();
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
        Game game = gameMaker.initializeGame(DEFAULT_DIMENSION, HVH.getNumericGameType(), cli);
        assertEquals(9, cli.requestNextPosition(game.getBoard()));
        assertThat(output.toString(), containsString(cli.POSITION_REQUEST));
    }

    @Test
    public void winningResultDisplayed() {
        cli.displayResult(X);
        String expected = String.format(cli.WINNER_ANNOUNCE, X);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void drawResultDisplayed() {
        cli.displayResult(Mark.EMPTY);
        assertThat(output.toString(), containsString(cli.DRAW_ANNOUNCE));
    }

    @Test
    public void playEntireHvHGame() {
        byte[] buf = "1\n1\n3\n1\n2\n5\n3\n9\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        CommandLineUI cli = new CommandLineUI(new ConsoleGameMaker(), inputStream, writer);
        cli.start();
        assertThat(output.toString(), containsString(cli.GREETING));
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }
}