package jttt.Core.Players;

import jttt.Core.Fakes.FakeCommandLineUI;
import jttt.Core.GameType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    public PlayerFactory playerFactory;

    @Before
    public void setUp() throws Exception {
        playerFactory = new PlayerFactory();
        playerFactory.registerGameTypeWithPlayerTypes();
    }

    @Test
    public void invalidGameTypeChoice() {
        Assert.assertEquals(false, playerFactory.validPlayerTypes(0));
    }

    @Test
    public void validGameTypeChoice() {
        Assert.assertEquals(true, playerFactory.validPlayerTypes(1));
        Assert.assertEquals(true, playerFactory.validPlayerTypes(2));
        Assert.assertEquals(true, playerFactory.validPlayerTypes(3));
    }

    @Test
    public void checkFactoryCanCreateHVHPlayers() {
        Assert.assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVH));
    }

    @Test
    public void checkFactoryCanCreateHVCPlayers() {
        Assert.assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVC));
    }

    @Test
    public void checkFactoryCanCreateCVHPlayers() {
        Assert.assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.CVH));
    }

    @Test
    public void requestHVHPlayer() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(1);
        assertEquals(2, players.size());
        Assert.assertEquals(HumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(HumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestHVCPlayer() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(2);
        assertEquals(2, players.size());
        Assert.assertEquals(HumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
    @Test
    public void requestCVHPlayer() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(2);
        assertEquals(2, players.size());
        Assert.assertEquals(ComputerPlayer.class, players.get(1).getClass());
        Assert.assertEquals(HumanPlayer.class, players.get(0).getClass());
    }
}
