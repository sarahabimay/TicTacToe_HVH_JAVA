package jttt.Core.Players;

import jttt.Core.Board;
import jttt.Core.Mark;
import jttt.UI.UserInterface;

public class HumanPlayer extends Player {

    public HumanPlayer(Mark mark, UserInterface userInterface) {
        super(mark, Type.HUMAN, userInterface);
    }

    public Board playTurn(Board board) {
        int nextPosition = userInterface.requestNextPosition();
        while (!board.validPosition(nextPosition)) {
            nextPosition = userInterface.requestNextPosition();
        }
        return board.playCounterInPosition(nextPosition, mark);
    }

    public Board playTurn(Board board, int newPosition) {
        return board.playCounterInPosition(newPosition, mark);
    }
}
