public abstract class Player {
    protected Counter counter;
    protected UserInterface userInterface;
    private Type playerType;

    public enum Type {
        Human,
        Computer,
    }

    public Type getPlayerType() {
        return playerType;
    }

    public Player(Counter counter, Type type, UserInterface userInterface) {
        this.counter = counter;
        this.playerType = type;
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
