package jttt.Core;

public class HumanPlayerNoUI extends Player {
    public HumanPlayerNoUI(Counter counter) {
    super(counter, Type.HUMAN, null);
    }

    @Override
    Board playTurn(Board board, int newPosition) {
        return board.playCounterInPosition(newPosition, counter);
    }

    @Override
    Board playTurn(Board board) {
        return null;
    }
}
