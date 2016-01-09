package javafxgui.javafxcomponents;


import javafx.scene.control.Button;
import jttt.Core.GameType;

import java.util.Map;

public class JavaFXGameOptionButton extends Button {
    private final GameType gameType;

    public JavaFXGameOptionButton(GameType gameType) {
        this.gameType = gameType;
        setText(gameType.toString());
        setId(gameType.toString());
    }

    public JavaFXGameOptionButton(Map.Entry<GameType, String> option) {
        this.gameType = option.getKey();
        setText(option.getValue());
        setId(gameType.toString());
//        setPadding(new Insets(10,10,10,10));
    }

    public GameType getGameType() {
        return gameType;
    }
}
