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
    private Scene scene;
    private TTTControllerSpy controllerSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        eventRegister = new EventRegister();
        controllerSpy = new TTTControllerSpy(
                new GUIDisplay(new BoardDisplay(new Board(3))),
                new Game(new Board(3), 1, new PlayerFactory()));
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
        scene = controllerSpy.displayGUI();
        GridPane gameBoard = (GridPane)scene.lookup("#gameBoard");
        Button button = (Button)gameBoard.getChildren().get(0);
        eventRegister.registerAllBoardButtonsWithHandler(gameBoard, controllerSpy);
        button.fire();
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }

    @Test
    public void registerAllClickableElementsWithHandlers() {
        scene = controllerSpy.displayGUI();
        eventRegister.registerAllClickableElementsWithHandler(scene, controllerSpy );

        GridPane gameBoard = (GridPane) scene.lookup("#gameBoard");
        Button button = (Button)gameBoard.getChildren().get(0);
        button.fire();
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }
}
