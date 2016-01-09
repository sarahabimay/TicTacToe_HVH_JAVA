package jttt.Core;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GameTypeTest {
    @Test
    public void getAllOptionsForDisplay() {
        HashMap<GameType, String> optionsForDisplay = GameType.getOptionsForDisplay();
        assertEquals(3, optionsForDisplay.size());
    }
}
