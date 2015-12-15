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

    public GUIDisplay(Scene scene, BoardDisplay boardDisplay) {
        this.scene = scene;
        this.boardDisplay = boardDisplay;
        this.border = new BorderPane();
    }

    public Node lookup(String id) {
        return scene.lookup(id);
    }

    public Scene getScene() {
        return scene;
    }

    public Scene displayGUI(Board board) {
        scene.setRoot(generateBorderLayout(board));
        return scene;
    }

    public GridPane displayBoard(Board board) {
        GridPane boardPane = createGameBoard(board);
        border.setCenter(boardPane);
        return boardPane;
    }

    public void displayResult(Mark winner) {
        announceResult(winner);
    }

    public BorderPane generateBorderLayout(Board board) {
        border.setTop(titleHeader());
        border.setCenter(createGameBoard(board));
        border.setBottom(resultFooter());
        border.setId("borderPane");
        return border;
    }

    private GridPane createGameBoard(Board board) {
        return boardDisplay.getBoardForDisplay(board);
    }

    public void disableBoard(Board board) {
        GridPane boardPane = boardDisplay.getDisabledBoard(board);
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
        return playAgain;
    }


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
