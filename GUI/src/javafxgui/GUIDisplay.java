package javafxgui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import jttt.Core.Board.Board;

public class GUIDisplay {

    private int DISPLAY_OFFSET = 1;
    public final String GAME_HEADER = "TIC TAC TOE GAME!";

    public Scene generateLandingPageScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 700, 675);
        scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        root.getChildren().add(generateBorderLayout(new Board(3), true));
        return scene;
    }

    public BorderPane generateBorderLayout(Board board, boolean setDisabled) {
        BorderPane border = new BorderPane();
        border.setTop(titleHeader());
        border.setLeft(gameOptions());
        border.setCenter(createGameBoard(board, setDisabled));
        border.setBottom(resultFooter());
        border.setId("borderPane");
        return border;
    }

    public HBox resultFooter() {
        HBox resultFooter = new HBox();
        resultFooter.setId("footer");
        resultFooter.getChildren().add(createGameResultsTarget());
        return resultFooter;
    }

    private Text createGameResultsTarget() {
        Text resultTarget = new Text("RESULTS HERE");
        resultTarget.setId("resultTarget");
        return resultTarget;
    }

    private GridPane createGameBoard(Board board, boolean setDisabled) {
        GridPane boardGrid = new GridPane();
        boardGrid.setId("gameBoard");
        boardGrid = generateBoardCells(board, boardGrid, setDisabled );
        return boardGrid;
    }

    private GridPane generateBoardCells(Board board, GridPane boardGrid, boolean setDisabled) {
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                boardGrid.add(boardCell(displayPosition(i), setDisabled), j, i);
            }
        }
        return boardGrid;
    }

    private Button boardCell(String position, boolean setDisabled){
        Button cell = new Button(position);
        cell.setId(position);
        cell.setDisable(setDisabled);
        return cell;
    }

    private String displayPosition(int i) {
        return String.valueOf(i + DISPLAY_OFFSET);
    }

    public HBox titleHeader() {
        HBox titleBar = new HBox();
        titleBar.setId("titleBar");
        Text title = new Text(GAME_HEADER);
        title.setId("gameTitle");
        titleBar.getChildren().add(title);
        return titleBar;
    }

    public VBox gameOptions() {
        VBox vbox = new VBox();
        vbox.setId("gameOptions");

        vbox = createGameType(vbox);
        vbox = createBoardDimension(vbox);
        vbox = createStartButton(vbox);
        return vbox;
    }

    private VBox createStartButton(VBox vbox) {
        Button button = new Button("Start Game");
        button.setId("startButton");
        vbox.getChildren().add(button);
        JavaFXButton javafxButton = new JavaFXButton(button);
//        Text actiontarget = new Text();
//        vbox.getChildren().add(actiontarget);
        return vbox;
    }

    private VBox createBoardDimension(VBox vbox) {
        Label boardDimension = new Label("Board:");
        boardDimension.setId("boardDimension");
        vbox.getChildren().add(boardDimension);
        vbox = addBoardDimensionRB(vbox);
        return vbox;
    }

    private VBox addBoardDimensionRB(VBox vbox) {
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("3x3");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("4x4");
        rb2.setToggleGroup(group);

        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        return vbox;
    }

    private VBox createGameType(VBox vbox) {
        Label gameTypelabel = new Label("Game Type:");
        gameTypelabel.setId("gameType");
        vbox.getChildren().add(gameTypelabel);
        vbox = addGameTypeGroup(vbox);
        return vbox;
    }

    private VBox addGameTypeGroup(VBox vbox) {
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("HVH");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("HVC");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("CVH");
        rb3.setToggleGroup(group);

        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(rb3);
        return vbox;
    }

    private VBox rightBorder() {
        VBox vbox = new VBox();
        vbox.setId("rightBorder");
        return vbox;
    }
}
