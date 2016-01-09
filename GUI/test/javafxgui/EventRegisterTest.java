package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafxgui.event.EventRegister;
import javafxgui.view.BoardDisplay;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventRegisterTest {


    private EventRegister eventRegister;
    private Scene scene;
    private GUIViewSpy guiViewSpy;
    private final int DEFAULT_WINDOW_WIDTH = 600;
    private final int DEFAULT_WINDOW_HEIGHT = 700;
    private GUIView guiView;

    @Before
    public void setUp() {
        new JFXPanel();
        eventRegister = new EventRegister();
        guiViewSpy = new GUIViewSpy(new Scene(new StackPane(), DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_HEIGHT), new BoardDisplay(), eventRegister);
        guiView = new GUIView(new Scene(new StackPane(), DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_HEIGHT), new BoardDisplay(), eventRegister);
    }

    @Test
    public void registerAllBoardButtonsWithHandler() {
        scene = guiView.displayGameLayoutComponent(new Board(3));
        GridPane gameBoard = (GridPane) scene.lookup("#gameBoard");
        eventRegister.registerAllBoardButtonsWithHandler(gameBoard, guiViewSpy);

        Button button = (Button) gameBoard.getChildren().get(0);
        button.fire();
        assertEquals(true, guiViewSpy.hasBoardButtonBeenClicked());
    }

    @Test
    public void registerReplayButtonWithHandler() {
        scene = guiView.displayGameLayoutComponent(new Board(3));
        Button replayButton = (Button) scene.lookup("#playAgain");
        eventRegister.registerAllClickableElementsWithHandler(scene, guiViewSpy);
        replayButton.fire();
        assertEquals(true, guiViewSpy.hasReplayGameBeenSelected());
    }

    @Test
    public void registerAllClickableElementsWithHandlers() {
        scene = guiView.displayGameLayoutComponent(new Board(3));
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
