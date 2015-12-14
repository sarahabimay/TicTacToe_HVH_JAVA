package javafxgui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public class GUIDisplay {

    public static final String GAME_HEADER = "TIC TAC TOE GAME!";
    public static final String WINNER_ANNOUNCEMENT = "PLAYER %s HAS WON!";
    public static final String DRAW_ANNOUNCEMENT = "THE GAME IS A DRAW!";
    private final String RESULTS_LABEL = "RESULTS HERE";
    private final String RESULTS_TARGET_ID = "resultTarget";
    private BoardDisplay boardDisplay;
    private BorderPane border;
    private Scene scene;
    private StackPane root;

    public GUIDisplay(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        this.root = new StackPane();
        this.scene = new Scene(root, 700, 675);
        scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        this.border = new BorderPane();
    }

    public Node lookup(String id) {
        return scene.lookup(id);
    }

    public Scene displayGUI() {
        root.getChildren().add(generateBorderLayout());
        return scene;
    }

    public BorderPane generateBorderLayout() {
        border.setTop(titleHeader());
        border.setCenter(createGameBoard());
        border.setBottom(resultFooter());
        border.setId("borderPane");
        return border;
    }

    private GridPane createGameBoard() {
        return boardDisplay.getBoardForDisplay();
    }

    public GridPane displayBoard(Board board) {
        GridPane boardPane = boardDisplay.getBoardForDisplay();
        border.setCenter(boardPane);
        return boardPane;
    }

    public void displayResult(Mark winner) {
        disableBoard();
        announceResult(winner);
    }

    private void disableBoard() {
        GridPane boardPane = boardDisplay.getDisabledBoard();
        border.setCenter(boardPane);
    }

    private void announceResult(Mark winner) {
        Text resultTarget = (Text) lookup("#resultTarget");
        resultTarget.setText(createResultAnnouncement(winner));
    }

    public HBox titleHeader() {
        HBox titleBar = createHBox("titleBar");
        titleBar.getChildren().add(createTextElement(GAME_HEADER, "gameTitle"));
        return titleBar;
    }

    public VBox resultFooter() {
        VBox resultFooter = createVBox("footer");
        resultFooter.getChildren().add(createGameResultsTarget());
        resultFooter.getChildren().add(createPlayAgainButtonTarget());
        return resultFooter;
    }

    public String createResultAnnouncement(Mark winner) {
        if (winner.isEmpty()) {
            return announceDraw();
        }
        return announceWinner(winner);
    }

    public String announceDraw() {
        return DRAW_ANNOUNCEMENT;
    }

    public String announceWinner(Mark winner) {
        return String.format(WINNER_ANNOUNCEMENT, winner.toString());
    }

    public void makePlayAgainVisible() {
        Button playAgain = (Button) lookup("#playAgain");
        switchElementVisibility(playAgain, true);
    }

    public Button createPlayAgainButtonTarget() {
        Button playAgain = createButton("Play Again?", "playAgain");
        playAgain.setVisible(false);
//        registerPlayAgainButtonWithHandler(new JavaFXButton(playAgain));
        return playAgain;
    }

//    private void registerPlayAgainButtonWithHandler(ClickableElement clickableElement) {
//        registerElementWithHandler(clickableElement, new NewGameEventHandler(controller));
//    }
//
//    public void registerElementWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
//        clickableElement.setOnAction(eventHandler);
//    }


    private void switchElementVisibility(Node element, boolean isVisible) {
        element.setVisible(isVisible);
    }


    private Button createButton(String label, String id) {
        Button playAgain = new Button(label);
        playAgain.setId(id);
        return playAgain;
    }

    private Text createGameResultsTarget() {
        return createTextElement(RESULTS_LABEL, RESULTS_TARGET_ID);
    }

    private HBox createHBox(String id) {
        HBox titleBar = new HBox();
        titleBar.setId(id);
        return titleBar;
    }

    private VBox createVBox(String id) {
        VBox resultFooter = new VBox();
        resultFooter.setId(id);
        return resultFooter;
    }

    private Text createTextElement(String label, String id) {
        Text title = new Text(label);
        title.setId(id);
        return title;
    }
}
