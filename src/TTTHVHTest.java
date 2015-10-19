import org.junit.Test;

import static org.junit.Assert.*;

public class TTTHVHTest {
    @Test
    public void playerPromptFor3x3Game(){
        Game game = new Game(new Board(3));
        assertEquals("[1][2][3][4][5][6][7][8][9]", game.startGame());
    }
}
