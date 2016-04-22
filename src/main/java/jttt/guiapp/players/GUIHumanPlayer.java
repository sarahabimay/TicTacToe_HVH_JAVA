package jttt.guiapp.players;

import jttt.core.players.Player;
import jttt.core.board.Board;
import jttt.core.board.Mark;

public class GUIHumanPlayer extends Player {
    private int nextMove;

    public GUIHumanPlayer(Mark mark) {
        super(mark, Type.GUI);
        nextMove = -1;
    }

    @Override
    public boolean isReady() {
        return hasNewMove();
    }

    @Override
    public int getNextPosition(Board board) {
        int playedMove = -1;
        if (hasNewMove()) {
            playedMove = nextMove;
            nextMove = -1;
        }
        return playedMove;
    }

    public boolean hasNewMove() {
        return nextMove != -1;
    }

    public void setNextUserMove(String displayPositionId) {
        this.nextMove = Integer.parseInt(displayPositionId);
    }
}
