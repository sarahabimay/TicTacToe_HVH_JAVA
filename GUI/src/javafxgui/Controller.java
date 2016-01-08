package javafxgui;

import jttt.Core.GameType;
import jttt.Core.Players.Player;

public interface Controller {
    void displayGameLayout();

    void displayResult();

    void playGame();

    void createNewGame();

    boolean foundWinOrDraw();

    void displayPlayAgain();

    Player getCurrentPlayer();

    void initializeGame(GameType gameType, int boardDimension);

    void startGame(GameType gameType, int boardDimension);

    void presentGameOptions();
}
