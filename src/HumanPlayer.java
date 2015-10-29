public class HumanPlayer extends Player {

    public HumanPlayer(Counter counter, UserInterface userInterface) {
        super(counter, userInterface);
    }

    @Override
    public Board playTurn(Board board) {
        Integer nextPosition = userInterface.requestNextPosition();
        return board.playCounterInPosition(nextPosition, counter);
    }

}
