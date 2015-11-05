import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class FakeCommandLineUI implements UserInterface {
    private List<Integer> dummyInputs = new ArrayList<>();
    private Integer playerType = 1;
    private boolean playAgain = false;
    private Counter winner = Counter.EMPTY;
    private boolean userHasBeenAskedForDimension = false;
    private boolean userHasBeenAskedForNextPosition = false;
    private boolean haveDisplayedBoardToUser = false;
    private boolean haveDisplayedResultToUser = false;
    private boolean haveAskedUserToQuitGame = false;
    private boolean haveAskedUserForGameType = false;
    private boolean haveValidatedGameType = false;
    private int dummyDimension = 0;

    public Integer requestBoardSize() {
        userHasBeenAskedForDimension = true;
        return dummyDimension;
    }

    public Integer requestGameType() {
        haveAskedUserForGameType = true;
        if (!validate(playerType, this::validGameType)) {
            playerType = -1;
        }
        return playerType;
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

    public String displayBoard(Board board) {
        String output = "";
        haveDisplayedBoardToUser = true;
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i), board);
        }
        return output;
    }

    public void printCurrentCounter(Counter currentCounter) {

    }

    public void addDummyPlayAgainChoice(Integer replayOrQuit) {
        playAgain = doPlayAgain(replayOrQuit);
    }

    public void addDummyDimension(int dimension) {
        this.dummyDimension = dimension;
    }

    public void addDummyHumanMoves(List<Integer> inputs) {
        dummyInputs = inputs;
    }

    public boolean validate(Integer choiceFromInput, IntPredicate isValidChoice) {
        return choiceFromInput != null && isValidChoice.test(choiceFromInput);
    }

    public boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    public boolean validPosition(int position) {
        return position > 0;
    }

    public boolean validInstruction(int instruction) {
        return 0 < instruction && instruction < 3;
    }

    public boolean validGameType(int choice) {
        haveValidatedGameType = true;
        return choice == 1 || choice == 2 || choice == 3;
    }

    public void outputToUI(String output) {
//        System.out.println(output);
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

    protected List<Integer> aListOfMoves(Integer[] moves) {
        List<Integer> listOfMoves = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            listOfMoves.add(moves[i]);
        }
        return listOfMoves;
    }

    // made public just for testing
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

    public void setGameType(Integer gameType) {
        this.playerType = gameType;
    }

    public boolean hasGameTypeBeenValidated() {
        return haveValidatedGameType;
    }

    boolean doPlayAgain(Integer instruction) {
        return 2 == instruction;
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
        return validate(nextMove, this::validPosition);//&& nextMove <= numberOfInputs;
    }


}
