package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafxgui.event.NewGameEventHandler;
import javafxgui.event.NewPlayerMoveEventHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventHandlerTest {

    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 600;
    private GUIViewSpy guiViewSpy;

    @Before
    public void setUp() {
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), GUI_WINDOW_WIDTH, GUI_WINDOW_HEIGHT));
    }

    @Test
    public void handleNewMoveEvent() {
        NewPlayerMoveEventHandler newMoveEventHandler = new NewPlayerMoveEventHandler(guiViewSpy);
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
