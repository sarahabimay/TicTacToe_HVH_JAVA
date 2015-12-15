package javafxgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class Main extends Application {
    private final int HVH_GAMETYPE = 1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        Scene scene = new Scene(new StackPane(), 700, 675);
        scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        Controller controller = new TTTController(
                new GUIDisplay(scene, new BoardDisplay()),
                new EventRegister(),
                new Game(new Board(3), HVH_GAMETYPE, new PlayerFactory()));
        primaryStage.setScene(controller.displayGUI());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
