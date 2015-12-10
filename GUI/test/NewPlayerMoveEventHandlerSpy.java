import javafxgui.ClickEventHandler;

public class NewPlayerMoveEventHandlerSpy implements ClickEventHandler {
    private ControllerSpy controller;
    private boolean hasBeenClicked = false;

    public NewPlayerMoveEventHandlerSpy() {
        this.controller = null;
    }

    public NewPlayerMoveEventHandlerSpy(ControllerSpy controller) {
        this.controller = controller;
    }

    public void action(String id) {
        hasBeenClicked = true;
        System.out.println("Start button pressed");
        controller.reDisplayBoard(id);
    }

    public boolean hasBeenClicked() {
        return hasBeenClicked;
    }

    public void addController(ControllerSpy controller) {
        this.controller = controller;
    }
}
