package jttt.core.game;

import jttt.core.Displayer;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.players.Player;

import java.util.HashMap;

public class Game {

    private Displayer displayer;
    private Board board;
    private HashMap<Mark, Player> players;

    public Game(Board board, Player player1, Player player2, Displayer displayer) {
        this.board = board;
        this.displayer = displayer;
        this.players = new HashMap<>();
        this.players.put(player1.getMark(), player1);
        this.players.put(player2.getMark(), player2);
    }

    public int getBoardSize() {
        return board.boardSize();
    }

    public Player getPlayer(Mark mark) {
        return players.get(mark);
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

    public void playAllAvailableMoves() {
        displayer.updateBoardDisplay(board);
        Player currentPlayer = getNextPlayer();
        while (!isGameOver() && currentPlayer.isReady()) {
            board.playCounterInPosition(
                    currentPlayer.getNextPosition(board),
                    currentPlayer.getMark());
            displayer.updateBoardDisplay(board);
            currentPlayer = getNextPlayer();
        }
    }
}
