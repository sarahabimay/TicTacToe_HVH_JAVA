package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.UI.UserInterface;

public class HumanPlayer extends Player {

    private final UserInterface userInterface;

    public HumanPlayer(Mark mark, UserInterface userInterface) {
        super(mark, Type.HUMAN);
        this.userInterface = userInterface;
    }

    public int getNextPosition(Board board) {
        return userInterface.requestNextPosition(board);
    }
}
