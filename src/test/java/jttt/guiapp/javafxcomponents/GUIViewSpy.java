package jttt.guiapp.javafxcomponents;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import jttt.guiapp.app.GUIApp;
import jttt.core.board.Board;
import jttt.core.board.Mark;

public class GUIViewSpy extends GUIView {
    private Scene scene;
    private StackPane root;
    private boolean hasGameLayoutBeenRendered = false;
    private boolean hasBoardBeenDisabled = false;
    private boolean hasResultBeenAnnounced = false;
    private boolean hasReplayButtonBeenDisplayed = false;
    private boolean hasButtonBeenClicked = false;
    private boolean hasReplayButtonBeenSelected = false;
    private boolean hasGameOptionsBeenPresented = false;
    private boolean hasHumanPlayerMadeAMove = false;

    public GUIViewSpy(Scene scene) {
        super(scene);
        this.root = new StackPane();
        this.scene = new Scene(root, 700, 675);
    }

    @Override
    public void displayGameOptions() {
        hasGameOptionsBeenPresented = true;
    }

    @Override
    public void disableBoard(Board board) {
        hasBoardBeenDisabled = true;
    }

    @Override
    public void playNewMove(String displayPositionId) {
        hasHumanPlayerMadeAMove = true;
    }

    @Override
    public void presentGameOptions() {
        hasReplayButtonBeenSelected = true;
    }

    public void setGuiApp(GUIApp guiApp) {

    }

    public Scene displayGameLayoutComponent(Board board) {
        hasGameLayoutBeenRendered = true;
        return scene;
    }

    public void displayResult(Mark winner) {
        hasResultBeenAnnounced = true;
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

    public boolean hasGameOptionsBeenPresented() {
        return hasGameOptionsBeenPresented;
    }

    public boolean hasHumanPlayerMadeAMove(){
        return hasHumanPlayerMadeAMove;
    }
}
