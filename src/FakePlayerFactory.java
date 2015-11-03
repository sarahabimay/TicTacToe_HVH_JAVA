import java.util.ArrayList;

public class FakePlayerFactory extends PlayerFactory {
    private FakeComputerPlayer fakeComputerPlayer = null;

    public FakePlayerFactory() {
        super();
    }

    @Override
    protected ArrayList<Player.Type> createPlayerTypeList(GameType gameType) {
        ArrayList<Player.Type> playerTypes = new ArrayList<>();
        if (gameType == GameType.HVH) {
            playerTypes.add(Player.Type.HUMAN);
            playerTypes.add(Player.Type.HUMAN);
        } else if (gameType == GameType.HVC) {
            playerTypes.add(Player.Type.HUMAN);
            playerTypes.add(Player.Type.FAKE);
        } else if (gameType == GameType.CVH) {
            playerTypes.add(Player.Type.FAKE);
            playerTypes.add(Player.Type.HUMAN);
        }
        return playerTypes;
    }

    @Override
    protected Player createNewPlayer(Player.Type playerType, Counter counter, UserInterface ui) {
        if (playerType == Player.Type.HUMAN) {
            return new HumanPlayer(counter, ui);
        } else if (playerType == Player.Type.COMPUTER) {
            return new ComputerPlayer(counter, ui);
        } else if (playerType == Player.Type.FAKE) {
            return fakeComputerPlayer;
        }
        return null;
    }

    public void addFakeComputerPlayer(FakeComputerPlayer fakeAI) {
        this.fakeComputerPlayer = fakeAI;
    }
}
