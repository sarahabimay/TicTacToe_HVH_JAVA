package jttt;

import jttt.Core.Board.Board;
import jttt.Core.Board.DisplayStyler;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.CommandLineUI;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {
        final int DEFAULT_DIMENSION = 0;
        CommandLineUI ui = new CommandLineUI(
                new Game(
                        new Board(DEFAULT_DIMENSION),
                        GameType.HVH.getNumericGameType(),
                        new PlayerFactory()),
                new DisplayStyler(),
                new BufferedInputStream(System.in),
                new OutputStreamWriter(System.out));
        ui.start();
    }
}
