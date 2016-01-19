package jttt.core.game;

import java.util.HashMap;

public enum GameType {
    HVH(1),
    HVC(2),
    CVH(3),
    GUI_HVH(4),
    GUI_CVH(5),
    GUI_HVC(6),
    INVALID(7);

    private final int numericGameType;

    private static HashMap<GameType, String> optionsForDisplay = new HashMap<>();
    static {
        optionsForDisplay.put(GameType.GUI_HVH, "Human Vs Human Game");
        optionsForDisplay.put(GameType.GUI_HVC, "Human Vs AI Game");
        optionsForDisplay.put(GameType.GUI_CVH, "AI Vs Human Game");
    }

    GameType(int option) {
        this.numericGameType = option;
    }

    public int getNumericGameType() {
        return numericGameType;
    }

    public static GameType lookupGameType(int gameType){
        GameType[] gameTypes = values();
        for (GameType type : gameTypes){
            if (type.getNumericGameType() == gameType){
               return type;
            }
        }
        return INVALID;
    }
    public static HashMap<GameType, String> getOptionsForDisplay() {
        return optionsForDisplay;
    }
}
