package jttt.Core.Players;

import jttt.Core.Fakes.FakeCommandLineUI;
import jttt.Core.GameType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {

    private final int GAME_TYPE_CVH = 3;
    public PlayerFactory playerFactory;

    @Before
    public void setUp() throws Exception {
        playerFactory = new PlayerFactory();
        playerFactory.registerGameTypeWithPlayerTypes();
    }

    @Test
    public void invalidGameTypeChoice() {
        Assert.assertEquals(false, playerFactory.isValidGameType(0));
    }

    @Test
    public void validGameTypeChoice() {
        Assert.assertEquals(true, playerFactory.isValidGameType(1));
        Assert.assertEquals(true, playerFactory.isValidGameType(2));
        Assert.assertEquals(true, playerFactory.isValidGameType(3));
        Assert.assertEquals(true, playerFactory.isValidGameType(4));
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
    public void requestHVHPlayers() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.HVH.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(HumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(HumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestHVCPlayers() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.HVC.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(HumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestCVHPlayers() {
        PlayerFactory playerFactory = new PlayerFactory(new FakeCommandLineUI());
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.CVH.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(ComputerPlayer.class, players.get(0).getClass());
        Assert.assertEquals(HumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestGUIHumanVGUIHumanPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.GUI_HVH.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(GUIHumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(GUIHumanPlayer.class, players.get(1).getClass());
    }
}
