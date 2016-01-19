package jttt.UI;

import jttt.core.board.Board;
import jttt.core.game.Game;
import jttt.core.game.GameMaker;
import jttt.core.players.Player;

import java.util.List;

public class ConsoleGameMaker implements GameMaker {

    public Game initializeGame(int boardDimension, int gameTypeOption, UserInterface userInterface) {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(userInterface);
        List<Player> players = playerFactory.findPlayersFor(gameTypeOption);
        return new Game(
                new Board(boardDimension),
                players.get(0),
                players.get(1),
                new UIDisplayer(userInterface, new DisplayStyler()));
    }
}
