package jttt.UI;

import jttt.core.displayer.Displayer;
import jttt.core.board.Board;

public class UIDisplayer implements Displayer {
    private DisplayStyler displayStyler;
    private UserInterface userInterface;

    public UIDisplayer(UserInterface userInterface, DisplayStyler styler) {
        this.userInterface = userInterface;
        this.displayStyler = styler;
    }

    @Override
    public void updateBoardDisplay(Board board) {
        userInterface.displayBoardToUser(displayStyler.createBoardForDisplay(board));
    }
}
