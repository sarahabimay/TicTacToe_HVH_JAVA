public class HumanPlayer extends Player {

    public HumanPlayer(Counter counter, UserInterface userInterface) {
        super(counter, userInterface);
    }

    public Board playTurn(Board board) {
        Integer nextPosition = userInterface.requestNextPosition();
        while (!board.validPosition(nextPosition)) {
            nextPosition = userInterface.requestNextPosition();
        }
        return board.playCounterInPosition(nextPosition, counter);
    }
}
