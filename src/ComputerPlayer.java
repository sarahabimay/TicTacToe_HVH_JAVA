import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class ComputerPlayer extends Player {
    private Integer DEPTH = 0;

    public ComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, userInterface);
    }

    Board playTurn(Board board) {
        return board.playCounterInPosition(calculateNextMove(board), counter);
    }

    public HashMap<String, Integer> calculateNextMoveWithMinimax(Board board) {
        DEPTH = board.numberOfOpenPositions();
        return minimax(DEPTH, this.counter, board);
    }

    private HashMap<String, Integer> minimax(Integer depth, Counter currentCounter, Board currentBoard) {
        List<Integer> openPositions = currentBoard.findOpenPositions();
        Integer bestScore = -10;
        Integer bestMove = -1;
        System.out.println("Entering minimax, Depth: " + depth);
        if (currentBoard.isGameOver() || depth == 0 || openPositions.size() == 0) {
            System.out.println("CurrentBoard: " + currentBoard.getCells());
            return calculateResult(currentBoard);
        }

        for (Integer move : openPositions){
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
            Integer score = minimax(depth - 1, currentCounter.opponentCounter(), currentStateOfBoard).get("Score");
            if (counter == currentCounter && score > bestScore ||
                    counter != currentCounter && score < bestScore) {
                bestScore = score;
                bestMove = move;
                System.out.println("CurrentCounter: " + currentCounter + ", Depth: " + depth);
                System.out.println("BestScore: " + bestScore + ", Best Move: " + bestMove);
            }
        }
        System.out.println("Exiting minimax, Depth: " + depth);
        System.out.println("Depth: " + depth + ", Node BestScore: " + bestScore + ", Node Best Move: " + bestMove);
        return createResultMap(bestScore, bestMove);
    }


    private HashMap<String, Integer> calculateResult(Board currentBoard) {
        HashMap<String, Integer> result = createResultMap(currentBoard.calculateBoardScore(counter), -1);
        System.out.println("Restult: " + result);
        return result;
    }

    private HashMap<String, Integer> createResultMap(int score, int move) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("Score", score);
        result.put("Move", move);
        return result;
    }

    private List<Integer> findOpenPositions(List<Counter> currentBoard) {
        return range(0, currentBoard.size())
                .filter(p -> currentBoard.get(p) == Counter.EMPTY)
                .mapToObj(i -> i)
                .collect(toList());
    }

    protected Integer calculateNextMove(Board board) {
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
