package jttt.guiapp.event;

import jttt.guiapp.javafxcomponents.GUIView;

public class NewPlayerMoveEventHandler implements ClickEventHandler {
    private GUIView guiView;

    public NewPlayerMoveEventHandler(GUIView guiView) {
        this.guiView = guiView;
    }

    public void action(String displayPositionId) {
        guiView.playNewMove(displayPositionId);
    }
}
