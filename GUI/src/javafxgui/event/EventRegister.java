package javafxgui.event;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafxgui.*;
import javafxgui.javafxcomponents.JavaFXButton;

public class EventRegister {
    public void registerAllClickableElementsWithHandler(Scene scene, GUIView guiView) {
        registerBoard(extractGameBoard(scene), guiView);
        registerReplayButtonWithHandler(extractReplayButton(scene), guiView);
    }

    public void registerAllBoardButtonsWithHandler(GridPane board, GUIView guiView) {
        for (Node button : board.getChildren()) {
            registerClickableElementWithHandler(new JavaFXButton((Button) button), new NewPlayerMoveEventHandler(guiView));
        }
    }

    private void registerBoard(GridPane gameBoard, GUIView guiView) {
        registerAllBoardButtonsWithHandler(gameBoard, guiView);
    }

    private void registerReplayButtonWithHandler(Button replayButton, GUIView guiView) {
        registerClickableElementWithHandler(new JavaFXButton(replayButton), new NewGameEventHandler(guiView));
    }

    private void registerClickableElementWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
        clickableElement.setOnAction(eventHandler);
    }

    private Button extractReplayButton(Scene scene) {
        return (Button) scene.lookup("#playAgain");
    }

    private GridPane extractGameBoard(Scene scene) {
        return (GridPane) scene.lookup("#gameBoard");
    }
}
