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
        System.out.println("Please provide the dimensions of the board:\n");
        System.out.println(String.format("%d", dimension));
        return dimension;
    }

    @Override
    public boolean requestPlayAgain() {
        return playAgain;
    }


    @Override
    public void displayResult(Counter winner) {
        if (winner.isEmpty()) {
            System.out.println("Is a draw");
            draw = true;
        } else {
            System.out.println(String.format("And the Winner is: %s", winner.toString()));
            this.winner = winner;
        }
    }

    @Override
    public Integer requestNextPosition() {
        System.out.println("Please enter the position number for your next move: \n");
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
