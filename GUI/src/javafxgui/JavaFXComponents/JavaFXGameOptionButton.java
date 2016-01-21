package javafxgui.javafxcomponents;


import javafx.scene.control.Button;
import jttt.core.game.GameType;

import java.util.Map;

public class JavaFXGameOptionButton extends Button {
    private final GameType gameType;

    public JavaFXGameOptionButton(Map.Entry<GameType, String> option) {
        this.gameType = option.getKey();
        setText(option.getValue());
        setId(gameType.toString());
    }

    public GameType getGameType() {
        return gameType;
    }
}
