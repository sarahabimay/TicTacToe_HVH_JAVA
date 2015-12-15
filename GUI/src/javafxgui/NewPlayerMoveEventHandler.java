package javafxgui;

public class NewPlayerMoveEventHandler implements ClickEventHandler {
    private Controller controller;

    public NewPlayerMoveEventHandler(Controller controller) {
        this.controller = controller;
    }

    public void action(String id) {
        controller.playMoveAtPosition(id);
    }
}
