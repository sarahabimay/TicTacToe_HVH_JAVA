package jttt.guiapp.players;

import jttt.core.game.GameType;
import jttt.core.players.Player;
import jttt.core.players.ComputerPlayer;
import jttt.core.players.PlayerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jttt.core.board.Mark.O;
import static jttt.core.board.Mark.X;

public class GUIPlayerFactory implements PlayerFactory {
    public static final Map<GameType, List<Player>> gameTypeOptionToPlayers = new HashMap<>();

    @Override
    public List<Player> findPlayersFor(int gameType) {
        registerGameTypeWithPlayerTypes();
        return gameTypeOptionToPlayers.get(GameType.lookupGameType(gameType));
    }

    @Override
    public void registerGameTypeWithPlayerTypes() {
        gameTypeOptionToPlayers.put(GameType.GUI_HVH, createPlayers(new GUIHumanPlayer(X), new GUIHumanPlayer(O)));
        gameTypeOptionToPlayers.put(GameType.GUI_CVH, createPlayers(new ComputerPlayer(X), new GUIHumanPlayer(O)));
        gameTypeOptionToPlayers.put(GameType.GUI_HVC, createPlayers(new GUIHumanPlayer(X), new ComputerPlayer(O)));

    }

    public List<Player> createPlayers(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }
}
