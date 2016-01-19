package jttt.UI;

import jttt.core.board.Board;

public class UIDisplayerSpy extends UIDisplayer {
    private boolean hasBoardBeenDisplayed = false;

    public UIDisplayerSpy(UserInterface userInterface, DisplayStyler styler) {
        super(userInterface, styler);
    }

    @Override
    public void updateBoardDisplay(Board board) {
        hasBoardBeenDisplayed = true;
    }

    public boolean hasBoardBeenDisplayed() {
        return hasBoardBeenDisplayed;
    }
}
