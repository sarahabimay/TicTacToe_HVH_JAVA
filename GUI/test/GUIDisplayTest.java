import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxgui.ClickEventHandler;
import javafxgui.ClickableElement;
import javafxgui.GUIDisplay;
import jttt.Core.Board.Board;
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
        guiDisplay = new GUIDisplay(controllerSpy);
        scene = guiDisplay.generateLandingPageScene();
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
    public void displayGameMenuOptions() {
        VBox gameOptionPanel = guiDisplay.gameOptions();
        assertEquals("gameOptions", gameOptionPanel.getId());
        Label gameTypeLabel = (Label) gameOptionPanel.getChildren().get(0);
        assertEquals("gameType", gameTypeLabel.getId());
        RadioButton gameTypeRB = (RadioButton) gameOptionPanel.getChildren().get(1);
        assertEquals("HVH", gameTypeRB.getText());
        Label boardDimensionLabel = (Label) gameOptionPanel.getChildren().get(4);
        assertEquals("boardDimension", boardDimensionLabel.getId());
        RadioButton dimensionRB = (RadioButton) gameOptionPanel.getChildren().get(5);
        assertEquals("3x3", dimensionRB.getText());
        Button startButton = (Button) gameOptionPanel.getChildren().get(7);
        assertEquals("startButton", startButton.getId());
    }

    @Test
    public void displayDisabledBoard() {
        BorderPane layout = guiDisplay.generateBorderLayout(new Board(3), true);
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
        GridPane gameBoard = guiDisplay.displayBoard();
        assertEquals(16, gameBoard.getChildren().size());
    }

    @Test
    public void registerStartButtonEventHandler() {
        Button startButton = new Button("Start Game");
        VBox gameOptions = guiDisplay.gameOptions();
//        GameOptionsButtonSpy gameOptionsButton = new GameOptionsButtonSpy(startButton, gameOptions);
        guiDisplay.registerStartButtonWithHandler(startButton, gameOptions);
        startButton.fire();
        assertEquals(true, controllerSpy.hasCreateAndEnableBoardBeenCalled());
    }

    private class GameOptionsButtonSpy implements ClickableElement {
        private VBox gameOptions;
        private Button button;

        public GameOptionsButtonSpy(Button button, VBox gameOptions) {
            this.gameOptions = gameOptions;
            this.button = button;
        }

        @Override
        public void setOnAction(ClickEventHandler clickEventHandler) {
            System.out.println("Event handler was registered");
            ObservableList<Node> children = gameOptions.getChildren();
            for (int i = 0; i < children.size(); i++) {
                System.out.println(children.get(i).toString());
            }
            button.setOnAction(event -> clickEventHandler.action());
        }
    }
}
