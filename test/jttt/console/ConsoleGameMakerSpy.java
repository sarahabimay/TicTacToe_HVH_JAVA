package jttt.console;

import jttt.core.boarddisplayer.BoardDisplayer;
import jttt.core.game.Game;
import jttt.core.players.PlayerFactory;

public class ConsoleGameMakerSpy extends ConsoleGameMaker {
    private boolean hasGameBeenInitialized = false;

    @Override
    public Game initializeGame(int boardDimension, int gameTypeOption, PlayerFactory playerFactory, BoardDisplayer displayer) {
        hasGameBeenInitialized = true;
        return null;
    }

    public boolean hasGameBeenInitialized() {
        return hasGameBeenInitialized;
    }
}
