package javafxgui.gamemaker;

import jttt.core.board.Board;
import jttt.core.boarddisplayer.BoardDisplayer;
import jttt.core.game.Game;
import jttt.core.game.GameMaker;
import jttt.core.players.Player;
import jttt.core.players.PlayerFactory;

import java.util.List;

public class GUIGameMaker implements GameMaker {

    @Override
    public Game initializeGame(int boardDimension, int gameTypeOption, PlayerFactory playerFactory, BoardDisplayer boardDisplayer) {
        List<Player> players = playerFactory.findPlayersFor(gameTypeOption);
        return new Game(new Board(boardDimension), players.get(0), players.get(1), boardDisplayer);
    }
}
