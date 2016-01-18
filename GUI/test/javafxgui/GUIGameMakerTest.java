package javafxgui;

import jttt.core.game.Game;
import org.junit.Test;

import static jttt.core.board.Mark.O;
import static jttt.core.board.Mark.X;
import static jttt.core.game.GameType.GUI_HVH;
import static org.junit.Assert.assertEquals;

public class GUIGameMakerTest {
    private static final int DEFAULT_BOARD_DIMENSION = 3;

    @Test
    public void create3x3GUIHVHGame() {
        GUIGameMaker gameMaker = new GUIGameMaker(new GUIPlayerFactory());
        Game game = gameMaker.initializeGame(DEFAULT_BOARD_DIMENSION,
                GUI_HVH.getNumericGameType(), null);
        assertEquals(9, game.getBoardSize());
        assertEquals(GUIHumanPlayer.class, game.getPlayer(X).getClass());
        assertEquals(GUIHumanPlayer.class, game.getPlayer(O).getClass());
    }
}
