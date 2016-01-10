package javafxgui.javafxcomponents;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafxgui.event.NewGameEventHandler;
import javafxgui.event.NewPlayerMoveEventHandler;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;

public class JavaFxGameLayoutComponent extends BorderPane {
    private final String GAME_TITLE = "TIC TAC TOE GAME!";
    private final String RESULTS_LABEL = "RESULTS HERE";
    private final String RESULTS_TARGET_ID = "resultTarget";
    private final String BORDER_PANE_ID = "borderPane";
    private final String TITLE_BAR_ID = "titleBar";
    private final String GAME_TITLE_TEXT_ID = "gameTitle";
    private final String FOOTER_ID = "footer";
    private final String PLAY_AGAIN_LABEL = "Play Again?";
    private final String PLAY_AGAIN_ID = "playAgain";
    private final GUIView guiView;

    public JavaFxGameLayoutComponent(Board board, GUIView guiView) {
        this.guiView = guiView;
        setId(BORDER_PANE_ID);
        setTop(titleHeader());
        setCenter(gameBoardForGUI(board));
        setBottom(resultFooter());
    }

    public HBox titleHeader() {
        HBox titleBar = createHBox(TITLE_BAR_ID);
        titleBar.getChildren().add(createTextElement(GAME_TITLE));
        return titleBar;
    }

    private JavaFxBoardComponent gameBoardForGUI(Board board) {
        return new JavaFxBoardComponent(board, new NewPlayerMoveEventHandler(guiView));
    }

    public VBox resultFooter() {
        VBox resultFooter = createVBox(FOOTER_ID);
        resultFooter.getChildren().add(createGameResultsTarget());
        resultFooter.getChildren().add(createPlayAgainButtonTarget());
        return resultFooter;
    }

    private Button createPlayAgainButtonTarget() {
        Button playAgain = new Button(PLAY_AGAIN_LABEL);
        playAgain.setId(PLAY_AGAIN_ID);
        playAgain.setVisible(false);
        registerButtonWithEventHandler(playAgain);
        return playAgain;
    }

    private void registerButtonWithEventHandler(Button playAgain) {
        JavaFXButton replayButton = new JavaFXButton(playAgain);
        replayButton.setOnAction(new NewGameEventHandler(guiView));
    }

    private Text createGameResultsTarget() {
        Text title = new Text(RESULTS_LABEL);
        title.setId(RESULTS_TARGET_ID);
        return title;
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
