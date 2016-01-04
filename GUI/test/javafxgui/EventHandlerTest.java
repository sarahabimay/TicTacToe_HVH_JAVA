package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventHandlerTest {

    private GUIViewSpy guiViewSpy;

    @Before
    public void setUp() {
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), 700, 600), new BoardDisplay(), new EventRegister());
    }

    @Test
    public void handleNewMoveEvent() {
        NewPlayerMoveEventHandler startHandler = new NewPlayerMoveEventHandler(guiViewSpy);
        startHandler.action("1");
        assertEquals(true, guiViewSpy.hasBoardButtonBeenClicked());
    }

    @Test
    public void handleRestartGameEvent() {
        NewGameEventHandler newGameHandler = new NewGameEventHandler(guiViewSpy);
        newGameHandler.action("");
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }
}
