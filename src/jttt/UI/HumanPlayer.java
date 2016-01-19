package jttt.UI;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.players.Player;

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
