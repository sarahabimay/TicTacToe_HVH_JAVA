package javafxgui.javafxcomponents;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxgui.event.NewGameEventHandler;
import javafxgui.event.NewPlayerMoveEventHandler;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public class JavaFxGameLayoutComponent extends BorderPane {
    private final String GAME_TITLE = "TIC TAC TOE GAME!";
    private final String RESULTS_LABEL = "RESULTS HERE";
    public static final String RESULTS_TARGET_ID = "resultTarget";
    public static final String BORDER_PANE_ID = "borderPane";
    public static final String TITLE_BAR_ID = "titleBar";
    public static final String GAME_TITLE_TEXT_ID = "gameTitle";
    public static final String FOOTER_ID = "footer";
    public static final String PLAY_AGAIN_LABEL = "Play Again?";
    public static final String PLAY_AGAIN_ID = "playAgain";
    public static final String WINNER_ANNOUNCEMENT = "PLAYER %s HAS WON!";
    public static final String DRAW_ANNOUNCEMENT = "THE GAME IS A DRAW!";
    private final GUIView guiView;
    private JavaFxBoardComponent gameBoard;

    public JavaFxGameLayoutComponent(Board board, GUIView guiView) {
        this.guiView = guiView;
        setId(BORDER_PANE_ID);
        addAllLayoutComponents(board);
    }

    public void displayPlayAgainButton() {
        VBox vbox = (VBox) getBottom();
        vbox.getChildren().get(1).setVisible(true);
    }

    public void disableGameBoard() {
        gameBoard.disableBoard();
    }

    public void displayResult(Mark winner) {
        VBox vbox = (VBox) getBottom();
        Text text = (Text) vbox.getChildren().get(0);
        text.setText(createResultAnnouncement(winner));
    }

    private void addAllLayoutComponents(Board board) {
        setTop(createTitleHeader());
        setCenter(createGameBoardForGUI(board));
        setBottom(createResultFooter());
    }

    private HBox createTitleHeader() {
        HBox titleBar = createHBox(TITLE_BAR_ID);
        titleBar.getChildren().add(createTextElement(GAME_TITLE));
        return titleBar;
    }

    private GridPane createGameBoardForGUI(Board board) {
        gameBoard = new JavaFxBoardComponent(board, new NewPlayerMoveEventHandler(guiView));
        return gameBoard;
    }

    private VBox createResultFooter() {
        VBox resultFooter = createVBox(FOOTER_ID);
        resultFooter.getChildren().add(createGameResultsTarget());
        resultFooter.getChildren().add(createPlayAgainButtonTarget());
        return resultFooter;
    }

    private Text createGameResultsTarget() {
        Text title = new Text(RESULTS_LABEL);
        title.setId(RESULTS_TARGET_ID);
        return title;
    }

    private Button createPlayAgainButtonTarget() {
        Button playAgain = new Button(PLAY_AGAIN_LABEL);
        playAgain.setId(PLAY_AGAIN_ID);
        playAgain.setVisible(false);
        registerButtonWithEventHandler(playAgain);
        return playAgain;
    }

    private String createResultAnnouncement(Mark winner) {
        if (winner.isEmpty()) {
            return DRAW_ANNOUNCEMENT;
        }
        return String.format(WINNER_ANNOUNCEMENT, winner.toString());
    }

    private void registerButtonWithEventHandler(Button playAgain) {
        JavaFXButton replayButton = new JavaFXButton(playAgain);
        replayButton.setOnAction(new NewGameEventHandler(guiView));
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

    private Text createTextElement(String label) {
        Text title = new Text(label);
        title.setId(GAME_TITLE_TEXT_ID);
        return title;
    }
}
