package javafxgui;

import javafx.application.Application;
import javafx.stage.Stage;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("javafxgui.fxml"));
        primaryStage.setTitle("TicTacToe");
        Controller controller = new TTTController(new GUIDisplay(), new Game(new Board(3), 1, new PlayerFactory()));
        primaryStage.setScene(controller.displayGUI());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
