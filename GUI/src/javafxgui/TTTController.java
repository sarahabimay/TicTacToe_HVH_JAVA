package javafxgui;

import javafx.scene.Scene;

public class TTTController implements Controller{
    private  GUIDisplay gameView;

    public TTTController(GUIDisplay gameView, Scene scene) {
        this.gameView = gameView;
    }

    public TTTController(GUIDisplay guiDisplay) {
        this.gameView = guiDisplay;
    }

    @Override
    public Scene generateLandingPageScene() {
        return gameView.generateLandingPageScene();
    }

    public void createAndEnableBoard() {
//        gameView.generateLandingPageScene();
        gameView.displayBoard();
    }
}
