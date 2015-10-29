public class ComputerPlayer extends Player {
    public ComputerPlayer(Counter counter, UserInterface userInterface){
        super(counter, userInterface);
    }

    @Override
    Board playTurn(Board board) {
        Integer nextPosition = 2;
        return board.playCounterInPosition(nextPosition, counter);
    }
}
