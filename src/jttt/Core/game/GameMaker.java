package jttt.core.game;

import jttt.core.boarddisplayer.BoardDisplayer;
import jttt.core.players.PlayerFactory;

public interface GameMaker {
    Game initializeGame(int boardDimension, int gameTypeOption, PlayerFactory playerFactory, BoardDisplayer boardDisplayer);
}
