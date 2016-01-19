package javafxgui.event;

import javafxgui.view.GUIView;
import javafxgui.GUIHumanPlayer;
import jttt.core.players.Player;

public class NewPlayerMoveEventHandler implements ClickEventHandler {
    private GUIView guiView;

    public NewPlayerMoveEventHandler(GUIView guiView) {
        this.guiView = guiView;
    }

    public void action(String displayPositionId) {
        updateCurrentPlayerWithMove(displayPositionId);
        guiView.playGame();
    }

    private void updateCurrentPlayerWithMove(String displayPositionId) {
        Player currentPlayer = guiView.getCurrentPlayer();
        GUIHumanPlayer guiHumanPlayer = (GUIHumanPlayer)currentPlayer;
        guiHumanPlayer.setNextUserMove(displayPositionId);
    }
}
