import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafxgui.Controller;

public class ControllerSpy implements Controller {
    private boolean hasReDisplayBoardBeenCalled = false;
    private boolean hasFoundWinOrDraw = false;
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
        return hasFoundWinOrDraw = true;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }

    public boolean hasSearchedForWinOrDraw() {
        return hasFoundWinOrDraw;
    }

    public boolean hasRestartGameBeenCalled() {
        return hasRestartGameBeenCalled;
    }
}
