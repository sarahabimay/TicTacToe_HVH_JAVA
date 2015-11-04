import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, userInterface);
    }

    Board playTurn(Board board) {
        return board.playCounterInPosition(calculateNextMove(board), counter);
    }

    private Integer calculateNextMove(Board board) {
        Integer randomPosition = calculateRandomPosition(board);
        while (!board.validPosition(randomPosition)) {
            randomPosition = calculateRandomPosition(board);
        }
        return randomPosition;
    }

    protected Integer calculateRandomPosition(Board board) {
        long range = calculateNumberRange(board);
        long fraction = randomFractionFromRange(range);
        return randomNumberInRange(fraction);
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
}
