import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {
    private HashMap<String, ArrayList<Player>> gameTypesPlayers = new HashMap<>();

    public PlayerFactory(UserInterface userInterface) {
        registerPlayersForGameType(userInterface);
    }

    private void registerPlayersForGameType(UserInterface userInterface) {
        gameTypesPlayers.put("HVH",
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new HumanPlayer(Counter.O, Player.Type.Human, userInterface)));
        gameTypesPlayers.put("HVC",
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new ComputerPlayer(Counter.O, Player.Type.Computer, userInterface)));
        gameTypesPlayers.put("CVH",
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

    public boolean playersAvailableForGameType(String gameType) {
        if (gameTypesPlayers.get(gameType) != null) {
            return true;
        }
        return false;
    }

    public ArrayList<Player> generatePlayersFor(String gameType) {
        return gameTypesPlayers.get(gameType);
    }
}
