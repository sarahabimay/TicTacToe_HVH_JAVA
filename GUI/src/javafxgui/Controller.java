package javafxgui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label helloworld;
    public void helloworldclick(ActionEvent event) {
        helloworld.setText("I'm IN!");
    }
}

