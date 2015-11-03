import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {

    static final HashMap<Integer, GameType> optionToGameType = new HashMap<>();
    static final HashMap<GameType, ArrayList<Player>> gameTypeOptionToPlayers = new HashMap<>();
    public UserInterface userInterface;
    private HashMap<GameType, ArrayList<Player.Type>> gameTypeToPlayerTypes = new HashMap<>();

    public PlayerFactory() {
        registerOptionsToGameType();
        registerGameTypeWithPlayerTypes();
    }
    public PlayerFactory(UserInterface ui) {
        this.userInterface = ui;
        registerOptionsToGameType();
        registerGameTypeWithPlayerTypes();
    }
    public static boolean validPlayerTypes(Integer choice) {
        return optionToGameType.containsKey(choice);
    }

    public boolean isPlayerTypeAvailable(GameType gameType) {
        return gameTypeOptionToPlayers.get(gameType) != null;
    }

    public ArrayList<Player> generatePlayersFor(Integer gameType) {
        return gameTypeOptionToPlayers.get(getGameType(gameType));
    }

    private void registerOptionsToGameType() {
        optionToGameType.put(1, GameType.HVH);
        optionToGameType.put(2, GameType.HVC);
        optionToGameType.put(3, GameType.CVH);
    }
//
    private void registerGameTypeWithPlayerTypes() {
        gameTypeOptionToPlayers.put(GameType.HVH, createPlayers(new HumanPlayer(Counter.X, userInterface), new HumanPlayer(Counter.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.HVC, createPlayers(new HumanPlayer(Counter.X, userInterface), new ComputerPlayer(Counter.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.CVH, createPlayers(new ComputerPlayer(Counter.X, userInterface), new HumanPlayer(Counter.O, userInterface)));
    }

    protected ArrayList<Player> createPlayers(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }
//
//    private ArrayList<Player> generatePlayers(GameType gameType, UserInterface ui) {
//        Player.Type player1Type = gameTypeToPlayerTypes.get(gameType).get(0);
//        Player.Type player2Type = gameTypeToPlayerTypes.get(gameType).get(1);
//        return generatePlayerList(player1Type, player2Type, ui);
//    }
//
//    private ArrayList<Player> generatePlayerList(Player.Type player1Type, Player.Type player2Type, UserInterface ui) {
//        return createPlayerList(createNewPlayer(player1Type, Counter.X, ui), createNewPlayer(player2Type, Counter.O, ui));
//    }
//
//    protected Player createNewPlayer(Player.Type playerType, Counter counter, UserInterface ui) {
//        if (playerType == Player.Type.HUMAN) {
//            return new HumanPlayer(counter, ui);
//        } else if (playerType == Player.Type.COMPUTER) {
//            return new ComputerPlayer(counter, ui);
//        }
//        return null;
//    }
//
//    private ArrayList<Player> createPlayerList(Player player1, Player player2) {
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(player1);
//        players.add(player2);
//        return players;
//    }
//
    private GameType getGameType(Integer gameTypeOption) {
        return optionToGameType.get(gameTypeOption);
    }
}
