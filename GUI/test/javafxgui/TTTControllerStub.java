package javafxgui;

import javafxgui.view.GUIView;
import jttt.Core.Game;
import jttt.Core.GameType;

public class TTTControllerStub extends TTTController {

    private boolean hasGameStartBeenCalled = false;

    public TTTControllerStub(GUIView guiView) {
        super(guiView);
    }

    public TTTControllerStub(GUIView guiView, Game game) {
        super(guiView, game);
    }

    @Override
    public void startGame(GameType gameType, int boardDimension) {
        hasGameStartBeenCalled = true;
    }

    public void createNewGame() {
    }

    public void playGame() {
    }

    public boolean hasGameStartBeenCalled() {
        return hasGameStartBeenCalled;
    }
}
