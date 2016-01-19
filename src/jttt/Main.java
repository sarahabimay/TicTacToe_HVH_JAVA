package jttt;

import jttt.UI.CommandLineUI;
import jttt.UI.ConsoleGameMaker;

import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI(
                new ConsoleGameMaker(),
                new BufferedInputStream(System.in),
                new OutputStreamWriter(System.out));
        ui.start();
    }
}
