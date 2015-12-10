import javafxgui.NewPlayerMoveEventHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewPlayerMoveEventHandlerTest {
    @Test
    public void createNewGame() {
        ControllerSpy controllerSpy = new ControllerSpy();
        NewPlayerMoveEventHandler startHandler = new NewPlayerMoveEventHandler(controllerSpy);
        startHandler.action("1");
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }
}
