package jttt.Core;

import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ComputerPlayer extends Player {
    private static final int SCORE_FOR_DRAW = 0;
    public static final int INITIAL_SCORE = 1000000;
    private static int DISPLAY_POSITION_OFFSET = 1;
    private static int INITIAL_ALPHA = -10000000;
    private static int INITIAL_BETA = 10000000;
    private static String MOVE_KEY = "Move";
    private static String SCORE_KEY = "Score";
    private static Integer DEPTH = 0;

    public ComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, Type.AI , userInterface);
    }

    Board playTurn(Board board) {
        return board.playCounterInPosition(calculateNextMoveWithAlphaBeta(board), counter);
    }

    Board playTurn(Board board, int newPosition) {
        return board;
    }

    public int calculateNextMoveWithAlphaBeta(Board board) {
        DEPTH = board.numberOfOpenPositions();
        return indexToDisplayPosition(aBMinimax(DEPTH, this.counter, INITIAL_ALPHA, INITIAL_BETA, board));
    }

    private HashMap<String, Integer> aBMinimax(int depth, Counter currentCounter, int alpha, int beta, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard, depth);
        }

        for (Integer move : currentBoard.remainingPositions()) {
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
            Integer score = aBMinimax(depth - 1, currentCounter.opponentCounter(), alpha, beta, currentStateOfBoard).get(SCORE_KEY);
            if (newBestScore(currentCounter, bestScore, score)) {
                bestScore = score;
                bestMove = move;
            }
            alpha = newAlpha(alpha, score, currentCounter);
            beta = newBeta(beta, score, currentCounter);
            if (beta <= alpha) {
                break;
            }
        }
        return createResultMap(bestScore, bestMove);
    }

    private int newAlpha(int alpha, Integer score, Counter currentCounter) {
        if (isComputerCounter(currentCounter)) {
            return max(alpha, score);
        }
        return alpha;
    }

    private int newBeta(int beta, Integer score, Counter currentCounter) {
        if (!isComputerCounter(currentCounter)) {
            return min(beta, score);
        }
        return beta;
    }

    private int indexToDisplayPosition(HashMap<String, Integer> result) {
        return result.get(MOVE_KEY) + DISPLAY_POSITION_OFFSET;
    }

    private boolean newBestScore(Counter currentCounter, Integer bestScore, Integer score) {
        return isComputerCounter(currentCounter) && score > bestScore ||
                !isComputerCounter(currentCounter) && score < bestScore;
    }

    private boolean isComputerCounter(Counter currentCounter) {
        return this.counter == currentCounter;
    }

    private boolean noMoreMovesAvailable(int depth, Board currentBoard) {
        return currentBoard.isGameOver() || depth == 0;
    }

    private Integer setInitialBestScore(Counter currentCounter) {
        return currentCounter == this.counter ? -INITIAL_SCORE : INITIAL_SCORE;
    }

    private HashMap<String, Integer> calculateResult(Board currentBoard, int depth) {
        double score = INITIAL_SCORE / (currentBoard.boardSize() - currentBoard.numberOfOpenPositions());
        if (currentBoard.findWinner().equals(Counter.EMPTY)) {
            return createResultMap(SCORE_FOR_DRAW, -1);
        } else if (currentBoard.findWinner().equals(this.counter)) {
            score = varyScoreUsingDepth(depth, score);
        } else {
            score = varyScoreUsingDepth(-depth, -score);
        }
        return createResultMap(score, -1);
    }

    private double varyScoreUsingDepth(int depth, double score) {
        return score + depth;
    }

    private HashMap<String, Integer> createResultMap(double score, int move) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put(SCORE_KEY, (int) score);
        result.put(MOVE_KEY, move);
        return result;
    }
}
