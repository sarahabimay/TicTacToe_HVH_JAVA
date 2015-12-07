import javafxgui.ClickEventHandler;
import javafxgui.Controller;

public class ControllerSpy extends Controller {
    private StartGameEventHandlerSpy startGameEventSpy;
    private boolean hasCreateAndEnableBeenCalled;

    public ClickEventHandler getStartEventHandler() {
        startGameEventSpy = new StartGameEventHandlerSpy(this);
        return startGameEventSpy;
    }

    public void createAndEnableBoard() {
        hasCreateAndEnableBeenCalled = true;
    }

    public boolean hasStartEventHandlerBeenCalled() {
        return startGameEventSpy.hasBeenClicked();
    }

    public boolean hasBoardBeenCreated() {
        return hasCreateAndEnableBeenCalled;
    }
}
