package jttt.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class NewGame {

    private PlayerFactory playerFactory;
    private Board board;
    private int gameType;
    private HashMap<Counter, Player> players;

    public NewGame() {
        this.playerFactory = new PlayerFactory();
        this.board = null;
        this.players = new HashMap<>();
        this.gameType = 0;
    }

    public int getBoardSize() {
        return board.boardSize();
    }

    public void setBoardDimension(int dimension) {
        board = new Board(dimension);
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;

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
        if (board.findPositions(Counter.X).size() > board.findPositions(Counter.O).size()){
            return players.get(Counter.O);
        }
        return players.get(Counter.X);
    }

    public Board getBoard() {
        return board;
    }

    public void playMove(int newPosition, Player player) {
        board = player.playTurn(board, newPosition);
    }

    public void playMove(Player playerA) {
        board = playerA.playTurn(board);
    }

    public void play(int dimension, int gameType) {
        setBoardDimension(dimension);
        setGameType(gameType);
    }
}
