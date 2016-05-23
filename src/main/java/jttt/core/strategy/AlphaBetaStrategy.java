package jttt.core.strategy;

import jttt.core.board.Board;
import jttt.core.board.Mark;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBetaStrategy implements AIMoveStrategy {
    private static int DISPLAY_POSITION_OFFSET = 1;
    private static final int SCORE_FOR_DRAW = 0;
    private static int INITIAL_SCORE = 1000000;
    private static int INITIAL_ALPHA = -10000000;
    private static int INITIAL_BETA = 10000000;
    private Mark mark;

    public int calculateNextMove(Board board, Mark mark) {
        this.mark = mark;
        int depth = board.numberOfOpenPositions();
        return indexToDisplayPosition(aBMinimax(depth, this.mark, INITIAL_ALPHA, INITIAL_BETA, board));
    }

    private Result aBMinimax(int depth, Mark currentMark, int alpha, int beta, Board currentBoard) {
        int bestScore = setInitialBestScore(currentMark);
        int bestMove = -1;
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard, depth);
        }

        for (Integer move : currentBoard.remainingPositions()) {
            Board currentStateOfBoard = currentBoard.createBoardCopy();
            currentStateOfBoard.playMark(displayPosition(move), currentMark);

            int score = aBMinimax(depth - 1,
                    currentMark.opponentCounter(),
                    alpha,
                    beta,
                    currentStateOfBoard).getScore();

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
        return new Result(bestScore, bestMove);
    }

    private Integer displayPosition(Integer move) {
        return move + DISPLAY_POSITION_OFFSET;
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

    private int indexToDisplayPosition(Result result) {
        return result.getMove() + DISPLAY_POSITION_OFFSET;
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

    private Result calculateResult(Board currentBoard, int depth) {
        double score = INITIAL_SCORE / (currentBoard.boardSize() - currentBoard.numberOfOpenPositions());
        if (hasWinner(currentBoard, Mark.EMPTY)) {
            score = SCORE_FOR_DRAW;
        } else if (hasWinner(currentBoard, this.mark)) {
            score = varyScoreUsingDepth(depth, score);
        } else {
            score = varyScoreUsingDepth(-depth, -score);
        }
        return new Result((int) score, -1);
    }

    private boolean hasWinner(Board currentBoard, Mark empty) {
        return currentBoard.findWinner().equals(empty);
    }

    private double varyScoreUsingDepth(int depth, double score) {
        return score + depth;
    }

    private class Result {
        private int move;
        private int score;

        public Result(int score, int move) {
            this.score = score;
            this.move = move;
        }

        public int getMove() {
            return move;
        }

        public int getScore() {
            return score;
        }
    }
}
