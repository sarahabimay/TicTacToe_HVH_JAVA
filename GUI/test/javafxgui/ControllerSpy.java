package javafxgui;

import javafx.scene.Scene;

public class ControllerSpy implements Controller {
    private boolean hasReDisplayBoardBeenCalled = false;
    private boolean hasRestartGameBeenCalled = false;

    public Scene displayGUI() {
        return null;
    }

    public void displayBoard() {
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
