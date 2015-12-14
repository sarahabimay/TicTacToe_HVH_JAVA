package javafxgui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class EventRegister {
    public void registerBoardButtonWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
        clickableElement.setOnAction(eventHandler);
    }

    public void registerAllBoardButtonsWithHandler(GridPane board, Controller controller) {
        for (Node button : board.getChildren()){
            JavaFXButton javaFXButton = new JavaFXButton((Button)button);
           registerBoardButtonWithHandler(javaFXButton, new NewPlayerMoveEventHandler(controller));
        }

    }

    public void registerAllClickableElementsWithHandler(Scene scene, Controller controller) {
        registerBoard(scene, controller);
//        registerReplayButton(scene, controller);
    }

    private void registerBoard(Scene scene, Controller controller) {
        GridPane gameBoard = (GridPane) scene.lookup("#gameBoard");
        registerAllBoardButtonsWithHandler(gameBoard, controller);
    }
}
