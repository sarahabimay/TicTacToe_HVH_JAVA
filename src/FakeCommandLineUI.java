import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class FakeCommandLineUI implements UserInterface {
    private List<Integer> dummyInputs = new ArrayList<>();
    private String gameType = "HVH";
    private boolean playAgain = false;
    private Counter winner = Counter.EMPTY;
    private int numberOfInputs;
    private boolean userHasBeenAskedForDimension = false;
    private boolean userHasBeenAskedForNextPosition = false;
    private boolean haveDisplayedBoardToUser = false;
    private boolean haveDisplayedResultToUser = false;
    private boolean haveAskedUserToQuitGame = false;
    private boolean haveAskedUserForGameType = false;

    public Integer requestBoardSize() {
        int dimension = (int) Math.sqrt(dummyInputs.size());
        userHasBeenAskedForDimension = true;
        return dimension;
    }
    public String requestGameType() {
        haveAskedUserForGameType = true;
        return gameType;
    }

    public Integer requestNextPosition() {
        Integer nextMove = dummyInputs.remove(0);
        while (!validDummyPosition(nextMove)) {
            nextMove = dummyInputs.size() > 0 ? dummyInputs.remove(0) : null;
        }
        userHasBeenAskedForNextPosition = true;
        return nextMove;
    }

    public boolean requestPlayAgain() {
        haveAskedUserToQuitGame = true;
        return playAgain;
    }

    public void outputToUI(String output) {
        System.out.println(output);
    }

    public Counter getWinner() {
        return this.winner;
    }

    public boolean isADraw() {
        return this.winner == Counter.EMPTY;
    }

    public void displayResult(Counter winner) {
        if (!winner.isEmpty()) {
            this.winner = winner;
        }
        haveDisplayedResultToUser = true;
    }

    public String displayBoard(Board board) {
        String output = "";
        haveDisplayedBoardToUser = true;
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i), board);
        }
        return output;
    }
    private String convertRowToString(int index, Counter cellValue, Board board) {
        String cellForDisplay = cellValue.counterForDisplay(index);
        String output = String.format("[%s]", cellForDisplay);
        if (isEndOfRow(index, board)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index, Board board) {
        return (index + 1) % calculateDimension(board) == 0;
    }

    private int calculateDimension(Board board) {
        return (int) Math.sqrt(board.boardSize());
    }

    private boolean validDummyPosition(Integer nextMove) {
        return validate(nextMove, this::validPosition) && nextMove <= numberOfInputs;
    }

    public void addDummyPlayAgainChoice(Integer replayOrQuit) {
        playAgain = doPlayAgain(replayOrQuit);
    }

    public void addDummyInputs(List<Integer> inputs) {
        dummyInputs = inputs;
        numberOfInputs = dummyInputs.size();
    }

    public boolean validate(Integer choiceFromInput, IntPredicate isValidChoice) {
        return choiceFromInput != null && isValidChoice.test(choiceFromInput);
    }

    boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    boolean validPosition(int position) {
        return position > 0;
    }

    boolean doPlayAgain(Integer instruction) {
        return 2 == instruction;
    }

    protected List<Integer> aListOfMoves(Integer[] moves) {
        List<Integer> listOfMoves = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            listOfMoves.add(moves[i]);
        }
        return listOfMoves;
    }

    public boolean hasAskedUserForDimension() {
        return userHasBeenAskedForDimension;
    }

    public boolean hasAskedUserForNextPosition() {
        return userHasBeenAskedForNextPosition;
    }

    public boolean hasDisplayedBoardToUser() {
        return haveDisplayedBoardToUser;
    }

    public boolean hasDisplayedResultToUser() {
        return haveDisplayedResultToUser;
    }

    public boolean hasAskedUserToQuitGame() {
        return haveAskedUserToQuitGame;
    }

    public boolean hasAskedUserForGameType() {
        return haveAskedUserForGameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
