package javafxgui;

import jttt.core.game.GameType;
import jttt.core.players.ComputerPlayer;

import static jttt.core.board.Mark.O;
import static jttt.core.board.Mark.X;

public class GUIPlayerFactoryFake extends GUIPlayerFactory {
    private String nextGUIHumanMove = "";

    public void setNextGUIHumanMove(String displayPositionId) {
        this.nextGUIHumanMove = displayPositionId;
    }

    @Override
    public void registerGameTypeWithPlayerTypes() {
        GUIHumanPlayer guiHumanPlayerX = new GUIHumanPlayer(X);
        GUIHumanPlayer guiHumanPlayerO = new GUIHumanPlayer(O);
        guiHumanPlayerX.setNextUserMove(nextGUIHumanMove);
        guiHumanPlayerO.setNextUserMove(nextGUIHumanMove);
        gameTypeOptionToPlayers.put(GameType.GUI_CVH, createPlayers(new ComputerPlayer(X), guiHumanPlayerO));
        gameTypeOptionToPlayers.put(GameType.GUI_HVC, createPlayers(guiHumanPlayerX, new ComputerPlayer(O)));
    }
}
