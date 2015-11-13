package jttt.Core;

public abstract class Player {
    Counter counter;
    UserInterface userInterface;

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

    abstract Board playTurn(Board board);

    abstract Board playTurn(Board board, int newPosition);

    public Counter getCounter() {
        return counter;
    }

}
