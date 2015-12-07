package javafxgui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("javafxgui.fxml"));
        primaryStage.setTitle("TicTacToe");
        GUIDisplay guiDisplay = new GUIDisplay();
        primaryStage.setScene(guiDisplay.generateLandingPageScene());
        primaryStage.show();
    }


//        controller.setGameOptions(vbox);
//        button.setOnAction(event -> controller.start(event));
//        button.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                actiontarget.setFill(Color.FIREBRICK);
//                actiontarget.setText("Sign in button pressed");
//            }
//        });
    public static void main(String[] args) {
        launch(args);
    }
}
