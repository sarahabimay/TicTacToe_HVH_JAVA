import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerFactoryTest {
    @Test
    public void checkFactoryCanCreateHVHPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVH));
    }
    @Test
    public void checkFactoryCanCreateHVCPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.HVC));
    }

    @Test
    public void checkFactoryCanCreateCVHPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        assertEquals(true, playerFactory.isPlayerTypeAvailable(GameType.CVH));
    }

    @Test
    public void requestHVHPlayer() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory();
        ArrayList<Player> players = playerFactory.generatePlayersFor("HVH",fakeUI );
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(HumanPlayer.class, players.get(1).getClass());
    }
    @Test
    public void requestHVCPlayer() {
        FakeCommandLineUI  fakeUI = new FakeCommandLineUI();
        PlayerFactory playerFactory = new PlayerFactory();
        ArrayList<Player> players = playerFactory.generatePlayersFor("HVC", fakeUI);
        assertEquals(2, players.size());
        assertEquals(HumanPlayer.class, players.get(0).getClass());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
}
