package jttt.Core;

public enum Mark {
    X,
    O,
    EMPTY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public String counterForDisplay(int index) {
        return isEmpty() ? String.valueOf(index + 1) : name();
    }

    public Mark opponentCounter() {
        return (this == Mark.X) ? Mark.O : Mark.X;
    }
}
