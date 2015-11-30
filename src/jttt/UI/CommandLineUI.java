package jttt.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.DisplayStyler;
import jttt.Core.Game;
import jttt.Core.Board.Mark;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

import java.io.*;
import java.util.function.IntPredicate;

public class CommandLineUI implements UserInterface {
    public String REPLAY_REQUEST = "Do you want to play again? Quit(1) or Replay(2) :\n";
    public String DIMENSION_REQUEST = "Please provide the dimensions of the board:\n";
    public String GAME_TYPE_REQUEST = "Human vs Human(1) or Human vs Computer(2) or Computer vs Human(3)?:\n";
    public String POSITION_REQUEST = "Please enter the position number for your next move:\n";
    public String DRAW_ANNOUNCE = "The game is a draw!";
    public String WINNER_ANNOUNCE = "We have a Winner! Player: %s\n";
    private Game game;
    private DisplayStyler styler;
    private BufferedReader readStream;
    private Writer writeStream;

    public CommandLineUI(Game game, InputStream inputStream, Writer writer) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = writer;
        this.game = game;
    }

    public CommandLineUI(Game game, DisplayStyler boardStyle, InputStream inputStream, Writer writer) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = writer;
        this.game = game;
        this.styler = boardStyle;
    }

//    public CommandLineUI(Game game, InputStream inputStream, Writer writer) {

//        String clear = "\033[H\033[2J";
//        formatConsoleDisplay(clear, writer);
//        formatConsoleDisplay("SOMETHING ELSE", writer);
//        formatConsoleDisplay(clear, writer);
//        formatConsoleDisplay("SOMETHING", writer);
//        formatConsoleDisplay(clear, writer);
//        String ANSI_RESET = "\u001B[0m";
//        String ANSI_RED = "\u001B[31m";
//        formatConsoleDisplay(ANSI_RED + "This text is red!" + ANSI_RESET,  writer);
//    }


    public void start() {
        boolean replay = true;
        while (replay) {
            createNewGame(requestGameType(), requestBoardDimension());
            displayBoard();
            playAllMoves();
            displayResult(game.findWinner());
            replay = playAgain();
        }
    }

    public int requestBoardDimension() {
        int dimension = 0;
        while (!validate(dimension, this::validateDimension)) {
            displayToOutput(DIMENSION_REQUEST);
            dimension = readInput();
        }
        clear();
        return dimension;
    }


    public int requestGameType() {
        int choice = -1;
        while (!validate(choice, this::validGameType)) {
            displayToOutput(GAME_TYPE_REQUEST);
            choice = readInput();
        }
        clear();
        return choice;
    }

    public int requestNextPosition() {
        int position = 0;
        while (!validate(position, this::validPosition)) {
            String prompt = POSITION_REQUEST;
            displayToOutput(prompt);
            position = readInput();
        }
        return position;
    }


    public void displayResult(Mark winner) {
        if (winner.isEmpty()) {
            announceDraw();
        } else {
            announceWinner(winner);
        }
    }

    public boolean playAgain() {
        if (requestPlayAgain()) {
            clear();
            int DEFAULT_DIMENSION = 3;
            int DEFAULT_GAME_TYPE = 1;
            game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAME_TYPE, new PlayerFactory());
            return true;
        }
        return false;
    }

    public boolean requestPlayAgain() {
        int instruction = 0;
        while (!validate(instruction, this::validInstruction)) {
            displayToOutput(REPLAY_REQUEST);
            instruction = readInput();
        }
        return doPlayAgain(instruction);
    }

    public String displayBoard() {
        clear();
        String output = styler.displayBoard(game.getBoard());
        displayToOutput(output+"\n");
        return output;
    }

    public boolean validate(int choiceFromInput, IntPredicate isValidChoice) {
        return isValidChoice.test(choiceFromInput);
    }

    public boolean validGameType(int choice) {
        return choice == 1 || choice == 2 || choice == 3;
    }

    public boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    public boolean validPosition(int position) {
        return position > 0;
    }

    public boolean validInstruction(int instruction) {
        return 0 < instruction && instruction < 3;
    }

    public void createNewGame(int gameType, int dimension) {
        game = new Game(
                new Board(dimension, new DisplayStyler()),
                gameType,
                new PlayerFactory());
    }

    private void playAllMoves() {
        while (!game.isGameOver()) {
            if (nextPlayerIsAI()) {
                game.playAIMove();
            } else {
                game.playMove(requestNextPosition());
            }
            displayBoard();
        }
    }

    private boolean nextPlayerIsAI() {
        return game.getNextPlayerType() == Player.Type.AI;
    }

    private void announceWinner(Mark winner) {
        displayToOutput(String.format(WINNER_ANNOUNCE, winner.toString()));
    }

    private void announceDraw() {
        displayToOutput(DRAW_ANNOUNCE);
    }

    private boolean doPlayAgain(int instruction) {
        return 2 == instruction;
    }

    private void displayToOutput(String messageToDisplay) {
        try {
            writeStream.write(messageToDisplay);
            writeStream.flush();
        } catch (IOException e) {
            System.out.println(String.format("IO Exception: %s", e.toString()));
        }
    }

    private void clear() {
        String message = "\033[H\033[2J";
        displayToOutput("\n" + message + "\n");
    }

    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }
}
