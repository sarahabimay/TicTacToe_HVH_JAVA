package jttt.Core.Strategy;

import jttt.Core.Board;
import jttt.Core.Mark;

import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBetaStrategy implements AIMoveStrategy {
    private static int DISPLAY_POSITION_OFFSET = 1;
    private static final int SCORE_FOR_DRAW = 0;
    private static int INITIAL_SCORE = 1000000;
    private static int INITIAL_ALPHA = -10000000;
    private static int INITIAL_BETA = 10000000;
    private static String MOVE_KEY = "Move";
    private static String SCORE_KEY = "Score";
    private Mark mark;

    public int calculateNextMove(Board board, Mark mark) {
        this.mark = mark;
        int depth = board.numberOfOpenPositions();
        return indexToDisplayPosition(aBMinimax(depth, this.mark, INITIAL_ALPHA, INITIAL_BETA, board));
    }

    private HashMap<String, Integer> aBMinimax(int depth, Mark currentMark, int alpha, int beta, Board currentBoard) {
        int bestScore = setInitialBestScore(currentMark);
        int bestMove = -1;
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard, depth);
        }

        for (Integer move : currentBoard.remainingPositions()) {
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentMark);
            int score = aBMinimax(  depth - 1,
                                        currentMark.opponentCounter(),
                                        alpha,
                                        beta,
                                        currentStateOfBoard).get(SCORE_KEY);
            if (newBestScore(currentMark, bestScore, score)) {
                bestScore = score;
                bestMove = move;
            }
            alpha = newAlpha(alpha, score, currentMark);
            beta = newBeta(beta, score, currentMark);
            if (beta <= alpha) {
                break;
            }
        }
        return createResultMap(bestScore, bestMove);
    }

    private int newAlpha(int alpha, int score, Mark currentMark) {
        if (isComputerCounter(currentMark)) {
            return max(alpha, score);
        }
        return alpha;
    }

    private int newBeta(int beta, int score, Mark currentMark) {
        if (!isComputerCounter(currentMark)) {
            return min(beta, score);
        }
        return beta;
    }

    private int indexToDisplayPosition(HashMap<String, Integer> result) {
        return result.get(MOVE_KEY) + DISPLAY_POSITION_OFFSET;
    }

    private boolean newBestScore(Mark currentMark, int bestScore, int score) {
        return isComputerCounter(currentMark) && score > bestScore ||
                !isComputerCounter(currentMark) && score < bestScore;
    }

    private boolean isComputerCounter(Mark currentMark) {
        return this.mark == currentMark;
    }

    private boolean noMoreMovesAvailable(int depth, Board currentBoard) {
        return currentBoard.isGameOver() || depth == 0;
    }

    private int setInitialBestScore(Mark currentMark) {
        return currentMark == this.mark ? -INITIAL_SCORE : INITIAL_SCORE;
    }

    private HashMap<String, Integer> calculateResult(Board currentBoard, int depth) {
        double score = INITIAL_SCORE / (currentBoard.boardSize() - currentBoard.numberOfOpenPositions());
        if (currentBoard.findWinner().equals(Mark.EMPTY)) {
            return createResultMap(SCORE_FOR_DRAW, -1);
        } else if (currentBoard.findWinner().equals(this.mark)) {
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
