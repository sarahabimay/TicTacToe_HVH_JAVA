package jttt.Core;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private PlayerFactory playerFactory;
    private Board board;
    private int gameType;
    private HashMap<Mark, Player> players;

    public Game(Board board, int gameType, PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
        this.board = board;
        this.players = new HashMap<>();
        this.gameType = gameType;
        createPlayers();
    }

    public int getBoardSize() {
        return board == null ? 0 : board.boardSize();
    }

    public Player getPlayer(Mark mark) {
        return players.get(mark);
    }

    public void selectPlayers(int newGameType) {
        ArrayList<Player> bothPlayers = playerFactory.generatePlayersFor(newGameType);
        this.players.put(Mark.X, bothPlayers.get(0));
        this.players.put(Mark.O, bothPlayers.get(1));
    }

    public void createPlayers() {
        selectPlayers(this.gameType);
    }

    public Player getNextPlayer() {
        if (board.isEmpty()) {
            return this.players.get(Mark.X);
        }
        return players.get(board.findNextCounter());
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public Mark findWinner() {
        return board.findWinner();
    }

    public void playCurrentPlayerMove() {
        Player currentPlayer = getNextPlayer();
        board.playCounterInPosition(
                currentPlayer.getNextPosition(board),
                currentPlayer.getMark());
    }
}
