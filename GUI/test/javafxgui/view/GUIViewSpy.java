package javafxgui.view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import javafxgui.GUIHumanPlayer;
import jttt.core.players.Player;

public class GUIViewSpy extends GUIView {
    private Scene scene;
    private StackPane root;
    private boolean hasGameLayoutBeenRendered = false;
    private boolean hasBoardBeenDisabled = false;
    private boolean hasResultBeenAnnounced = false;
    private boolean hasReplayButtonBeenDisplayed = false;
    private boolean hasButtonBeenClicked = false;
    private boolean hasReplayButtonBeenSelected = false;
    private boolean hasNextGUIPlayerBeenFound = false;
    private boolean hasGameOptionsBeenPresented = false;

    public GUIViewSpy(Scene scene) {
        super(scene);
        this.root = new StackPane();
        this.scene = new Scene(root, 700, 675);
    }

    public Player getCurrentPlayer() {
        hasNextGUIPlayerBeenFound = true;
        return new GUIHumanPlayer(Mark.X);
    }

    @Override
    public void displayGameOptions() {
        hasGameOptionsBeenPresented = true;
    }

    public Scene displayGameLayoutComponent(Board board) {
        hasGameLayoutBeenRendered = true;
        return scene;
    }

    public void displayResult(Mark winner) {
        hasResultBeenAnnounced = true;
    }

    @Override
    public void disableBoard(Board board) {
        hasBoardBeenDisabled = true;
    }

    @Override
    public void playGame() {
        hasButtonBeenClicked = true;
    }

    @Override
    public void createNewGame() {
        hasReplayButtonBeenSelected = true;
    }

    public void makePlayAgainVisible() {
        hasReplayButtonBeenDisplayed = true;
    }

    public boolean hasGameLayoutBeenRendered() {
        return hasGameLayoutBeenRendered;
    }

    public boolean hasBoardBeenDisabled() {
        return hasBoardBeenDisabled;
    }

    public boolean hasResultBeenAnnounced() {
        return hasResultBeenAnnounced;
    }

    public boolean hasReplayButtonBeenDisplayed() {
        return hasReplayButtonBeenDisplayed;
    }

    public boolean hasBoardButtonBeenClicked() {
        return hasButtonBeenClicked;
    }

    public boolean hasReplayGameBeenSelected() {
        return hasReplayButtonBeenSelected;
    }

    public boolean hasNextGUIPlayerBeenFound() {
        return hasNextGUIPlayerBeenFound;
    }

    public boolean hasGameOptionsBeenPresented() {
        return hasGameOptionsBeenPresented;
    }
}
