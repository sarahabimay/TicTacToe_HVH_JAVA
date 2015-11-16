package jttt.Core;

public abstract class Player {
    public static enum Type{
        AI,
        HUMAN
    }
    Counter counter;
    UserInterface userInterface;
    Type playerType;

    public Player(Counter counter, Type type, UserInterface userInterface) {
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

    public Type getPlayerType() {
        return playerType;
    }

    abstract Board playTurn(Board board);

    abstract Board playTurn(Board board, int newPosition);

    public Counter getCounter() {
        return counter;
    }

}
