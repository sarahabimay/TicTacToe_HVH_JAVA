package jttt.guiapp.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import jttt.guiapp.event.NewPlayerMoveEventHandlerSpy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFxButtonTest {

    @Before
    public void setUp() {
        new JFXPanel();
    }

    @Test
    public void registerJavaFxElementWithActionHandler() {
        Button boardButton = new Button("1");
        boardButton.setId("1");
        JavaFXButton javaFXButton = new JavaFXButton(boardButton);
        NewPlayerMoveEventHandlerSpy handler = new NewPlayerMoveEventHandlerSpy(null);
        javaFXButton.setOnAction(handler);
        boardButton.fire();
        assertEquals(true, handler.hasBeenClicked());
    }
}
