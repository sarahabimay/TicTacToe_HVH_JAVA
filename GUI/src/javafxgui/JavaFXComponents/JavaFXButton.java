package javafxgui.JavaFXComponents;

import javafx.scene.control.Button;
import javafxgui.ClickEventHandler;
import javafxgui.ClickableElement;

public class JavaFXButton implements ClickableElement {
    private final Button realJavaFXButton;

    public JavaFXButton(Button button) {
        this.realJavaFXButton = button;
    }

    public void setOnAction(ClickEventHandler eventHandler) {
        realJavaFXButton.setOnAction(event -> eventHandler.action(realJavaFXButton.getId()));
    }
}
