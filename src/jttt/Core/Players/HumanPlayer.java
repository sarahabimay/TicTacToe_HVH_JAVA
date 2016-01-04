package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.UI.UserInterface;

public class HumanPlayer extends Player {

    public HumanPlayer(Mark mark, UserInterface userInterface) {
        super(mark, Type.HUMAN, userInterface);
    }

    public int getNextPosition(Board board) {
        return userInterface.requestNextPosition(board);
    }

    public Board playTurn(Board board, int newPosition) {
        return board.playCounterInPosition(newPosition, mark);
    }
}
