import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {
    public enum GameType {
        HVH,
        HVC,
        CVH,
        NONE
    }

    private HashMap<String, GameType> optionToGameType = new HashMap<>();
    private HashMap<GameType, ArrayList<Player>> gameTypesPlayers = new HashMap<>();

    public PlayerFactory(UserInterface userInterface) {
        optionToGameType.put("HVH", GameType.HVH);
        optionToGameType.put("HVC", GameType.HVC);
        optionToGameType.put("CVH", GameType.CVH);
        registerPlayersForGameType(userInterface);
    }

    private void registerPlayersForGameType(UserInterface userInterface) {
        gameTypesPlayers.put(PlayerFactory.GameType.HVH,
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new HumanPlayer(Counter.O, Player.Type.Human, userInterface)));
        gameTypesPlayers.put(PlayerFactory.GameType.HVC,
                createPlayerList(
                        new HumanPlayer(Counter.X, Player.Type.Human, userInterface),
                        new ComputerPlayer(Counter.O, Player.Type.Computer, userInterface)));
        gameTypesPlayers.put(PlayerFactory.GameType.CVH,
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

    public boolean playersAvailableForGameType(GameType gameType) {
        return gameTypesPlayers.get(gameType) != null;
    }

    public ArrayList<Player> generatePlayersFor(String gameType) {
        return gameTypesPlayers.get(getGameType(gameType));
    }

    private GameType getGameType(String gameTypeOption) {
        return optionToGameType.get(gameTypeOption);
//        if (gameTypeOption == 1) {
//            return PlayerFactory.GameType.HVH;
//        } else if (gameTypeOption == 2) {
//            return PlayerFactory.GameType.HVC;
//        } else if (gameTypeOption == 3) {
//            return PlayerFactory.GameType.CVH;
//        }
//        return GameType.NONE;
    }
}
