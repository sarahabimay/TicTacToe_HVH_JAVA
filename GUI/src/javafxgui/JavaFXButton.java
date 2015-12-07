package javafxgui;

import javafx.scene.control.Button;
import javafx.scene.control.Control;

public class JavaFXButton {
    private final Button javafxElement;

    public JavaFXButton(Button button) {
        this.javafxElement = button;
    }

    public Control getJavafxElement() {
        return javafxElement;
    }

    public void setOnAction(ClickEventHandler eventHandler) {
        javafxElement.setOnAction(event -> eventHandler.action());
    }
}
