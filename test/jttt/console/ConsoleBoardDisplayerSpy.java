package jttt.console;

import jttt.core.UIPresenter;
import jttt.core.board.Board;

public class ConsoleBoardDisplayerSpy extends ConsoleBoardDisplayer {
    private boolean hasBoardBeenDisplayed = false;

    public ConsoleBoardDisplayerSpy(UIPresenter uiPresenter, ConsoleDisplayStyler styler) {
        super(uiPresenter);
    }

    @Override
    public void updateBoardDisplay(Board board) {
        hasBoardBeenDisplayed = true;
    }

    public boolean hasBoardBeenDisplayed() {
        return hasBoardBeenDisplayed;
    }
}
