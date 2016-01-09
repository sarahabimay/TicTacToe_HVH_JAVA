package javafxgui.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxgui.Controller;
import javafxgui.event.EventRegister;
import javafxgui.javafxcomponents.JFXGameOptionsPage;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.GameType;
import jttt.Core.Players.Player;

public class GUIView {

    public static final String GAME_HEADER = "TIC TAC TOE GAME!";
    public static final String WINNER_ANNOUNCEMENT = "PLAYER %s HAS WON!";
    public static final String DRAW_ANNOUNCEMENT = "THE GAME IS A DRAW!";
    private final int DEFAULT_BOARD_DIMENSION = 3;
    private final String RESULTS_LABEL = "RESULTS HERE";
    private final String RESULTS_TARGET_ID = "resultTarget";
    private BoardDisplay boardDisplay;
    private BorderPane border;
    private Scene scene;
    private EventRegister eventRegister;
    private Controller controller;

    public GUIView(Scene scene, BoardDisplay boardDisplay, EventRegister eventRegister) {
        this.scene = scene;
        this.boardDisplay = boardDisplay;
        this.eventRegister = eventRegister;
        this.controller = null;
        this.border = new BorderPane();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Player getCurrentPlayer() {
        return controller.getCurrentPlayer();
    }

    public Node lookup(String id) {
        return scene.lookup(id);
    }

    public void displayGameOptions() {
        scene.setRoot(new JFXGameOptionsPage(this));
    }

    public Scene displayGameLayoutComponent(Board board) {
        scene.setRoot(generateBorderLayout(board));
        registerEventHandlers(scene);
        return scene;
    }

    public void prepareGameForStart(GameType gameTypeOption) {
        displayGameLayoutComponent(new Board(DEFAULT_BOARD_DIMENSION));
        controller.startGame(gameTypeOption, DEFAULT_BOARD_DIMENSION);
    }

    public void displayResult(Mark winner) {
        announceResult(winner);
    }

    public void playGame() {
        controller.playGame();
    }

    public void createNewGame() {
        controller.createNewGame();
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

    private void registerEventHandlers(Scene scene) {
        eventRegister.registerAllClickableElementsWithHandler(scene, this);
    }
}
