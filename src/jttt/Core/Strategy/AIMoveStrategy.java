package jttt.core.strategy;

import jttt.core.board.Board;
import jttt.core.board.Mark;

public interface AIMoveStrategy {
    int calculateNextMove(Board board, Mark mark);
}
