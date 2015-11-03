import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeComputerPlayer extends Player {
    private Integer dummyPosition;
    private boolean hasGeneratedNextMove;
    private List<Integer> dummyPositions;

    public FakeComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, Type.FAKE, userInterface);
        this.dummyPosition = 0;
        this.hasGeneratedNextMove = false;
        this.dummyPositions = new ArrayList<>();
    }

    Board playTurn(Board board) {
        hasGeneratedNextMove = true;
        Integer nextPosition = calculateNextMove(board);
        return board.playCounterInPosition(nextPosition, counter);
    }

    public void setDummyPosition(Integer dummyPosition) {
        this.dummyPositions = new ArrayList<>(
            Arrays.asList(dummyPosition));
    }

    private Integer calculateNextMove(Board board) {
        if (dummyPositions.size()>0){
            return dummyPositions.remove(0);
        }
        System.out.println("No computer positions pre-loaded");
        return null;
    }

    public boolean computerHasGeneratedNextMove() {
        return hasGeneratedNextMove;
    }

    public void setDummyAIMoves(List<Integer> computerMoves) {
        this.dummyPositions = computerMoves;
    }
}
