package jttt.UI;

import jttt.Core.*;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

import java.io.*;
import java.util.function.IntPredicate;

public class CommandLineUI implements UserInterface {
    private Game game;
    private BufferedReader readStream;
    private PrintStream writeStream;

    public CommandLineUI(Game game, InputStream inputStream, PrintStream outputStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = outputStream;
        this.game = game;
    }

    public void start() {
        createNewGame(requestBoardSize(), requestGameType());
        playAllMoves();
        displayResult(game.findWinner());
        playAgain();
    }

    public int requestBoardSize() {
        int dimension = 0;
        while (!validate(dimension, this::validateDimension)) {
            writeStream.println("Please provide the dimensions of the board:\n");
            dimension = readInput();
        }
        return dimension;
    }

    public int requestGameType() {
        int choice = -1;
        while (!validate(choice, this::validGameType)) {
            writeStream.println("Human vs Human(1) or Human vs Computer(2) or Computer vs Human(3)?:\n");
            choice = readInput();
        }
        return choice;
    }

    public int requestNextPosition() {
        int position = 0;
        while (!validate(position, this::validPosition)) {
            String prompt = "Please enter the position number for your next move:\n";
            writeStream.println(prompt);
            position = readInput();
        }
        return position;
    }

    public boolean requestPlayAgain() {
        int instruction = 0;
        while (!validate(instruction, this::validInstruction)) {
            writeStream.println("Do you want to play again? Quit(1) or Replay(2) :\n");
            instruction = readInput();
        }
        return doPlayAgain(instruction);
    }

    public void displayResult(Mark winner) {
        if (winner.isEmpty()) {
            announceDraw();
        } else {
            announceWinner(winner);
        }
    }

    public String displayBoard() {
        String output = boardForDisplay(game.getBoard());
        writeStream.println(output);
        return output;
    }

    public String displayBoard(Board board) {
        String output = boardForDisplay(board);
        writeStream.println(output);
        return output;
    }

    public void printCurrentCounter(Mark currentMark) {
        writeStream.println(String.format("Board after %s's move: \n", currentMark.name()));
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

    private void playAgain() {
        if (requestPlayAgain()) {
            game = new Game(new PlayerFactory());
            start();
        }
    }

    public void createNewGame(int dimension, int gameType) {
        game = new Game(new PlayerFactory(), dimension, gameType);
    }

    private void playAllMoves() {
        while (!game.isGameOver()) {
            if (game.getNextPlayer().getPlayerType() == Player.Type.AI) {
                game.playMove();
            } else {
                game.playMove(requestNextPosition());
            }
            displayBoard();
        }
    }

    private void announceWinner(Mark winner) {
        writeStream.println(String.format("We have a Winner! Player: %s\n", winner.toString()));
    }

    private void announceDraw() {
        writeStream.println("The game is a draw!");
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

    private boolean doPlayAgain(int instruction) {
        return 2 == instruction;
    }

    private String boardForDisplay(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.findCounterAtIndex(i), board);
        }
        return output;
    }

    private String convertRowToString(int index, Mark cellValue, Board board) {
        String cellForDisplay = cellValue.counterForDisplay(index);
        String output = String.format("[%s]", cellForDisplay);
        if (isEndOfRow(index, board)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index, Board board) {
        return (index + 1) % calculateDimension(board) == 0;
    }

    private int calculateDimension(Board board) {
        return (int) Math.sqrt(board.boardSize());
    }
}
