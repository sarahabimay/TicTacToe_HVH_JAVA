public class Player {
    private Counter counter;
    private UserInterface userInterface;

    public Player(Counter counter, UserInterface userInterface) {
        this.counter = counter;
        this.userInterface = userInterface;
    }

    public Counter opponentCounter() {
        if (counter == Counter.X) {
            return Counter.O;
        }
        if (counter == Counter.O) {
            return Counter.X;
        }
        return Counter.EMPTY;
    }

    public Board playTurn(Board board) {
        Integer nextPosition = userInterface.requestNextPosition();
        return board.playCounterInPosition(nextPosition, counter);
    }

    public Counter getCounter() {
        return counter;
    }
}
