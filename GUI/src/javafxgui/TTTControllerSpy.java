package javafxgui;

import jttt.Core.Game;

public class TTTControllerSpy extends TTTController {
    private boolean hasReDisplayBoardBeenCalled;

    public TTTControllerSpy(GUIDisplay guiDisplay, Game game) {
        super(guiDisplay, game);
    }

    public void playMoveAtPosition(String id) {
        hasReDisplayBoardBeenCalled = true;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }
}
