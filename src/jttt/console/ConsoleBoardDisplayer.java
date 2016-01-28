package jttt.console;

import jttt.core.UIPresenter;
import jttt.core.board.Board;
import jttt.core.boarddisplayer.BoardDisplayer;

public class ConsoleBoardDisplayer implements BoardDisplayer {
    private UIPresenter uiPresenter;

    public ConsoleBoardDisplayer(UIPresenter uiPresenter) {
        this.uiPresenter = uiPresenter;
    }

    @Override
    public void updateBoardDisplay(Board board) {
        uiPresenter.displayGameLayout(board);
    }
}
