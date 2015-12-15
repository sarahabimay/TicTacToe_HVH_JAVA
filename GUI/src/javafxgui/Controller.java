package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public interface Controller {
    Scene displayGUI();

    GridPane displayBoard();

    void displayResult();

    void playMoveAtPosition(String id);

    void createNewGame();

    boolean foundWinOrDraw();

    void displayPlayAgain();
}
