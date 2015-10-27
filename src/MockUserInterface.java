import java.util.ArrayList;
import java.util.List;

public class MockUserInterface extends UserInterface {
    private boolean draw = false;
    private boolean playAgain = false;
    private Counter winner;
    private List<Integer> dummyInputs = new ArrayList<>();
    private int numberOfInputs;

    @Override
    public Integer requestBoardSize() {
        int dimension = (int) Math.sqrt(dummyInputs.size());
        return dimension;
    }

    @Override
    public boolean requestPlayAgain() {
        return playAgain;
    }


    @Override
    public void displayResult(Counter winner) {
        if (winner.isEmpty()) {
            draw = true;
        } else {
            this.winner = winner;
        }
    }

    @Override
    public Integer requestNextPosition() {
        int nextMove = dummyInputs.remove(0);
        while (!validateDummyPosition(nextMove)) {
            nextMove = dummyInputs.remove(0);
        }
        return nextMove;
    }

    private boolean validateDummyPosition(Integer nextMove) {
        return validate(nextMove, this::validPosition) && nextMove <= numberOfInputs;
    }

    public Counter getWinner() {
        return this.winner;
    }

    public boolean isDraw() {
        return draw;
    }

    public void addDummyPlayAgainChoice(Integer replayOrQuit) {
        playAgain = super.doPlayAgain(replayOrQuit);
    }

    public void addDummyInputs(List<Integer> inputs) {
        dummyInputs = inputs;
        numberOfInputs = dummyInputs.size();
    }

    protected List<Integer> aListOfMoves(Integer[] moves) {
        List<Integer> listOfMoves = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            listOfMoves.add(moves[i]);
        }
        return listOfMoves;
    }

}
