import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {
    @Test
    public void checkFactoryCanCreateHVHPlayers() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        assertEquals(true, playerFactory.playersAvailableForGameType("HVH"));
    }
    @Test
    public void checkFactoryCanCreateHVCPlayers() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        assertEquals(true, playerFactory.playersAvailableForGameType("HVC"));
    }

    @Test
    public void checkFactoryCanCreateCVHPlayers() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        assertEquals(true, playerFactory.playersAvailableForGameType("CVH"));
    }

    @Test
    public void requestHVHPlayer() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        ArrayList<Player> players = playerFactory.generatePlayersFor("HVH");
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(HumanPlayer.class, players.get(1).getClass());
    }
    @Test
    public void requestHVCPlayer() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory(fakeUI);
        ArrayList<Player> players = playerFactory.generatePlayersFor("HVC");
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
}
