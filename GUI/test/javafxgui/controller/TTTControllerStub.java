package javafxgui.controller;

import javafxgui.javafxcomponents.GUIView;
import jttt.core.game.GameMaker;
import jttt.core.game.GameType;

public class TTTControllerStub extends TTTController {

    private boolean hasGameStartBeenCalled = false;

    public TTTControllerStub(GUIView guiView, GameMaker gameMaker) {
        super(guiView, gameMaker);
    }

    @Override
    public void startGame(GameType gameType, int boardDimension) {
        hasGameStartBeenCalled = true;
    }

    public boolean hasGameStartBeenCalled() {
        return hasGameStartBeenCalled;
    }
}
