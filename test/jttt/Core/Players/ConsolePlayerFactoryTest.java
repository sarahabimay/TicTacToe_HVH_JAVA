package jttt.core.players;

import jttt.UI.ConsolePlayerFactory;
import jttt.UI.HumanPlayer;
import jttt.UI.FakeCommandLineUI;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static jttt.core.game.GameType.*;
import static org.junit.Assert.assertEquals;

public class ConsolePlayerFactoryTest {

    public ConsolePlayerFactory playerFactory;
    private FakeCommandLineUI fakeUI;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        fakeUI = new FakeCommandLineUI(null, inputStream, writer);
        playerFactory = new ConsolePlayerFactory(fakeUI);
        playerFactory.registerGameTypeWithPlayerTypes();
    }

    @Test
    public void threePlayerCombinationsPossible() {
        assertEquals(2, playerFactory.findPlayersFor(HVH.getNumericGameType()).size());
        assertEquals(2, playerFactory.findPlayersFor(CVH.getNumericGameType()).size());
        assertEquals(2, playerFactory.findPlayersFor(HVC.getNumericGameType()).size());
    }

    @Test
    public void requestHVHPlayers() {
        List<Player> players = playerFactory.findPlayersFor(HVH.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(HumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestHVCPlayers() {
        List<Player> players = playerFactory.findPlayersFor(HVC.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestCVHPlayers() {
        List<Player> players = playerFactory.findPlayersFor(CVH.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(ComputerPlayer.class, players.get(0).getClass());
        assertEquals(HumanPlayer.class, players.get(1).getClass());
    }
}
