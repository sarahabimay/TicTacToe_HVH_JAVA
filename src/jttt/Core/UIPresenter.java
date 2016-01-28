package jttt.core;

import jttt.core.board.Board;

public interface UIPresenter {
    void displayGameOptions();

    void displayGameLayout(Board board);

    void displayResult();

    int displayPlayAgainOption();

}
