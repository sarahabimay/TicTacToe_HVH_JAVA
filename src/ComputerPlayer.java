import java.util.HashMap;
import java.util.List;

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
        super(counter, userInterface);
    }

    Board playTurn(Board board) {
        return board.playCounterInPosition(calculateNextMoveWithAlphaBeta(board), counter);
    }

    public Integer calculateNextMoveWithMinimax(Board board) {
        DEPTH = board.numberOfOpenPositions();
        return indexToDisplayPosition(minimax(DEPTH, this.counter, board));
    }

    public int calculateNextMoveWithAlphaBeta(Board board) {
        DEPTH = board.numberOfOpenPositions();
//        return indexToDisplayPosition(aBMinimaxAlternative(DEPTH, this.counter, INITIAL_ALPHA, INITIAL_BETA, board));
        return indexToDisplayPosition(aBMinimax(DEPTH, this.counter, INITIAL_ALPHA, INITIAL_BETA, board));
    }

    private HashMap<String, Integer> aBMinimax(Integer depth, Counter currentCounter, int alpha, int beta, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard);
        }

        for (Integer move : currentBoard.remainingPositions()) {
            if ((move == 2 || move == 1 || move == 5) && depth == 3){
//                System.out.println("debug");
            }
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
            Integer score = aBMinimax(depth - 1, currentCounter.opponentCounter(), alpha, beta, currentStateOfBoard).get(SCORE_KEY);
            if (newBestScore(currentCounter, bestScore, score)) {
                if ((move == 2 || move == 1 || move == 5) && depth == 3){
//                    System.out.println("debug");
                }
                bestScore = score;
                bestMove = move;
            }
            alpha = newAlpha(alpha, score, currentCounter);
            beta = newBeta(beta, score, currentCounter);
            if ((move == 2 || move == 1 || move == 5) && depth == 3){
//                System.out.println("debug");
            }
            if (beta <= alpha) {
                if ((move == 2 || move == 1 || move == 5) && depth == 3){
//                    System.out.println("debug");
                }
                break;
            }
        }
        return createResultMap(bestScore, bestMove);
    }

    private HashMap<String, Integer> aBMinimaxAlternative(Integer depth, Counter currentCounter, int alpha, int beta, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard);
        }
        if (currentCounter == this.counter) {
            bestScore = -1000000;
            for (Integer move : currentBoard.remainingPositions()) {
                Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
                Integer score = aBMinimax(depth - 1, currentCounter.opponentCounter(), alpha, beta, currentStateOfBoard).get(SCORE_KEY);
                if (this.counter == currentCounter && score >= bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
                if (alpha < score) {
                    alpha = score;
                }
                if (beta <= alpha) {
                    break;
                }
            }
        } else if (currentCounter != this.counter) {
            bestScore = 1000000;
            for (Integer move : currentBoard.remainingPositions()) {
                Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
                Integer score = aBMinimax(depth - 1, currentCounter.opponentCounter(), alpha, beta, currentStateOfBoard).get(SCORE_KEY);
                if (this.counter != currentCounter && score <= bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
                if (beta > score) {
                    beta = score;
                }
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return createResultMap(bestScore, bestMove);
    }

    private int newAlpha(int alpha, Integer score, Counter currentCounter) {
        if (currentCounter == this.counter) {
            return max(alpha, score);
        }
        return alpha;
    }

    private int newBeta(int beta, Integer score, Counter currentCounter) {
        if (currentCounter != this.counter) {
            return min(beta, score);
        }
        return beta;
    }

    private int indexToDisplayPosition(HashMap<String, Integer> result) {
        return result.get(MOVE_KEY) + DISPLAY_POSITION_OFFSET;
    }

    private HashMap<String, Integer> minimax(Integer depth, Counter currentCounter, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
        List<Integer> openPositions = currentBoard.remainingPositions();
        if (noMoreMovesAvailable(depth, currentBoard)) {
            return calculateResult(currentBoard);
        }

        for (Integer move : openPositions) {
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
            Integer score = minimax(depth - 1, currentCounter.opponentCounter(), currentStateOfBoard).get(SCORE_KEY);
            if (newBestScore(currentCounter, bestScore, score)) {
                bestScore = score;
                bestMove = move;
            }
        }
        return createResultMap(bestScore, bestMove);
    }

    private boolean newBestScore(Counter currentCounter, Integer bestScore, Integer score) {
        return this.counter == currentCounter && score > bestScore ||
                this.counter != currentCounter && score < bestScore;
    }

    private boolean noMoreMovesAvailable(int depth, Board currentBoard) {
        return currentBoard.isGameOver() || depth == 0;
    }

    private Integer setInitialBestScore(Counter currentCounter) {
        return currentCounter == this.counter ? -INITIAL_SCORE : INITIAL_SCORE;
    }

    private HashMap<String, Integer> calculateResult(Board currentBoard) {
        double score = INITIAL_SCORE / (currentBoard.boardSize() - currentBoard.numberOfOpenPositions());

        if (currentBoard.findWinner().equals(Counter.EMPTY)) {
            return createResultMap(SCORE_FOR_DRAW, -1);
        } else if (currentBoard.findWinner().equals(this.counter)) {
            return createResultMap(score, -1);
        }

        return createResultMap(-score, -1);
//        HashMap<String, Integer> result = createResultMap(currentBoard.calculateBoardScore(this.counter), -1);
//        return result;
    }

    private HashMap<String, Integer> createResultMap(double score, int move) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put(SCORE_KEY, (int)score);
        result.put(MOVE_KEY, move);
        return result;
    }

}
