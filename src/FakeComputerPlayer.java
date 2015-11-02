public class FakeComputerPlayer extends Player {
    private Integer dummyPosition;
    private boolean hasGeneratedNextMove;

    public FakeComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, Type.COMPUTER, userInterface);
        this.dummyPosition = 0;
        this.hasGeneratedNextMove = false;
    }

    Board playTurn(Board board) {
        hasGeneratedNextMove = true;
        Integer nextPosition = calculateNextMove(board);
        return board.playCounterInPosition(nextPosition, counter);
    }

    public void setDummyPosition(Integer dummyPosition) {
        this.dummyPosition = dummyPosition;
    }

    private Integer calculateNextMove(Board board) {
        return dummyPosition;
    }

    public boolean computerHasGeneratedNextMove() {
        return hasGeneratedNextMove;
    }
}
