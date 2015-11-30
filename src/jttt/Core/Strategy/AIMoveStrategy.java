package jttt.Core.Strategy;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public interface AIMoveStrategy {
    int calculateNextMove(Board board, Mark mark);
}
