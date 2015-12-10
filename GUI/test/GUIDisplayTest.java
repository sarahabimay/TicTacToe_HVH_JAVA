import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafxgui.GUIDisplay;
import javafxgui.JavaFxButtonSpy;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GUIDisplayTest {

    private GUIDisplay guiDisplay;
    private Scene scene;
    private ControllerSpy controllerSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        controllerSpy = new ControllerSpy();
        guiDisplay = new GUIDisplay();
        guiDisplay.setController(controllerSpy);
        scene = guiDisplay.displayGUI();
    }

    @Test
    public void initialDisplay() {
        BorderPane borderPane = (BorderPane) scene.getRoot().getChildrenUnmodifiable().get(0);
        assertEquals("borderPane", borderPane.getId());
    }

    @Test
    public void displayHasTitle() {
        HBox titleBar = guiDisplay.titleHeader();
        assertEquals("titleBar", titleBar.getId());

        Text gameTitle = (Text) titleBar.getChildren().get(0);
        assertEquals(guiDisplay.GAME_HEADER, gameTitle.getText());
    }

    @Test
    public void displayDisabledBoard() {
        BorderPane layout = guiDisplay.generateBorderLayout(new Board(3));
        GridPane gameBoard = (GridPane) layout.getCenter();
        assertEquals("gameBoard", gameBoard.getId());
        assertEquals(9, gameBoard.getChildren().size());
    }

    @Test
    public void displayFooterBar() {
        HBox footer = guiDisplay.resultFooter();
        assertEquals("footer", footer.getId());
        assertEquals("resultTarget", footer.getChildren().get(0).getId());
    }

    @Test
    public void displayBoardUsingOptions() {
        Board board = new Board(3);
        GridPane gameBoard = guiDisplay.displayBoard(board);
        assertEquals(9, gameBoard.getChildren().size());
    }

    @Test
    public void buttonRegisteredWithHandler() {
        Button button = new Button();
        JavaFxButtonSpy buttonTest = new JavaFxButtonSpy(button);
        NewPlayerMoveEventHandlerSpy handlerSpy = new NewPlayerMoveEventHandlerSpy(controllerSpy);
        guiDisplay.registerElementWithHandler(buttonTest, handlerSpy);
        assertEquals(true, buttonTest.hasButtonBeenAssociatedWithHandler());
        assertEquals(false, handlerSpy.hasBeenClicked());
        button.fire();
        assertEquals(true, handlerSpy.hasBeenClicked());
    }

    @Test
    public void displayWinningResult() {
        guiDisplay.displayResult(Mark.X);
        assertEquals(String.format(guiDisplay.WINNER_ANNOUNCEMENT, "X"), guiDisplay.announceWinner(Mark.X));
        assertEquals(String.format(guiDisplay.WINNER_ANNOUNCEMENT, "X"), guiDisplay.createResultAnnouncement(Mark.X));
    }

    @Test
    public void displayResult() {
        guiDisplay.displayResult(Mark.EMPTY);
        assertEquals(guiDisplay.DRAW_ANNOUNCEMENT, guiDisplay.announceDraw());
        assertEquals(guiDisplay.DRAW_ANNOUNCEMENT, guiDisplay.createResultAnnouncement(Mark.EMPTY));
    }

    @Test
    public void disableBoard() {
        guiDisplay.disableBoard();
        Button button = (Button)guiDisplay.lookup("#1");
        assertEquals(true, button.isDisabled());
    }
}
