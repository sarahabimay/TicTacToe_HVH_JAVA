package jttt.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Game {

    private PlayerFactory playerFactory;
    private Board board;
    private int gameType;
    private HashMap<Counter, Player> players;

    public Game() {
        this.playerFactory = new PlayerFactory();
        this.board = null;
        this.players = new HashMap<>();
        this.gameType = 0;
    }

    public Game(int dimension, int gameType) {
        this.gameType = gameType;
        this.board = new Board(dimension);
        this.playerFactory = new PlayerFactory();
        this.players = new HashMap<>();
        createPlayers();
    }

    public int getBoardSize() {
        return board == null ? 0 : board.boardSize();
    }

    public void setBoardDimension(int dimension) {
        board = new Board(dimension);
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;

    }

    public Player getPlayer(Counter counter) {
        return players.get(counter);
    }

    public List<Player> getPlayers() {
        if (this.players == null ) {
            return null;
        }
        return this.players.entrySet().stream().map(Map.Entry::getValue).collect(toList());
    }

    public void selectPlayers(int newGameType) {
        ArrayList<Player> bothPlayers = playerFactory.generatePlayersFor(newGameType);
        this.players.put(Counter.X, bothPlayers.get(0));
        this.players.put(Counter.O, bothPlayers.get(1));
    }

    public void createPlayers() {
        selectPlayers(this.gameType);
    }

    public Player getNextPlayer() {
        if (board.isEmpty()){
            return this.players.get(Counter.X);
        }
        // get next player by looking at the board and working out who is next
        return players.get(board.findNextCounter());
    }

    public Board getBoard() {
        return board;
    }

    public void playMove(int newPosition) {
        Player currentPlayer = getNextPlayer();
        board = currentPlayer.playTurn(board, newPosition);
    }

    public void playMove() {
        Player currentPlayer = getNextPlayer();
        board = currentPlayer.playTurn(board);
    }

    public void play(int dimension, int gameType) {
        setBoardDimension(dimension);
        setGameType(gameType);
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public Counter findWinner() {
        return board.findWinner();
    }

}
