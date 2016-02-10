package jttt.console;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {
        ConsoleApp ui = new ConsoleApp(
                new ConsoleGameMaker(),
                new BufferedInputStream(System.in),
                new OutputStreamWriter(System.out));
        ui.startGame();
    }
}
