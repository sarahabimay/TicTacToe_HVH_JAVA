package jttt.guiapp.players;

import org.junit.Test;

import jttt.core.players.ComputerPlayer;
import jttt.core.players.Player;
import java.util.List;

import static jttt.core.game.GameType.*;
import static org.junit.Assert.assertEquals;

public class GUIPlayerFactoryTest {
    @Test
    public void threePlayerCombinationsPossible() {
        GUIPlayerFactory guiPlayerFactory = new GUIPlayerFactory();
        assertEquals(2, guiPlayerFactory.findPlayersFor(GUI_HVH.getNumericGameType()).size());
        assertEquals(2, guiPlayerFactory.findPlayersFor(GUI_CVH.getNumericGameType()).size());
        assertEquals(2, guiPlayerFactory.findPlayersFor(GUI_HVC.getNumericGameType()).size());
    }
    @Test
    public void requestGUIHumanVGUIHumanPlayers() {
        GUIPlayerFactory playerFactory = new GUIPlayerFactory();
        List<Player> players = playerFactory.findPlayersFor(GUI_HVH.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(GUIHumanPlayer.class, players.get(0).getClass());
        assertEquals(GUIHumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestComputerVGUIPlayers() {
        GUIPlayerFactory playerFactory = new GUIPlayerFactory();
        List<Player> players = playerFactory.findPlayersFor(GUI_CVH.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(ComputerPlayer.class, players.get(0).getClass());
        assertEquals(GUIHumanPlayer.class, players.get(1).getClass());
    }

    @Test
    public void requestGUIvCPlayers() {
        GUIPlayerFactory playerFactory = new GUIPlayerFactory();
        List<Player> players = playerFactory.findPlayersFor(GUI_HVC.getNumericGameType());
        assertEquals(2, players.size());
        assertEquals(GUIHumanPlayer.class, players.get(0).getClass());
        assertEquals(ComputerPlayer.class, players.get(1).getClass());
    }
}
