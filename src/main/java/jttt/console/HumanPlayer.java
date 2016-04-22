package jttt.console;

import jttt.core.board.Board;
import jttt.core.players.Player;
import jttt.core.UIReader;
import jttt.core.board.Mark;

public class HumanPlayer extends Player {

    private final UIReader uiReader;

    public HumanPlayer(Mark mark, UIReader uiReader) {
        super(mark, Type.HUMAN);
        this.uiReader = uiReader;
    }

    public int getNextPosition(Board board) {
        return uiReader.requestNextPosition();
    }
}
