package javafxgui;

import jttt.core.Displayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GUIDisplayerTest {
    @Test
    public void displayerIsAKnownKindOfDisplayer() {
        Displayer displayer = new GUIDisplayer();
        assertEquals(true, displayer instanceof Displayer);
    }
}
