package javafxgui.controller;

import javafxgui.javafxcomponents.GUIView;
import jttt.core.game.GameMaker;

public class GUIAppControllerStub extends GUIAppController {

    private boolean hasGameStartBeenCalled = false;

    public GUIAppControllerStub(GUIView guiView, GameMaker gameMaker) {
        super(gameMaker, guiView);
    }

    @Override
    public void startGame() {
        hasGameStartBeenCalled = true;
    }

    public boolean hasGameStartBeenCalled() {
        return hasGameStartBeenCalled;
    }
}
