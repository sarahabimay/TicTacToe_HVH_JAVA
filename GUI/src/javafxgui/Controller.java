package javafxgui;

import jttt.Core.Players.Player;

public interface Controller {
    void displayGUI();

    void displayBoard();

    void displayResult();

    void playGame();

    void createNewGame();

    boolean foundWinOrDraw();

    void displayPlayAgain();

    Player getCurrentPlayer();
}
