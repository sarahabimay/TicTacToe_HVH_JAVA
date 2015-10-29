public abstract class Player {
    protected Counter counter;
    protected UserInterface userInterface;

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

    public Counter getCounter() {
        return counter;
    }
}
