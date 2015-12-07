package javafxgui;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("javafxgui.fxml"));
        primaryStage.setTitle("TicTacToe");
        GUIDisplay guiDisplay = new GUIDisplay();
        primaryStage.setScene(guiDisplay.generateLandingPageScene());
        primaryStage.show();
    }

    private VBox rightBorder() {
        VBox vbox = new VBox();
        vbox.setId("rightBorder");
        return vbox;
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
