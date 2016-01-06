package javafxgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.PlayerFactory;

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
                new GUIView(scene, new BoardDisplay(), new EventRegister()),
                new Game(new Board(3), GameType.GUI_HVH.getGameTypeOption(), new PlayerFactory()));
        controller.displayGUI();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
