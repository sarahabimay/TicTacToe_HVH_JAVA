package javafxgui.event;

import javafxgui.view.GUIView;

public class NewGameEventHandler implements ClickEventHandler {
    private final GUIView guiView;

    public NewGameEventHandler(GUIView guiView) {
        this.guiView = guiView;
    }

    public void action(String displayPositionId) {
        guiView.createNewGame();
    }
}
