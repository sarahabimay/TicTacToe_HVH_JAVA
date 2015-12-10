import javafxgui.Controller;
import javafxgui.NewPlayerMoveEventHandler;

public class NewPlayerMoveEventHandlerSpy extends NewPlayerMoveEventHandler {
    private Controller controller;
    private boolean hasBeenClicked = false;

    public NewPlayerMoveEventHandlerSpy(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    public void action(String id) {
        hasBeenClicked = true;
        this.controller.playMoveAtPosition(id);
    }

    public boolean hasBeenClicked() {
        return hasBeenClicked;
    }
}
