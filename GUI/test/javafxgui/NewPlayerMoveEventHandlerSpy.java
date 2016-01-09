package javafxgui;

import javafxgui.event.NewPlayerMoveEventHandler;
import javafxgui.view.GUIView;

public class NewPlayerMoveEventHandlerSpy extends NewPlayerMoveEventHandler {
    private boolean hasBeenClicked = false;

    public NewPlayerMoveEventHandlerSpy(GUIView guiView) {
        super(null);
    }

    public void action(String displayPositionId) {
        hasBeenClicked = true;
    }

    public boolean hasBeenClicked() {
        return hasBeenClicked;
    }
}
