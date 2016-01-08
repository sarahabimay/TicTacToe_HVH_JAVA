package jttt.Core.Players;

import jttt.Core.Board.Mark;
import jttt.Core.GameType;
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
        optionToGameType.put(4, GameType.GUI_HVH);
        optionToGameType.put(5, GameType.GUI_CVH);
        optionToGameType.put(6, GameType.GUI_HVC);
    }

    void registerGameTypeWithPlayerTypes() {
        gameTypeOptionToPlayers.put(GameType.HVH, createPlayers(new HumanPlayer(Mark.X, userInterface), new HumanPlayer(Mark.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.HVC, createPlayers(new HumanPlayer(Mark.X, userInterface), new ComputerPlayer(Mark.O)));
        gameTypeOptionToPlayers.put(GameType.CVH, createPlayers(new ComputerPlayer(Mark.X), new HumanPlayer(Mark.O, userInterface)));
        gameTypeOptionToPlayers.put(GameType.GUI_HVH, createPlayers(new GUIHumanPlayer(Mark.X), new GUIHumanPlayer(Mark.O)));
        gameTypeOptionToPlayers.put(GameType.GUI_CVH, createPlayers(new ComputerPlayer(Mark.X), new GUIHumanPlayer(Mark.O)));
        gameTypeOptionToPlayers.put(GameType.GUI_HVC, createPlayers(new GUIHumanPlayer(Mark.X), new ComputerPlayer(Mark.O)));
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
