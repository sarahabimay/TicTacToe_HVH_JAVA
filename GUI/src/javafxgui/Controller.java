package javafxgui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import jttt.Core.Board;
import jttt.Core.Game;
import jttt.Core.Mark;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    public Label gametitle = new Label();
    public Button zero;
    public TextField dimension;
    public TextField gametype;
    public GridPane gameboard;
    private Game game;
    private Map<Integer, String> gameTypeOptionToString = new HashMap<>();

    public Controller() {
        this.game = null;
        mapGameTypeOptionToStringValue();
    }

    public Game getGame() {
        return game;
    }

    public void positionSelected(ActionEvent event) {
        Mark mark = this.game.getNextPlayer().getMark();
        Button button = (Button) event.getSource();
        this.game.playMove(Integer.valueOf(button.getText()));
        button.setText(mark.name());
        if (!consolePlayersTurn()) {
            enableBoard(false);
            playAIMove();
        }
    }

    public void start(ActionEvent event) {
        changeTitle(getGameType());
        createNewGame(getDimension(), getGameType());
        if (isGameValid()) {
            initiateFirstPlayerMove();
        }
    }

    private void initiateFirstPlayerMove() {
        if (consolePlayersTurn()) {
            enableBoard(true);
        } else {
            playAIMove();
        }
    }

    private boolean consolePlayersTurn() {
        return this.game.getNextPlayer().getPlayerType() == Player.Type.HUMAN;
    }

    private void enableBoard(boolean enable) {
        System.out.println(enable);
        for (Node node : gameboard.getChildren()) {
//            System.out.println((Button) node);
            node.setDisable(!enable);
        }
    }

    private boolean isGameValid() {
        return this.game != null;
    }

    public int requestNextPosition() {
        Integer position = 0;
//        while (!validate(position, this::validPosition)) {
        String prompt = "Please enter the position number for your next move:\n";
//            writeStream.println(prompt);
//            position = readInput();
//        }
        return position;
    }

    public boolean requestPlayAgain() {
        Integer instruction = 0;
//        while (!validate(instruction, this::validInstruction)) {
//            writeStream.println("Do you want to play again? Quit(1) or Replay(2) :\n");
//            instruction = readInput();
//        }
//        return doPlayAgain(instruction);
        return false;
    }

    public void displayResult(Mark winner) {
        if (winner.isEmpty()) {
            System.out.println("No Winner");
//            announceDraw();
        } else {
            System.out.println("Winner is " + winner.toString());
//            announceWinner(winner);
        }
    }

    public String displayBoard() {
        System.out.println(this.game.getBoard().toString());
        return "";
    }

    public void options(ActionEvent actionEvent) {

    }

    public void createNewGame(int dimension, int gameType) {
        this.game = new Game(new Board(dimension), gameType, new PlayerFactory());
    }

    private void changeTitle(int gameType) {
        gametitle.setText(createGameTypeTitle(gameType));
    }

    private void playAIMove() {
        while (game.getNextPlayer().getPlayerType() == Player.Type.AI) {
            game.playAIMove();
        }
        displayBoard();
    }

    private int getGameType() {
        return Integer.valueOf(gametype.getText());
    }

    private int getDimension() {
        return Integer.valueOf(dimension.getText());
    }

    private String createGameTypeTitle(int gameType) {
        return String.format("TicTacToe: %s", gameTypeOptionToString.get(gameType));
    }

    private void mapGameTypeOptionToStringValue() {
        gameTypeOptionToString.put(1, "Human Vs Human");
        gameTypeOptionToString.put(2, "Human Vs Computer");
        gameTypeOptionToString.put(3, "Computer Vs Human");
    }

}

