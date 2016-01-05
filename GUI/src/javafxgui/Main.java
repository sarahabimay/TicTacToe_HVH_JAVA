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
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        Scene scene = new Scene(new StackPane(), 700, 675);
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
