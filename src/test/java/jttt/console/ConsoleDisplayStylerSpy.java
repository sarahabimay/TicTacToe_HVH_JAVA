package jttt.console;

import jttt.core.board.Board;

public class ConsoleDisplayStylerSpy extends ConsoleDisplayStyler {
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
