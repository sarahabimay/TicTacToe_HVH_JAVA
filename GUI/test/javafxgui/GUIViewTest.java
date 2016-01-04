package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GUIViewTest {

    private GUIView guiView;
    private Scene scene;
    private Board defaultBoard;
    private EventRegisterSpy eventRegisterSpy;

    @Before
    public void setUp() {
        new JFXPanel();
        defaultBoard = new Board(3);
        eventRegisterSpy = new EventRegisterSpy();
        guiView = new GUIView(new Scene(new StackPane(),700, 600), new BoardDisplay(), eventRegisterSpy);
        scene = guiView.displayGUI(defaultBoard);
    }

    @Test
    public void initialDisplay() {
        BorderPane borderPane = (BorderPane) scene.getRoot();
        assertEquals("borderPane", borderPane.getId());
    }

    @Test
    public void displayHasTitle() {
        HBox titleBar = guiView.titleHeader();
        assertEquals("titleBar", titleBar.getId());

        Text gameTitle = (Text) titleBar.getChildren().get(0);
        assertEquals(guiView.GAME_HEADER, gameTitle.getText());
    }

    @Test
    public void displayDisabledBoard() {
        BorderPane layout = guiView.generateBorderLayout(new Board(3));
        GridPane gameBoard = (GridPane) layout.getCenter();
        assertEquals("gameBoard", gameBoard.getId());
    }

    @Test
    public void displayFooterBar() {
        VBox footer = guiView.resultFooter();
        assertEquals("footer", footer.getId());
        assertEquals("resultTarget", footer.getChildren().get(0).getId());
    }

    @Test
    public void displayBoardUsingOptions() {
        Board board = new Board(3);
        GridPane gameBoard = guiView.displayBoard(board);
        assertEquals(9, gameBoard.getChildren().size());
    }

    @Test
    public void displayWinningResult() {
        guiView.displayResult(Mark.X);
        assertEquals(String.format(guiView.WINNER_ANNOUNCEMENT, "X"), guiView.announceWinner(Mark.X));
        assertEquals(String.format(guiView.WINNER_ANNOUNCEMENT, "X"), guiView.createResultAnnouncement(Mark.X));
    }

    @Test
    public void displayResult() {
        guiView.displayResult(Mark.EMPTY);
        assertEquals(guiView.DRAW_ANNOUNCEMENT, guiView.announceDraw());
        assertEquals(guiView.DRAW_ANNOUNCEMENT, guiView.createResultAnnouncement(Mark.EMPTY));
    }

    @Test
    public void createPlayAgainButtonTarget() {
        Button playAgain = guiView.createPlayAgainButtonTarget();
        assertEquals("playAgain", playAgain.getId());
        assertEquals(false, playAgain.isVisible());
    }

    @Test
    public void displayPlayAgainButton() {
        Button replayButton = (Button) guiView.lookup("#playAgain");
        assertEquals(false, replayButton.isVisible());
        guiView.makePlayAgainVisible();
        assertThat(replayButton, notNullValue());
        assertEquals(true, replayButton.isVisible());
    }
    @Test
    public void registerAllBoardButtonsWithEventHandler() {
        guiView.displayBoard(defaultBoard);
        assertEquals(true, eventRegisterSpy.haveBoardButtonsBeenRegistered());
    }

    @Test
    public void registerAllElementsWithEventHandler() {
        guiView.displayGUI(defaultBoard);
        assertEquals(true, eventRegisterSpy.hasAllElementsBeenRegistered());
    }

    private class EventRegisterSpy extends EventRegister {
        private boolean hasRegisteredAllBoardButtons = false;
        private boolean hasRegisteredAllClickableElements = false;

        @Override
        public void registerAllBoardButtonsWithHandler(GridPane board, GUIView guiView) {
            hasRegisteredAllBoardButtons = true;
        }

        @Override
        public void registerAllClickableElementsWithHandler(Scene scene, GUIView guiView) {
            hasRegisteredAllClickableElements = true;
        }

        public boolean haveBoardButtonsBeenRegistered() {
            return hasRegisteredAllBoardButtons;
        }

        public boolean hasAllElementsBeenRegistered() {
            return hasRegisteredAllClickableElements;
        }
    }
}
