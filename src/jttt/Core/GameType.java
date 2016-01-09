package jttt.Core;

public enum GameType {
    HVH(1),
    HVC(2),
    CVH(3),
    GUI_HVH(4),
    GUI_CVH(5),
    GUI_HVC(6);

    private final int gameTypeOption;

    GameType(int option) {
        this.gameTypeOption = option;
    }

    public int getGameTypeOption() {
        return gameTypeOption;
    }
}
