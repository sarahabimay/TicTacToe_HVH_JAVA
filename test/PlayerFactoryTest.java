import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    public PlayerFactory playerFactory;

    @Before
    public void setUp() throws Exception {
        playerFactory = new PlayerFactory();
    }

    @Test
    public void invalidGameTypeChoice() {
        assertEquals(false, playerFactory.validPlayerTypes(0));
    }

    @Test
    public void validGameTypeChoice() {
        assertEquals(true, playerFactory.validPlayerTypes(1));
        assertEquals(true, playerFactory.validPlayerTypes(2));
        assertEquals(true, playerFactory.validPlayerTypes(3));
    }

    @Test
    public void checkFactoryCanCreateHVHPlayers() {
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVH));
    }

    @Test
    public void checkFactoryCanCreateHVCPlayers() {
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVC));
    }

    @Test
    public void checkFactoryCanCreateCVHPlayers() {
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.CVH));
    }

    @Test
    public void requestHVHPlayer() {
        FakeCommandLineUI fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        ArrayList<Player> players = playerFactory.generatePlayersFor(1);
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(HumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestHVCPlayer() {
        FakeCommandLineUI fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        ArrayList<Player> players = playerFactory.generatePlayersFor(2);
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
    @Test
    public void requestCVHPlayer() {
        FakeCommandLineUI fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        ArrayList<Player> players = playerFactory.generatePlayersFor(2);
        assertEquals(2, players.size());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
    }
}
