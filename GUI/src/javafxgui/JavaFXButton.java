package javafxgui;

import javafx.scene.control.Button;

public class JavaFXButton {
    private final Button javafxElement;

    public JavaFXButton(Button button) {
        this.javafxElement = button;
    }

    public void setOnAction(ClickEventHandler eventHandler) {
        javafxElement.setOnAction(event -> eventHandler.action());
    }
}
