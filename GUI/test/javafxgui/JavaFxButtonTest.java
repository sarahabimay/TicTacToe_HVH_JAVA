package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFxButtonTest {
    TTTControllerSpy controllerSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        controllerSpy = new TTTControllerSpy(
                new GUIDisplay(new Scene(new StackPane(),700, 600), new BoardDisplay()),
                new EventRegister(),
                new Game(new Board(3), 1, new PlayerFactory()));
    }

    @Test
    public void registerJavaFxElementWithActionHandler() {
        Button boardButton = new Button("1");
        boardButton.setId("1");
        JavaFXButton javaFXButton = new JavaFXButton(boardButton);
        NewPlayerMoveEventHandlerSpy handler = new NewPlayerMoveEventHandlerSpy(controllerSpy);
        javaFXButton.setOnAction(handler);
        boardButton.fire();
        assertEquals(true, handler.hasBeenClicked());
        assertEquals(true, controllerSpy.hasReDisplayBoardBeenCalled());
    }
}
