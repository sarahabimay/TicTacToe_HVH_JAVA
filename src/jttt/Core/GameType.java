package jttt.Core;

public enum GameType {
    HVH(1),
    HVC(2),
    CVH(3),
    GUI_HVH(4),
    CVGUI(5);

    private final int gameTypeOption;

    GameType(int option) {
        this.gameTypeOption = option;
    }

    public int getGameTypeOption() {
        return gameTypeOption;
    }
}
