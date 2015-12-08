import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
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



}
