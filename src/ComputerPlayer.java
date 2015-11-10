import java.util.HashMap;
import java.util.List;

public class ComputerPlayer extends Player {
    public final int INITIAL_SCORE = 1000000;
    private int DISPLAY_POSITION_OFFSET = 1;
    private int INITIAL_ALPHA = -10000000;
    private int INITIAL_BETA = 10000000;
    private String MOVE_KEY = "Move";
    private String SCORE_KEY = "Score";
    private Integer DEPTH = 0;

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

    public Integer calculateNextMoveWithAlphaBeta(Board board) {
        DEPTH = board.numberOfOpenPositions();
        return indexToDisplayPosition(aBMinimax(DEPTH, this.counter, INITIAL_ALPHA, INITIAL_BETA, board));
    }

    private HashMap<String, Integer> aBMinimax(Integer depth, Counter currentCounter, int alpha, int beta, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
//        List<Integer> openPositions = currentBoard.findOpenPositions();
        if (noMoreMovesNeeded(depth, currentBoard)) {
            return calculateResult(currentBoard);
        }

        for (Integer move : currentBoard.findOpenPositions()) {
            if ((move == 1 || move == 8) && depth == 7){
//                System.out.println("debug here");
            }
            Board currentStateOfBoard = currentBoard.newBoardWithNewMove(move, currentCounter);
            Integer score = aBMinimax(depth - 1, currentCounter.opponentCounter(), alpha, beta, currentStateOfBoard).get(SCORE_KEY);
            if (newBestScore(currentCounter, bestScore, score)) {
                bestScore = score;
                bestMove = move;
            }
            alpha = newAlpha(alpha, bestScore, currentCounter);
            beta = newBeta(beta, bestScore, currentCounter);
            if ((move == 1 || move == 8) && depth == 7){
//                System.out.println("debug here");
            }
            if (beta <= alpha){
                if ((move == 1 || move == 8) && depth == 7){
//                    System.out.println("debug here");
                }
                break;
            }
        }
        if ((bestMove == 1 || bestMove == 8) && depth == 7){
//            System.out.println("debug here");
        }
        return createResultMap(bestScore, bestMove);
    }

    private int newAlpha(int alpha, Integer score, Counter currentCounter) {
        if (currentCounter == this.counter){
            return Math.max(alpha, score);
        }
        return alpha;
    }

    private int newBeta(int beta, Integer score, Counter currentCounter) {
        if (currentCounter != this.counter) {
            return Math.min(beta, score);
        }
        return beta;
    }

    private Integer indexToDisplayPosition(HashMap<String, Integer> result) {
        return result.get(MOVE_KEY) + DISPLAY_POSITION_OFFSET;
    }

    private HashMap<String, Integer> minimax(Integer depth, Counter currentCounter, Board currentBoard) {
        Integer bestScore = setInitialBestScore(currentCounter);
        Integer bestMove = -1;
        List<Integer> openPositions = currentBoard.findOpenPositions();
        if (noMoreMovesNeeded(depth, currentBoard)) {
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
        return this.counter == currentCounter && score >= bestScore ||
                this.counter != currentCounter && score <= bestScore;
    }

    private boolean noMoreMovesNeeded(Integer depth, Board currentBoard) {
        return currentBoard.isGameOver() || depth == 0 ;//|| openPositions.size() == 0;
    }

    private Integer setInitialBestScore(Counter currentCounter) {
        return currentCounter == this.counter ? -INITIAL_SCORE : INITIAL_SCORE;
    }

    private HashMap<String, Integer> calculateResult(Board currentBoard) {
        HashMap<String, Integer> result = createResultMap(currentBoard.calculateBoardScore(this.counter), -1);
        return result;
    }

    private HashMap<String, Integer> createResultMap(int score, int move) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put(SCORE_KEY, score);
        result.put(MOVE_KEY, move);
        return result;
    }

}
