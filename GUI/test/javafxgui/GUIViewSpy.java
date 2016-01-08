package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafxgui.event.EventRegister;
import javafxgui.view.BoardDisplay;
import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Players.GUIHumanPlayer;
import jttt.Core.Players.Player;

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

    public GUIViewSpy(Scene scene, BoardDisplay boardDisplay, EventRegister eventRegister) {
        super(scene, boardDisplay, null);
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
