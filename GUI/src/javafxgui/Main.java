package javafxgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafxgui.controller.TTTController;
import javafxgui.view.GUIView;

public class Main extends Application {
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 675;
    private final String TTT_TITLE = "TicTacToe";
    private final String CSS_SCRIPT = "javafxgui.css";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TTT_TITLE);
        Scene scene = new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH);
        scene.getStylesheets().add(Main.class.getResource(CSS_SCRIPT).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        TTTController controller = new TTTController(new GUIView(scene), new GUIGameMaker(new GUIPlayerFactory()));
        controller.presentGameOptions();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
