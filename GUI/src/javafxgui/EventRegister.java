package javafxgui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class EventRegister {
    public void registerClickableElementWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
        clickableElement.setOnAction(eventHandler);
    }

    public void registerAllBoardButtonsWithHandler(GridPane board, Controller controller) {
        for (Node button : board.getChildren()) {
            registerClickableElementWithHandler(new JavaFXButton((Button) button), new NewPlayerMoveEventHandler(controller));
        }
    }

    public void registerReplayButtonWithHandler(Button replayButton, Controller controller) {
        registerClickableElementWithHandler(new JavaFXButton(replayButton), new NewGameEventHandler(controller));
    }

    public void registerAllClickableElementsWithHandler(Scene scene, Controller controller) {
        registerBoard(extractGameBoard(scene), controller);
        registerReplayButtonWithHandler(extractReplayButton(scene), controller);
    }

    private void registerBoard(GridPane gameBoard, Controller controller) {
        registerAllBoardButtonsWithHandler((GridPane) gameBoard.lookup("#gameBoard"), controller);
    }

    private Button extractReplayButton(Scene scene) {
        return (Button) scene.lookup("#playAgain");
    }

    private GridPane extractGameBoard(Scene scene) {
        return (GridPane) scene.lookup("#gameBoard");
    }
}
