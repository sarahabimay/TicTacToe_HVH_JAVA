import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {
    public static boolean validPlayerTypes(String choice) {
        return optionToPlayerType.containsKey(choice);
    }

    public enum PlayerTypes {
        HVH,
        HVC,
        CVH,
    }

    static final HashMap<String, PlayerTypes> optionToPlayerType = new HashMap<>();
    private HashMap<PlayerTypes, ArrayList<Player>> playerTypesToPlayers = new HashMap<>();

    public PlayerFactory(UserInterface userInterface) {
        optionToPlayerType.put("HVH", PlayerTypes.HVH);
        optionToPlayerType.put("HVC", PlayerTypes.HVC);
        optionToPlayerType.put("CVH", PlayerTypes.CVH);
        registerPlayers(userInterface);
    }

    public boolean isPlayerTypeAvailable(PlayerTypes gameType) {
        return playerTypesToPlayers.get(gameType) != null;
    }

    public ArrayList<Player> generatePlayersFor(String playerType) {
        return playerTypesToPlayers.get(getPlayerType(playerType));
    }

    private void registerPlayers(UserInterface userInterface) {
        playerTypesToPlayers.put(PlayerFactory.PlayerTypes.HVH,
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new HumanPlayer(Counter.O, Player.Type.Human, userInterface)));
        playerTypesToPlayers.put(PlayerFactory.PlayerTypes.HVC,
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new ComputerPlayer(Counter.O, Player.Type.Computer, userInterface)));
        playerTypesToPlayers.put(PlayerFactory.PlayerTypes.CVH,
                createPlayerList(
                        new ComputerPlayer(Counter.O, Player.Type.Computer, userInterface),
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface)));
    }

    private ArrayList<Player> createPlayerList(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    private PlayerTypes getPlayerType(String playerTypeOption) {
        return optionToPlayerType.get(playerTypeOption);
    }
}
