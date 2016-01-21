package jttt.core;

import jttt.core.board.Board;

public interface UIReader {
    int requestBoardDimension();

    int requestGameType();

    int requestNextPosition();
}
