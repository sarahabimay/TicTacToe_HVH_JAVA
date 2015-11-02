import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {

    static final HashMap<String, GameType> optionToGameType = new HashMap<>();
    private HashMap<GameType, ArrayList<Player.Type>> gameTypeToPlayerTypes = new HashMap<>();

    public PlayerFactory() {
        registerOptionsToGameType();
        registerGameTypeWithPlayerTypes();
    }

    public static boolean validPlayerTypes(String choice) {
        return optionToGameType.containsKey(choice);
    }

    public boolean isPlayerTypeAvailable(GameType gameType) {
        return gameTypeToPlayerTypes.get(gameType) != null;
    }

    public ArrayList<Player> generatePlayersFor(String playerType, UserInterface userInterface) {
        return generatePlayers(getGameType(playerType), userInterface);
    }

    private void registerOptionsToGameType() {
        optionToGameType.put("HVH", GameType.HVH);
        optionToGameType.put("HVC", GameType.HVC);
        optionToGameType.put("CVH", GameType.CVH);
    }

    private void registerGameTypeWithPlayerTypes() {
        gameTypeToPlayerTypes.put(getGameType("HVH"), createPlayerTypeList(GameType.HVH));
        gameTypeToPlayerTypes.put(getGameType("HVC"), createPlayerTypeList(GameType.HVC));
        gameTypeToPlayerTypes.put(getGameType("CVH"), createPlayerTypeList(GameType.CVH));
    }

    private ArrayList<Player.Type> createPlayerTypeList(GameType gameType) {
        ArrayList<Player.Type> playerTypes = new ArrayList<>();
        if (gameType == GameType.HVH) {
            playerTypes.add(Player.Type.HUMAN);
            playerTypes.add(Player.Type.HUMAN);
        } else if (gameType == GameType.HVC) {
            playerTypes.add(Player.Type.HUMAN);
            playerTypes.add(Player.Type.COMPUTER);
        } else if (gameType == GameType.CVH) {
            playerTypes.add(Player.Type.COMPUTER);
            playerTypes.add(Player.Type.HUMAN);
        }
        return playerTypes;
    }

    private ArrayList<Player> generatePlayers(GameType gameType, UserInterface ui) {
        Player.Type player1Type = gameTypeToPlayerTypes.get(gameType).get(0);
        Player.Type player2Type = gameTypeToPlayerTypes.get(gameType).get(1);
        return generatePlayerList(player1Type, player2Type, ui);
    }

    private ArrayList<Player> generatePlayerList(Player.Type player1Type, Player.Type player2Type, UserInterface ui) {
        return createPlayerList(createNewPlayer(player1Type, Counter.X, ui), createNewPlayer(player2Type, Counter.O, ui));
    }

    private Player createNewPlayer(Player.Type playerType, Counter counter, UserInterface ui) {
        if (playerType == Player.Type.HUMAN) {
            return new HumanPlayer(counter, ui);
        } else if (playerType == Player.Type.COMPUTER) {
            return new ComputerPlayer(counter, ui);
        }
        return null;
    }

    private ArrayList<Player> createPlayerList(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    private GameType getGameType(String playerTypeOption) {
        return optionToGameType.get(playerTypeOption);
    }
}
