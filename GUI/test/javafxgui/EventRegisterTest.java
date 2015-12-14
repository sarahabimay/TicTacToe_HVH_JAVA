package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventRegisterTest {


    private EventRegister eventRegister;

    @Before
    public void setUp() {
        new JFXPanel();
        eventRegister = new EventRegister();
    }

    @Test
    public void registerASingleBoardButtonWithPlayMoveHandler() {
        Button button = new Button();
        JavaFxButtonSpy buttonTest = new JavaFxButtonSpy(button);
        ControllerSpy controllerSpy = new ControllerSpy();
        NewPlayerMoveEventHandlerSpy eventHandlerSpy = new NewPlayerMoveEventHandlerSpy(controllerSpy);
        eventRegister.registerBoardButtonWithHandler(buttonTest, eventHandlerSpy);
        button.fire();
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }

    @Test
    public void registerAllBoardButtonsWithHandler() {
       TTTControllerSpy controllerSpy = new TTTControllerSpy(
               new GUIDisplay(new BoardDisplay(new Board(3))),
               new Game(new Board(3), 1, new PlayerFactory()));
        EventRegister eventRegister = new EventRegister();
        Scene scene = controllerSpy.displayGUI();
        GridPane gameBoard = (GridPane)scene.lookup("#gameBoard");
        Button button = (Button)gameBoard.getChildren().get(0);
        eventRegister.registerAllBoardButtonsWithHandler(gameBoard, controllerSpy);
        button.fire();
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }
}
