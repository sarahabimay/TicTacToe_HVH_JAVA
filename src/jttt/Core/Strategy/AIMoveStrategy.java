package jttt.Core.Strategy;

import jttt.Core.Board;
import jttt.Core.Mark;

public interface AIMoveStrategy {
    int calculateNextMove(Board board, Mark mark);
}
