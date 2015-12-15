package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class ControllerSpy implements Controller {
    private boolean hasReDisplayBoardBeenCalled = false;
    private boolean hasRestartGameBeenCalled = false;

    public Scene displayGUI() {
        return null;
    }

    public GridPane displayBoard() {
        return null;
    }

    public void displayResult() {
    }

    public void createNewGame() {
        hasRestartGameBeenCalled = true;
    }

    public void playMoveAtPosition(String id) {
        hasReDisplayBoardBeenCalled = true;
    }

    public boolean foundWinOrDraw() {
        return true;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }

    public boolean hasRestartGameBeenCalled() {
        return hasRestartGameBeenCalled;
    }
}
