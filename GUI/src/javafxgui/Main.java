package javafxgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafxgui.event.EventRegister;
import javafxgui.view.BoardDisplay;
import javafxgui.view.GUIView;

public class Main extends Application {
    private final int GUI_WINDOW_HEIGHT = 700;
    private final int GUI_WINDOW_WIDTH = 675;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        Scene scene = new Scene(new StackPane(), GUI_WINDOW_HEIGHT, GUI_WINDOW_WIDTH);
        scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        TTTController controller = new TTTController(
                new GUIView(scene, new BoardDisplay(), new EventRegister()));
        controller.presentGameOptions();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
