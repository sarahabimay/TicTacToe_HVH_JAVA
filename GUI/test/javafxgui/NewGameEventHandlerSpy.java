package javafxgui;

import javafxgui.ClickEventHandler;
import javafxgui.Controller;

public class NewGameEventHandlerSpy implements ClickEventHandler {
    private Controller controller;
    private boolean hasBeenClicked = false;

    public NewGameEventHandlerSpy(Controller controller) {
        this.controller = controller;
    }

    public void action(String id) {
        hasBeenClicked = true;
        this.controller.createNewGame();
    }

    public boolean hasBeenClicked() {
        return hasBeenClicked;
    }
}
