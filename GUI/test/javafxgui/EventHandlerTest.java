package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventHandlerTest {

    private GUIViewSpy guiViewSpy;
    private NewPlayerMoveEventHandler newMoveEventHandler;

    @Before
    public void setUp() {
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), 700, 600), new BoardDisplay(), new EventRegister());
        newMoveEventHandler = new NewPlayerMoveEventHandler(guiViewSpy);
    }

    @Test
    public void handleNewMoveEvent() {
        newMoveEventHandler.action("1");
        assertEquals(true, guiViewSpy.hasNextGUIPlayerBeenFound());
        assertEquals(true, guiViewSpy.hasBoardButtonBeenClicked());
    }

    @Test
    public void handleRestartGameEvent() {
        NewGameEventHandler newGameHandler = new NewGameEventHandler(guiViewSpy);
        newGameHandler.action("");
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }
}
