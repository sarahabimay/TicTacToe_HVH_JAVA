package jttt.guiapp.javafxcomponents;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFxGameOptionsPageTest {
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 600;

    @Before
    public void setUp() {
        new JFXPanel();
    }

    @Test
    public void pageContainsButtonForEachGameType() {
        Scene scene = new Scene(new StackPane(), WINDOW_HEIGHT, WINDOW_WIDTH);
        JFXGameOptionsPage gameOptionsPage = new JFXGameOptionsPage(
                new GUIViewSpy(scene));
        assertEquals("gameOptions", gameOptionsPage.getId());
    }
}
