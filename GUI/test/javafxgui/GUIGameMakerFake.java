package javafxgui;

import jttt.UI.UserInterface;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.game.Game;
import jttt.core.players.Player;
import jttt.core.players.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class GUIGameMakerFake extends GUIGameMaker {
    private final PlayerFactory playerFactory;
    private boolean hasInitializedGame = false;
    private List<Mark> dummyBoardMoves;

    public GUIGameMakerFake(PlayerFactory playerFactory) {
        super(null);
        this.playerFactory = playerFactory;
        this.dummyBoardMoves = new ArrayList<>();
    }

    @Override
    public Game initializeGame(int boardDimension, int gameTypeOption, UserInterface ui) {
        hasInitializedGame = true;
        Board board = new Board((int) Math.sqrt(dummyBoardMoves.size()), dummyBoardMoves);
        List<Player> players = playerFactory.findPlayersFor(gameTypeOption);
        return new Game(board, players.get(0), players.get(1), new GUIDisplayer());
    }

    public void setDummyBoard(List<Mark> board) {
        this.dummyBoardMoves = board;
    }

    public boolean hasInitializedGame() {
        return hasInitializedGame;
    }
}
