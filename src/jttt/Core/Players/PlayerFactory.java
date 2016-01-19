package jttt.core.players;

import java.util.List;

public interface PlayerFactory {

    List<Player> findPlayersFor(int gameType);
    void registerGameTypeWithPlayerTypes();
}

