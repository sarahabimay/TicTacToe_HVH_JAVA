package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public interface Controller {
    void playMoveAtPosition(String id);

    Scene displayGUI();

    GridPane displayBoard();

    void displayResult();

    boolean foundWinOrDraw();
}
