package jttt.Core.Players;

import jttt.Core.UI.FakeCommandLineUI;
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
    public void checkFactoryCanCreateGUIHVHPlayers() {
        Assert.assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.GUI_HVH));
    }

    @Test
    public void checkFactoryCanCreateCVGUI_HPlayers() {
        Assert.assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.GUI_CVH));
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

    @Test
    public void requestComputerVGUIPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.GUI_CVH.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(ComputerPlayer.class, players.get(0).getClass());
        Assert.assertEquals(GUIHumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestGUIvCPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        ArrayList<Player> players = playerFactory.generatePlayersFor(GameType.GUI_HVC.getGameTypeOption());
        assertEquals(2, players.size());
        Assert.assertEquals(GUIHumanPlayer.class, players.get(0).getClass());
        Assert.assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
}
