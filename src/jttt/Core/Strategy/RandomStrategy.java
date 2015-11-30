package jttt.Core.Strategy;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

import java.util.Random;

public class RandomStrategy implements AIMoveStrategy{

    private Mark mark;

    public int calculateNextMove(Board board, Mark mark) {
        this.mark = mark;
        long range = calculateNumberRange(board);
        long fraction = randomFractionFromRange(range);
        return randomNumberInRange(fraction);
    }

    long calculateNumberRange(Board board) {
        int start = 1;
        int end = board.boardSize();
        return end - start + 1;
    }

    long randomFractionFromRange(long range) {
        Random randomGenerator = new Random();
        return (long) (range * randomGenerator.nextDouble());
    }

    int randomNumberInRange(long fraction) {
        int start = 1;
        return (int) (fraction + start);
    }
}
