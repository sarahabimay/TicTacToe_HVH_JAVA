package jttt.core;

import jttt.core.board.Board;
import jttt.core.game.Game;

public interface UIPresenter {
    void displayGameOptions();

    void displayGameLayout(Board board);

    void displayResult(Game game);
}
