package jttt;

import jttt.Core.Board;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.CommandLineUI;

import java.io.BufferedInputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI(new Game(new Board(0), new PlayerFactory()), new BufferedInputStream(System.in), new PrintStream(System.out));
        ui.start();
    }
}
