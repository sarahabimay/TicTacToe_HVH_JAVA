package javafxgui.javafxcomponents;

import jttt.core.UIPresenter;
import jttt.core.board.Board;
import jttt.core.boarddisplayer.BoardDisplayer;

public class GUIBoardDisplayer implements BoardDisplayer {
    private UIPresenter uiPresenter;

    public GUIBoardDisplayer(UIPresenter uiPresenter) {
        this.uiPresenter = uiPresenter;
    }

    @Override
    public void updateBoardDisplay(Board board) {
        uiPresenter.displayGameLayout(board);
    }
}
