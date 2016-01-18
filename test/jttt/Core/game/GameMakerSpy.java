package jttt.core.game;

import jttt.UI.UserInterface;
import jttt.UI.ConsoleGameMaker;

public class GameMakerSpy extends ConsoleGameMaker {
    private boolean hasGameBeenInitialized = false;

    @Override
    public Game initializeGame(int boardDimension, int gameTypeOption, UserInterface userInterface) {
        hasGameBeenInitialized = true;
        return null;
    }

    public boolean hasGameBeenInitialized() {
        return hasGameBeenInitialized;
    }
}
