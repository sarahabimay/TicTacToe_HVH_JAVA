import javafxgui.StartGameEventHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StartGameEventHandlerTest {
    @Test
    public void createNewGame() {
        ControllerSpy controllerSpy = new ControllerSpy();
        StartGameEventHandler startHandler = new StartGameEventHandler(controllerSpy);
        startHandler.action();
        assertEquals(true, controllerSpy.hasBoardBeenCreated());
    }
}
