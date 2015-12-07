import javafxgui.ClickEventHandler;

public class StartGameEventHandlerSpy implements ClickEventHandler {
    private ControllerSpy controller;
    private boolean hasBeenClicked = false;

    public StartGameEventHandlerSpy(ControllerSpy controller) {
        this.controller = controller;
    }

    public void action() {
        hasBeenClicked = true;
        System.out.println("Start button pressed");
        controller.createAndEnableBoard();
    }

    public boolean hasBeenClicked() {
        return hasBeenClicked;
    }
}
