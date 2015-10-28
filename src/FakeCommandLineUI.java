import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class FakeCommandLineUI implements UserInterface {
    private boolean draw = false;
    private boolean playAgain = false;
    private Counter winner;
    private List<Integer> dummyInputs = new ArrayList<>();
    private int numberOfInputs;
    private boolean userHasBeenAskedForDimension = false;
    private boolean userHasBeenAskedForNextPosition = false;
    private boolean haveDisplayedBoardToUser = false;
    private boolean haveDisplayedResultToUser = false;
    private boolean haveRequestedToQuit = false;

    public Integer requestBoardSize() {
        int dimension = (int) Math.sqrt(dummyInputs.size());
        userHasBeenAskedForDimension = true;
        return dimension;
    }

    public Integer requestNextPosition() {
        Integer nextMove = dummyInputs.remove(0);
        while (!validDummyPosition(nextMove)) {
            nextMove = dummyInputs.size()>0 ? dummyInputs.remove(0) : null;
        }
        userHasBeenAskedForNextPosition = true;
        return nextMove;
    }

    public boolean requestPlayAgain() {
        return playAgain;
    }

    public void displayResult(Counter winner) {
        if (winner.isEmpty()) {
            draw = true;
        } else {
            this.winner = winner;
        }
        haveDisplayedResultToUser = true;
    }

    public String displayBoard(Board board) {
        haveDisplayedBoardToUser = true;
        return null;
    }

    public boolean requestToContinueGame() {
        if( dummyInputs.size() == 0){
            haveRequestedToQuit = true;
        }
        return haveRequestedToQuit;
    }

    private boolean validDummyPosition(Integer nextMove) {
        return validate(nextMove, this::validPosition) && nextMove <= numberOfInputs;
    }

    public Counter getWinner() {
        return this.winner;
    }

    public boolean isDraw() {
        return draw;
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

    public boolean hasRequestedToQuit() {
        return haveRequestedToQuit;
    }
}
