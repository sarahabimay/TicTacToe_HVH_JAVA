package javafxgui;

import javafx.application.Application;
import javafx.stage.Stage;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TicTacToe");
        Board defaultBoard = new Board(3);
        int HVH_GAMETYPE = 1;
        Controller controller = new TTTController(
                new GUIDisplay(new BoardDisplay(defaultBoard)),
                new EventRegister(),
                new Game(defaultBoard, HVH_GAMETYPE, new PlayerFactory()));
        primaryStage.setScene(controller.displayGUI());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
