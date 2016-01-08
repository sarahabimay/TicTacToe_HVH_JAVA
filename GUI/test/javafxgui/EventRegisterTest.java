package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafxgui.event.EventRegister;
import javafxgui.view.BoardDisplay;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventRegisterTest {


    private EventRegister eventRegister;
    private Scene scene;
    private GUIViewSpy guiViewSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        eventRegister = new EventRegister();
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), 700, 600), new BoardDisplay(), new EventRegister());
    }

    @Test
    public void registerAllBoardButtonsWithHandler() {
        scene = guiViewSpy.displayGameLayoutComponent(new Board(3));
        GridPane gameBoard = (GridPane) scene.lookup("#gameBoard");
        Button button = (Button) gameBoard.getChildren().get(0);
        eventRegister.registerAllBoardButtonsWithHandler(gameBoard, guiViewSpy);
        button.fire();
        assertEquals(true, guiViewSpy.hasBoardButtonBeenClicked());
    }

    @Test
    public void registerReplayButtonWithHandler() {
        scene = guiViewSpy.displayGameLayoutComponent(new Board(3));
        Button replayButton = (Button) scene.lookup("#playAgain");
        eventRegister.registerAllClickableElementsWithHandler(scene, guiViewSpy);
        replayButton.fire();
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }

    @Test
    public void registerAllClickableElementsWithHandlers() {
        scene = guiViewSpy.displayGameLayoutComponent(new Board(3));
        eventRegister.registerAllClickableElementsWithHandler(scene, guiViewSpy);

        GridPane gameBoard = (GridPane) scene.lookup("#gameBoard");
        Button button = (Button) gameBoard.getChildren().get(0);
        button.fire();
        assertEquals(true, guiViewSpy.hasBoardButtonBeenClicked());
        Button replayButton  = (Button) scene.lookup("#playAgain");
        replayButton.fire();
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }
}
