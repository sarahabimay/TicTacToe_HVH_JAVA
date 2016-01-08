package javafxgui.event;

import javafxgui.ClickEventHandler;
import javafxgui.GUIView;
import jttt.Core.Players.GUIHumanPlayer;
import jttt.Core.Players.Player;

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
