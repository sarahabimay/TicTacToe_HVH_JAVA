package javafxgui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GUIDisplayTest {

    private GUIDisplay guiDisplay;
    private Scene scene;

    @Before
    public void setUp() {
        new JFXPanel();
        guiDisplay = new GUIDisplay(new BoardDisplay(new Board(3)));
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
        BorderPane layout = guiDisplay.generateBorderLayout();
        GridPane gameBoard = (GridPane) layout.getCenter();
        assertEquals("gameBoard", gameBoard.getId());
    }

    @Test
    public void displayFooterBar() {
        VBox footer = guiDisplay.resultFooter();
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
    public void createPlayAgainButtonTarget() {
        Button playAgain = guiDisplay.createPlayAgainButtonTarget();
        assertEquals("playAgain", playAgain.getId());
        assertEquals(false, playAgain.isVisible());
    }

    @Test
    public void displayPlayAgainButton() {
        Button replayButton = (Button) guiDisplay.lookup("#playAgain");
        assertEquals(false, replayButton.isVisible());
        guiDisplay.makePlayAgainVisible();
        assertThat(replayButton, notNullValue());
        assertEquals(true, replayButton.isVisible());
    }
}
