import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafxgui.Controller;

public class ControllerSpy implements Controller {
    private boolean hasReDisplayBoardBeenCalled;
    private boolean hasSearchedForWinOrDraw;

    public Scene displayGUI() {
        return null;
    }

    public GridPane displayBoard() {
        return null;
    }

    public void displayResult() {

    }

    public void playMoveAtPosition(String id) {
        hasReDisplayBoardBeenCalled = true;
    }

    public boolean foundWinOrDraw() {
        return hasSearchedForWinOrDraw = true;
    }

    public boolean hasReDisplayBoardBeenCalled() {
        return hasReDisplayBoardBeenCalled;
    }

    public boolean hasSearchedForWinOrDraw() {
        return hasSearchedForWinOrDraw;
    }
}
