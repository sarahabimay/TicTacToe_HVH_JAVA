package javafxgui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Players.GUIHumanPlayer;
import jttt.Core.Players.Player;

class GUIViewSpy extends GUIView {
    private boolean hasLandingPageBeenRendered = false;
    private boolean hasBoardBeenReDisplayed = false;
    private boolean hasBoardBeenDisabled = false;
    private boolean hasResultBeenAnnounced = false;
    private boolean hasReplayButtonBeenDisplayed = false;
    private Scene scene;
    private StackPane root;
    private boolean hasButtonBeenClicked = false;
    private boolean hasReplayButtonBeenSelected = false;
    private boolean hasNextGUIPlayerBeenFound = false;

    public GUIViewSpy(Scene scene, BoardDisplay boardDisplay, EventRegister eventRegister) {
        super(scene, boardDisplay, null);
        this.root = new StackPane();
        this.scene = new Scene(root, 700, 675);
    }

    public Player getCurrentPlayer() {
        hasNextGUIPlayerBeenFound = true;
        return new GUIHumanPlayer(Mark.X);
    }

    public Scene displayGUI(Board board) {
        hasLandingPageBeenRendered = true;
        root.getChildren().add(generateBorderLayout(board));
        return scene;
    }

    public void displayResult(Mark winner) {
        hasResultBeenAnnounced = true;
    }

    public GridPane displayBoard(Board board) {
        hasBoardBeenReDisplayed = true;
        return null;
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

    public boolean hasBoardBeenReDisplayed() {
        return hasBoardBeenReDisplayed;
    }

    public boolean hasLandingPageBeenRendered() {
        return hasLandingPageBeenRendered;
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
}
