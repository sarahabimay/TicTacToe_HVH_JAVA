package javafxgui.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafxgui.controller.Controller;
import javafxgui.javafxcomponents.JFXGameOptionsPage;
import javafxgui.javafxcomponents.JavaFxGameLayoutComponent;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.game.GameType;
import jttt.core.players.Player;

public class GUIView {

    private final int DEFAULT_BOARD_DIMENSION = 3;
    private Scene scene;
    private Controller controller;
    private JavaFxGameLayoutComponent gameLayoutWindow;

    public GUIView(Scene scene) {
        this.scene = scene;
        this.controller = null;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Player getCurrentPlayer() {
        return controller.getCurrentPlayer();
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

    public void playGame() {
        controller.playGame();
    }

    public void createNewGame() {
        controller.createNewGame();
    }

    public void disableBoard(Board board) {
        gameLayoutWindow.disableGameBoard();
    }

    public void makePlayAgainVisible() {
        gameLayoutWindow.displayPlayAgainButton();
    }
}
