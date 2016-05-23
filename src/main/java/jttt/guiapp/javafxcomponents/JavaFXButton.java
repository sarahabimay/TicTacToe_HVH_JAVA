package jttt.guiapp.javafxcomponents;

import javafx.scene.control.Button;
import jttt.guiapp.event.ClickEventHandler;

public class JavaFXButton implements ClickableElement {
    private final Button realJavaFXButton;

    public JavaFXButton(Button button) {
        this.realJavaFXButton = button;
    }

    public void setOnAction(ClickEventHandler eventHandler) {
        realJavaFXButton.setOnAction(event -> eventHandler.action(realJavaFXButton.getId()));
    }
}
