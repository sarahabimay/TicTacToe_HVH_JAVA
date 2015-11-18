package jttt.Core.Strategy;
import jttt.Core.Board;
import jttt.Core.Mark;
import java.util.Random;

public class FourByFourAlphaBetaStrategy extends AlphaBetaStrategy {

    private final int FOUR_BY_FOUR_SIZE = 16;
    private final int THRESHOLD_FOR_RANDOM = 12;

    public int calculateNextMove(Board board, Mark mark) {
        int numberOfOpenPositions = board.numberOfOpenPositions();
        if (randomStrategyWillBeFaster(board, numberOfOpenPositions)) {
            return calculateRandomPosition(board);
        }
        return super.calculateNextMove(board, mark);
    }

    protected long calculateNumberRange(Board board) {
        int start = 1;
        int end = board.boardSize();
        return end - start + 1;
    }

    protected long randomFractionFromRange(long range) {
        Random randomGenerator = new Random();
        return (long) (range * randomGenerator.nextDouble());
    }

    protected int randomNumberInRange(long fraction) {
        int start = 1;
        return (int) (fraction + start);
    }

    int calculateRandomPosition(Board board) {
        long range = calculateNumberRange(board);
        long fraction = randomFractionFromRange(range);
        return randomNumberInRange(fraction);
    }

    private boolean randomStrategyWillBeFaster(Board board, int numberOfOpenPositions) {
        return board.boardSize() == FOUR_BY_FOUR_SIZE && numberOfOpenPositions > THRESHOLD_FOR_RANDOM;
    }
}
