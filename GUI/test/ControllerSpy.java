import javafx.scene.Scene;
import javafxgui.ClickEventHandler;
import javafxgui.Controller;

public class ControllerSpy implements Controller {
    private StartGameEventHandlerSpy startGameEventSpy;
    private boolean hasCreateAndEnableBeenCalled;

    public ClickEventHandler getStartEventHandler() {
        startGameEventSpy = new StartGameEventHandlerSpy(this);
        return startGameEventSpy;
    }

    public Scene generateLandingPageScene() {
        return null;
    }

    public void createAndEnableBoard() {
        hasCreateAndEnableBeenCalled = true;
    }

    public boolean hasStartEventHandlerBeenCalled() {
        return startGameEventSpy.hasBeenClicked();
    }

    public boolean hasCreateAndEnableBoardBeenCalled() {
        return hasCreateAndEnableBeenCalled;
    }
}
