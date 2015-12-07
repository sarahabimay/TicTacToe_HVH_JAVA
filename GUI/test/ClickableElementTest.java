import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafxgui.ClickEventHandler;
import javafxgui.JavaFXButton;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClickableElementTest {
    @Before
    public void setUp() {
        new JFXPanel();
    }

    @Test
    public void registerJavaFxElemntWithActionHandler() {
        Button startButton = new Button("Start Game");
        JavaFXButton javaFXButton = new JavaFXButton(startButton);
        ControllerSpy controller = new ControllerSpy();
        javaFXButton.setOnAction(controller.getStartEventHandler());
        startButton.fire();
        assertEquals(true, controller.hasStartEventHandlerBeenCalled());
        assertEquals(true, controller.hasBoardBeenCreated());
    }

    private class ControllerSpy {

        private StartGameEventSpy startGameEventSpy;
        private boolean hasCreateAndEnableBeenCalled;

        public ClickEventHandler getStartEventHandler() {
            startGameEventSpy = new StartGameEventSpy(this);
            return startGameEventSpy;
        }

        public void createAndEnableBoard() {
            hasCreateAndEnableBeenCalled = true;
        }

        public boolean hasStartEventHandlerBeenCalled() {
            return startGameEventSpy.hasBeenClicked();
        }

        public boolean hasBoardBeenCreated() {
            return hasCreateAndEnableBeenCalled;
        }
    }

    private class StartGameEventSpy implements ClickEventHandler {
        private ControllerSpy controller;
        private boolean hasBeenClicked = false;

        public StartGameEventSpy(ControllerSpy controller) {
            this.controller = controller;
        }

        public void action() {
            hasBeenClicked = true;
            System.out.println("Start button pressed");
            controller.createAndEnableBoard();
        }

        public boolean hasBeenClicked() {
            return hasBeenClicked;
        }
    }
}
