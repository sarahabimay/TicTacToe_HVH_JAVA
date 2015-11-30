package jttt.Core.Players;

import jttt.Core.GameType;
import jttt.Core.Board.Mark;
import jttt.UI.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {

    static final Map<Integer, GameType> optionToGameType = new HashMap<>();
    static final Map<GameType, ArrayList<Player>> gameTypeOptionToPlayers = new HashMap<>();
    public UserInterface userInterface;

    public PlayerFactory() {
        registerOptionsToGameType();
    }

    public PlayerFactory(UserInterface ui) {
        this.userInterface = ui;
        registerOptionsToGameType();
    }

    public static boolean validPlayerTypes(int choice) {
        return optionToGameType.containsKey(choice);
    }

    public boolean isPlayerTypeAvailable(GameType gameType) {
        return gameTypeOptionToPlayers.get(gameType) != null;
    }

    public ArrayList<Player> generatePlayersFor(int gameType) {
        registerGameTypeWithPlayerTypes();
        return gameTypeOptionToPlayers.get(getGameType(gameType));
    }

    void registerOptionsToGameType() {
        optionToGameType.put(1, GameType.HVH);
        optionToGameType.put(2, GameType.HVC);
        optionToGameType.put(3, GameType.CVH);
    }

    void registerGameTypeWithPlayerTypes() {
        gameTypeOptionToPlayers.put(GameType.HVH, createPlayers(new HumanPlayer(Mark.X, userInterface), new HumanPlayer(Mark.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.HVC, createPlayers(new HumanPlayer(Mark.X, userInterface), new ComputerPlayer(Mark.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.CVH, createPlayers(new ComputerPlayer(Mark.X, userInterface), new HumanPlayer(Mark.O, userInterface)));
    }

    ArrayList<Player> createPlayers(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    private GameType getGameType(int gameTypeOption) {
        return optionToGameType.get(gameTypeOption);
    }
}
