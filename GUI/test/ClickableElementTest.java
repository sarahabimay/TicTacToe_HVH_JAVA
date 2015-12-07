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
        StartGameEventSpy startGameEventHandler= new StartGameEventSpy();
        javaFXButton.setOnAction(startGameEventHandler);
        startButton.fire();
        assertEquals(true, startGameEventHandler.hasBeenClicked());
    }

    private class StartGameEventSpy implements ClickEventHandler {
        private boolean hasBeenClicked = false;

        public void action(){
            hasBeenClicked = true;
            System.out.println("Start button pressed");
        }

        public boolean hasBeenClicked() {
            return hasBeenClicked;
        }
    }
}
