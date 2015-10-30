public class HumanPlayer extends Player {

    public HumanPlayer(Counter counter, Type type, UserInterface userInterface) {
        super(counter, type, userInterface);
    }

    @Override
    public Board playTurn(Board board) {
        Integer nextPosition = userInterface.requestNextPosition();
        return board.playCounterInPosition(nextPosition, counter);
    }
}
