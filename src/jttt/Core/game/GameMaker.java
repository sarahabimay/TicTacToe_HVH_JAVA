package jttt.core.game;

import jttt.UI.UserInterface;

public interface GameMaker {
    Game initializeGame(int boardDimension, int gameTypeOption, UserInterface ui);
}
