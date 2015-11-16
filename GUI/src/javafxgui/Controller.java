package javafxgui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import jttt.Core.NewGame;

import java.util.HashMap;

public class Controller {
    public Text gametitle = new Text();
    public Button zero;
    public TextField dimension;
    public TextField gametype;
    private NewGame game;
    private HashMap<Integer, String> gameTypeOptionToString = new HashMap<>();

    public Controller() {
        this.game = null;
        mapGameTypeOptionToStringValue();
    }

    public NewGame getGame() {
        return game;
    }

    public void positionSelected(ActionEvent event) {
        zero.setText(event.getEventType().toString());
    }

    public void startGame(int dimension, int gameType) {
        gametitle.setText(createGameTypeTitle(gameType));
        this.game = new NewGame(dimension, gameType);
    }

    public void start(ActionEvent event) {
        startGame(Integer.valueOf(dimension.getText()), Integer.valueOf(gametype.getText()));
        System.out.println(this.game.getBoardSize());
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

