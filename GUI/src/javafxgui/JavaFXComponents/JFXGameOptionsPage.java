package javafxgui.javafxcomponents;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import jttt.core.game.GameType;

import java.util.Map;

public class JFXGameOptionsPage extends GridPane {
    private static int rowNumber = 0;

    public JFXGameOptionsPage(GUIView guiView) {
        buildGameOptionsComponent(guiView);
    }

    private void buildGameOptionsComponent(GUIView guiView) {
        setId("gameOptions");
        addTitlesToComponent();
        addGameOptionButtons(guiView);
//        setGridLinesVisible(true);
    }

    private void addTitlesToComponent() {
        Label title = new Label("Tic Tac Toe");
        title.setId("optionTitle1");
        add(title, 20, rowNumber++);
        title = new Label("Choose Your Game Type: ");
        title.setId("optionTitle2");
        add(title, 20, rowNumber++);
    }

    private void addGameOptionButtons(GUIView guiView) {
        Map<GameType, String> optionsForDisplay = GameType.getOptionsForDisplay();
        for (Map.Entry<GameType, String> option : optionsForDisplay.entrySet()) {
            javafxgui.javafxcomponents.JavaFXGameOptionButton optionButton = new javafxgui.javafxcomponents.JavaFXGameOptionButton(option);
            add(optionButton, 20, rowNumber++);
            addClickEventHandler(optionButton, guiView);
        }
    }

    private void addClickEventHandler(JavaFXGameOptionButton optionButton, GUIView guiView) {
        optionButton.setOnAction(event -> guiView.prepareGameForStart(optionButton.getGameType()));
    }
}
