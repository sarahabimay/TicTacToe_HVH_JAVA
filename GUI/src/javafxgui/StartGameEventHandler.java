package javafxgui;

public class StartGameEventHandler implements ClickEventHandler {
    private Controller controller;

    public StartGameEventHandler(Controller controller) {
        this.controller = controller;
    }

    public void action() {
        controller.createAndEnableBoard();
    }
}
