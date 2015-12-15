package javafxgui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class EventRegister {
    public void registerABoardButtonWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
        clickableElement.setOnAction(eventHandler);
    }

    public void registerAllBoardButtonsWithHandler(GridPane board, Controller controller) {
        for (Node button : board.getChildren()) {
            JavaFXButton javaFXButton = new JavaFXButton((Button) button);
            registerABoardButtonWithHandler(javaFXButton, new NewPlayerMoveEventHandler(controller));
        }
    }

    public void registerAllClickableElementsWithHandler(Scene scene, Controller controller) {
        registerBoard(extractGameBoard(scene), controller);
//        registerReplayButton(extractReplayButton(scene), controller);
    }

    private void registerBoard(GridPane gameBoard, Controller controller) {
        registerAllBoardButtonsWithHandler((GridPane) gameBoard.lookup("#gameBoard"), controller);
    }

    private GridPane extractGameBoard(Scene scene) {
        return (GridPane) scene.lookup("#gameBoard");
    }
}
