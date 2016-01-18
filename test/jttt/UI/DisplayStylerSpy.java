package jttt.UI;

import jttt.core.board.Board;

public class DisplayStylerSpy extends DisplayStyler {
    private boolean hasBoardBeenStyledForDisplay = false;

    @Override
    public String createBoardForDisplay(Board board) {
        hasBoardBeenStyledForDisplay = true;
        return "";
    }

    public boolean hasBoardBeenStyledForDisplay() {
        return hasBoardBeenStyledForDisplay;
    }
}
