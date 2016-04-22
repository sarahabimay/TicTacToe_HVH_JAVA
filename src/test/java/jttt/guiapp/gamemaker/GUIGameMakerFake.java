package jttt.guiapp.gamemaker;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.boarddisplayer.BoardDisplayer;
import jttt.core.game.Game;
import jttt.core.players.Player;
import jttt.core.players.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class GUIGameMakerFake extends GUIGameMaker {
    private boolean hasInitializedGame = false;
    private List<Mark> boardMoves = new ArrayList<>();

    @Override
    public Game initializeGame(int boardDimension, int gameTypeOption, PlayerFactory playerFactory, BoardDisplayer boardDisplayer) {
        hasInitializedGame = true;
        Board board = new Board((int) Math.sqrt(boardMoves.size()), boardMoves);
        List<Player> players = playerFactory.findPlayersFor(gameTypeOption);
        return new Game(board, players.get(0), players.get(1), boardDisplayer);
    }

    public void setDummyBoard(List<Mark> board) {
        this.boardMoves = board;
    }

    public boolean hasInitializedGame() {
        return hasInitializedGame;
    }
}
