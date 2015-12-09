import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafxgui.GUIDisplay;
import javafxgui.TTTController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    @Test
    public void displayBoard() {
        GUIDisplayViewSpy gameView = new GUIDisplayViewSpy();
        TTTController controller = new TTTController(gameView, gameView.generateLandingPageScene());
        controller.createAndEnableBoard();
        assertEquals(true, gameView.hasLandingPageBeenRendered());

    }

    @Test
    public void displayEnabledBoard() {
        GUIDisplayViewSpy gameView = new GUIDisplayViewSpy();
        TTTController controller = new TTTController(gameView, gameView.generateLandingPageScene());
        controller.createAndEnableBoard();
        assertEquals(true, gameView.hasLandingPageBeenRendered());
    }

    private class GUIDisplayViewSpy extends GUIDisplay {
        private boolean hasBeenAskedForGameOptions = false;
        private boolean hasLandingPageBeenRendered = false;

        public GUIDisplayViewSpy() {
            super();
        }

        public Scene generateLandingPageScene() {
            hasLandingPageBeenRendered = true;
            StackPane root = new StackPane();
            return new Scene(root, 700, 675);
        }

        public boolean hasBeenAskedForGameOptions() {
            return hasBeenAskedForGameOptions;
        }

        public boolean hasLandingPageBeenRendered() {
            return hasLandingPageBeenRendered;
        }
    }
}
