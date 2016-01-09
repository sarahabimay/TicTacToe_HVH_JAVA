package jttt.Core.Players;

import jttt.Core.GameType;

import static jttt.Core.Board.Mark.O;
import static jttt.Core.Board.Mark.X;

public class PlayerFactoryFake extends PlayerFactory {
    private String nextGUIHumanMove;

    public PlayerFactoryFake() {
        super();
        this.nextGUIHumanMove = null;
    }

    public void setNextGUIHumanMove(String displayPositionId) {
        this.nextGUIHumanMove = displayPositionId;
    }

    @Override
    void registerGameTypeWithPlayerTypes() {
        GUIHumanPlayer guiHumanPlayerX = new GUIHumanPlayer(X);
        GUIHumanPlayer guiHumanPlayerO = new GUIHumanPlayer(O);
        guiHumanPlayerX.setNextUserMove(nextGUIHumanMove);
        guiHumanPlayerO.setNextUserMove(nextGUIHumanMove);
        gameTypeOptionToPlayers.put(GameType.GUI_CVH, createPlayers(new ComputerPlayer(X), guiHumanPlayerO));
        gameTypeOptionToPlayers.put(GameType.GUI_HVC, createPlayers(guiHumanPlayerX, new ComputerPlayer(O)));
    }
}
