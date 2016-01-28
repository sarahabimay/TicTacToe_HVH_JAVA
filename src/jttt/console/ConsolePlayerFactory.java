package jttt.console;

import jttt.core.UIReader;
import jttt.core.board.Mark;
import jttt.core.game.GameType;
import jttt.core.players.ComputerPlayer;
import jttt.core.players.Player;
import jttt.core.players.PlayerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jttt.core.game.GameType.*;

public class ConsolePlayerFactory implements PlayerFactory {
    static final Map<GameType, List<Player>> gameTypeOptionToPlayers = new HashMap<>();
    public UIReader uiReader;

    public ConsolePlayerFactory(UIReader uiReader) {
        this.uiReader = uiReader;
    }

    public List<Player> findPlayersFor(int gameType) {
        registerGameTypeWithPlayerTypes();
        return gameTypeOptionToPlayers.get(lookupGameType(gameType));
    }

    public void registerGameTypeWithPlayerTypes() {
        gameTypeOptionToPlayers.put(HVH, createPlayers(new HumanPlayer(Mark.X, uiReader), new HumanPlayer(Mark.O, uiReader)));
        gameTypeOptionToPlayers.put(HVC, createPlayers(new HumanPlayer(Mark.X, uiReader), new ComputerPlayer(Mark.O)));
        gameTypeOptionToPlayers.put(CVH, createPlayers(new ComputerPlayer(Mark.X), new HumanPlayer(Mark.O, uiReader)));
    }

    List<Player> createPlayers(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }
}
