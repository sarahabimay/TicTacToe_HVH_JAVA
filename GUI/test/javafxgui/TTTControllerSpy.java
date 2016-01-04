package javafxgui;

import jttt.Core.Game;

public class TTTControllerSpy extends TTTController {
    private boolean hasReDisplayBoardBeenCalled = false;
    private boolean hasReplayGameBeenSelected = false;

    public TTTControllerSpy(GUIView guiView, Game game) {
        super(guiView, game);
    }

    public void createNewGame() {
        hasReplayGameBeenSelected = true;
    }

    public void playMoveAtPosition(String id) {
        hasReDisplayBoardBeenCalled = true;
    }

    public boolean hasReplayGameBeenSelected() {
        return hasReplayGameBeenSelected;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }
}
