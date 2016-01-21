package javafxgui.controller;

import jttt.core.game.GameType;

public interface Controller {
    void displayGameOptions();

    void displayGameLayout();

    void displayResult();

    void displayPlayAgainOption();

    void startGame(GameType gameType, int boardDimension);

    void registerPlayerMove(String displayPositionId);
}
