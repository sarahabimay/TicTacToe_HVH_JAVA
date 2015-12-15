package javafxgui;

public class NewGameEventHandler implements ClickEventHandler{
    private final Controller controller;

    public NewGameEventHandler(Controller controller) {
        this.controller = controller;
    }

    public void action(String id) {
        controller.createNewGame();
    }
}
