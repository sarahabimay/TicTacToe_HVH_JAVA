package jttt.Core.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.DisplayStyler;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.CommandLineUI;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineUITest {

    private final int CVH_GAME_TYPE_OPTION = 3;
    private final int NO = 2;
    private int DEFAULT_GAMETYPE = 1;
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
        game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAMETYPE, new PlayerFactory());
        cli = new CommandLineUI(game, new DisplayStyler(), inputStream, writer);
    }

    @Test
    public void displayOpeningMessage() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), DEFAULT_DIMENSION, new PlayerFactory()),
                new DisplayStyler(),
                inputStream, writer);
        cli.displayGreetingRequest();
        assertThat(output.toString(), containsString(cli.GREETING));
    }

    @Test
    public void empty3x3BoardIsDisplayedCorrectly() {
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), DEFAULT_DIMENSION, new PlayerFactory()),
                new DisplayStylerFake(),
                inputStream, writer);
        cli.createNewGame(DEFAULT_GAMETYPE, DEFAULT_DIMENSION);
        cli.displayBoard();
        assertThat(output.toString(),
                containsString(String.format("Board Size: %s, Empty Positions: %s",
                        DEFAULT_DIMENSION * DEFAULT_DIMENSION, 9)));
    }

    @Test
    public void userChoosesToQuit() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), CVH_GAME_TYPE_OPTION, new PlayerFactory()),
                new DisplayStyler(),
                inputStream, writer);
        assertEquals(NO, cli.requestPlayAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void userChoosesToReplay() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), CVH_GAME_TYPE_OPTION, new PlayerFactory()),
                new DisplayStyler(),
                inputStream, writer);
        assertEquals(true, cli.playAgain());
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    @Test
    public void requestBoardSizeCalled() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        CommandLineUI cli = new CommandLineUI(
                new Game(new Board(DEFAULT_DIMENSION), CVH_GAME_TYPE_OPTION, new PlayerFactory()),
                new DisplayStyler(),
                inputStream, writer);
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
        CommandLineUI cli = new CommandLineUI(
                game, new DisplayStyler(),
                inputStream, writer);
        assertEquals(9, cli.requestNextPosition(game.getBoard()));
        assertThat(output.toString(), containsString(cli.POSITION_REQUEST));
    }

    @Test
    public void winningResultDisplayed() {
        cli.displayResult(Mark.X);
        String expected = String.format(cli.WINNER_ANNOUNCE, Mark.X);
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
        CommandLineUI cli = new CommandLineUI(
                game, new DisplayStyler(),
                inputStream, writer);
        cli.start();
        assertThat(output.toString(), containsString(cli.GREETING));
        assertThat(output.toString(), containsString(cli.REPLAY_REQUEST));
    }

    private class DisplayStylerFake extends DisplayStyler {
        public String formatBoardForDisplay(Board board) {
            return String.format("Board Size: %s, Empty Positions: %s", board.boardSize(), board.numberOfOpenPositions());
        }
    }
}