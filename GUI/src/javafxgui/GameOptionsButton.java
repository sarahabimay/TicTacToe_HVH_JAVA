package javafxgui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameOptionsButton implements ClickableElement{

    private VBox gameOptions;
    private Button button;

    public GameOptionsButton(Button button, VBox gameOptions) {
        this.button = button;
        this.gameOptions = gameOptions;
    }

    public void setOnAction(ClickEventHandler clickEventHandler) {
        System.out.println(this.button.toString());
        ObservableList<Node> children = gameOptions.getChildren();
        for (int i = 0; i < children.size() ; i++) {
            System.out.println(children.get(i).toString());
        }
        button.setOnAction(event -> clickEventHandler.action());
    }
}
