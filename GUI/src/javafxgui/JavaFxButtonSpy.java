package javafxgui;

import javafx.scene.control.Button;

public class JavaFxButtonSpy extends JavaFXButton {
    private boolean hasButtonBeenAssociatedWithHandler;

    public JavaFxButtonSpy(Button button) {
        super(button);
    }

    public void setOnAction(ClickEventHandler eventHandler) {
        hasButtonBeenAssociatedWithHandler = true;
        super.setOnAction(eventHandler);
    }

    public boolean hasButtonBeenAssociatedWithHandler() {
        return hasButtonBeenAssociatedWithHandler;
    }
}
