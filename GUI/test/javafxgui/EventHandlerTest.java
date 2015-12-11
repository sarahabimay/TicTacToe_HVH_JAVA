package javafxgui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventHandlerTest {
    @Test
    public void handleNewMoveEvent() {
        ControllerSpy controllerSpy = new ControllerSpy();
        NewPlayerMoveEventHandler startHandler = new NewPlayerMoveEventHandler(controllerSpy);
        startHandler.action("1");
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }

    @Test
    public void handleRestartGameEvent() {
        ControllerSpy controllerSpy = new ControllerSpy();
        NewGameEventHandler newGameHandler = new NewGameEventHandler(controllerSpy);
        newGameHandler.action("");
        assertEquals(true, controllerSpy.hasRestartGameBeenCalled());
    }
}
