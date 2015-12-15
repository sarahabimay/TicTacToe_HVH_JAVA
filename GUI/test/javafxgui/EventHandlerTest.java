package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventHandlerTest {

    private TTTControllerSpy controllerSpy;

    @Before
    public void setUp() {
        controllerSpy = new TTTControllerSpy(
                new GUIDisplay(new Scene(new StackPane(),700, 600), new BoardDisplay()),
                new EventRegister(),
                new Game(new Board(3), 1, new PlayerFactory()));
    }

    @Test
    public void handleNewMoveEvent() {
        NewPlayerMoveEventHandler startHandler = new NewPlayerMoveEventHandler(controllerSpy);
        startHandler.action("1");
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }

    @Test
    public void handleRestartGameEvent() {
        NewGameEventHandler newGameHandler = new NewGameEventHandler(controllerSpy);
        newGameHandler.action("");
        assertEquals(true, controllerSpy.hasReplayGameBeenSelected());
    }
}
