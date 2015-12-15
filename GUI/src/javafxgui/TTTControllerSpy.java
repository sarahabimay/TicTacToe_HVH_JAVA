package javafxgui;

import jttt.Core.Game;

public class TTTControllerSpy extends TTTController {
    private boolean hasReDisplayBoardBeenCalled;

    public TTTControllerSpy(GUIDisplay guiDisplay, EventRegister eventRegister, Game game) {
        super(guiDisplay, eventRegister, game);
    }

    public void playMoveAtPosition(String id) {
        hasReDisplayBoardBeenCalled = true;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }
}
