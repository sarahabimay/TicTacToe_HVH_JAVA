public enum Counter {
    X,
    O,
    EMPTY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public String counterForDisplay(int index) {
        return isEmpty() ? String.valueOf(index + 1) : name();
    }

    public Counter opponentCounter() {
        return (this == Counter.X) ? Counter.O : Counter.X;
    }
}
