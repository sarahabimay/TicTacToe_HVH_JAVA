package jttt.core.board;

public enum Mark {
    X,
    O,
    EMPTY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public String markOrPositionForDisplay(int index) {
        return isEmpty() ? String.valueOf(index + 1) : name();
    }

    public Mark opponentCounter() {
        return (this == Mark.X) ? Mark.O : Mark.X;
    }
}
