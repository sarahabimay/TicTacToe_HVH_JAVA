package jttt.console;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static jttt.core.board.Mark.X;
import static jttt.core.game.GameType.HVH;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConsoleAppTest {

    private final int DEFAULT_DIMENSION = 3;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;
    private ConsoleApp cli;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
    }

    @Test
    public void constructCommandLineUsingGameMaker() {
        ConsoleGameMakerSpy consoleGameMakerSpy = new ConsoleGameMakerSpy();
        ConsoleApp cli = new ConsoleApp(consoleGameMakerSpy, inputStream, writer);
        cli.createNewGameFromOptions(HVH.getNumericGameType(), DEFAULT_DIMENSION);
        assertEquals(true, consoleGameMakerSpy.hasGameBeenInitialized());
    }

    @Test
    public void displayOpeningMessage() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        cli.newGameRequest();
        assertThat(output.toString(), containsString(ConsoleApp.PLAY_GAME_REQUEST));
    }

    @Test
    public void userChoosesToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        Assert.assertEquals(BinaryChoice.NO.getChoiceOption(), cli.requestPlayAgainChoice());
        assertThat(output.toString(), containsString(ConsoleApp.REPLAY_REQUEST));
    }

    @Test
    public void userChoosesPlay() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);

        assertEquals(true, cli.playAgain());
        assertThat(output.toString(), containsString(ConsoleApp.REPLAY_REQUEST));
    }

    @Test
    public void requestBoardSizeCalled() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        assertEquals(3, cli.requestBoardDimension());
        assertThat(output.toString(), containsString(ConsoleApp.DIMENSION_REQUEST));
    }

    @Test
    public void requestGameTypeCalled() {
        assertEquals(1, cli.requestGameType());
        assertThat(output.toString(), containsString(ConsoleApp.GAME_TYPE_REQUEST));
    }

    @Test
    public void winningResultDisplayed() {
        byte[] buf = "1\n1\n3\n1\n2\n5\n3\n9\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        cli.startGame();
//        cli.displayResult();
        String expected = String.format(ConsoleApp.WINNER_ANNOUNCE, X);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void drawResultDisplayed() {
        byte[] buf = "1\n1\n3\n1\n2\n3\n5\n8\n4\n6\n9\n7\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        cli.startGame();
//        cli.displayResult();
        assertThat(output.toString(), containsString(ConsoleApp.DRAW_ANNOUNCE));
    }

    @Test
    public void playEntireHvHGame() {
        byte[] buf = "1\n1\n3\n1\n2\n5\n3\n9\n2\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(buf);
        ConsoleApp cli = new ConsoleApp(new ConsoleGameMaker(), inputStream, writer);
        cli.startGame();
        assertThat(output.toString(), containsString(ConsoleApp.PLAY_GAME_REQUEST));
    }
}