package javafxgui.javafxcomponents;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafxgui.controller.GUIAppController;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.game.GameType;

public class GUIView {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private Scene scene;
    private GUIAppController controller; // IS THIS OKAY TO DO SINCE IT'S UNLIKELY ANY OTHER GUIAPP COULD BE USED ?
    private JavaFxGameLayoutComponent gameLayoutWindow;

    public GUIView(Scene scene) {
        this.scene = scene;
        this.controller = null;
    }

    public void setController(GUIAppController controller) {
        this.controller = controller;
    }

    public Node lookup(String id) {
        return scene.lookup(id);
    }

    public void displayGameOptions() {
        scene.setRoot(new JFXGameOptionsPage(this));
    }

    public void prepareGameForStart(GameType gameTypeOption) {
        displayGameLayoutComponent(new Board(DEFAULT_BOARD_DIMENSION));
        controller.startGame(gameTypeOption, DEFAULT_BOARD_DIMENSION);
    }

    public Scene displayGameLayoutComponent(Board board) {
        gameLayoutWindow = new JavaFxGameLayoutComponent(board, this);
        scene.setRoot(gameLayoutWindow);
        return scene;
    }

    public void displayResult(Mark winner) {
        gameLayoutWindow.displayResult(winner);
    }

    public void presentGameOptions() {
        controller.displayGameOptions();
    }

    public void disableBoard(Board board) {
        gameLayoutWindow.disableGameBoard();
    }

    public void makePlayAgainVisible() {
        gameLayoutWindow.displayPlayAgainButton();
    }

    public void playNewMove(String displayPositionId) {
        controller.registerPlayerMove(displayPositionId);
        controller.startGame();
    }
}
