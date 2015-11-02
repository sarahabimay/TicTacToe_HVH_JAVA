import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(Counter counter, Type type, UserInterface userInterface) {
        super(counter, type, userInterface);
    }

    @Override
    Board playTurn(Board board) {
        userInterface.outputToUI(String.format("Computer(%s)'s turn: \n", counter));
        Integer nextPosition = calculateNextMove(board);
        return board.playCounterInPosition(nextPosition, counter);
    }

    private Integer calculateNextMove(Board board) {
        Integer randomPosition = calculateRandomPosition(board);
        while (!board.validPosition(randomPosition) ) {
            randomPosition = calculateRandomPosition(board);
        }
        return randomPosition;
    }

    private Integer calculateRandomPosition(Board board) {
        long range = calculateNumberRange(board);
        long fraction = randomFractionFromRange(range);
        return randomNumberInRange(fraction);
    }

    private int randomNumberInRange(long fraction) {
        int start = 1;
        return (int) (fraction + start);
    }

    private long randomFractionFromRange(long range) {
        Random randomGenerator = new Random();
        return (long) (range * randomGenerator.nextDouble());
    }

    private long calculateNumberRange(Board board) {
        int start = 1;
        int end = board.boardSize();
        return end - start + 1;
    }
}
