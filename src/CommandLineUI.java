import java.io.*;
import java.util.function.IntPredicate;

public class CommandLineUI implements UserInterface {
    private BufferedReader readStream;
    private PrintStream writeStream;

    public CommandLineUI() {
        this.readStream = new BufferedReader(new InputStreamReader(System.in));
        this.writeStream = new PrintStream(System.out);
    }

    public Integer requestBoardSize() {
        Integer dimension = 0;
        while (!validate(dimension, this::validateDimension)) {
            writeStream.println("Please provide the dimensions of the board:\n");
            dimension = readInput();
        }
        return dimension;
    }

    public String requestPlayerTypes() {
        String choice = "";
        while (!validatePlayerTypes(choice)) {
            writeStream.println("Human vs Human(HVH) or Human vs Computer(HVC) or Computer vs Computer(CVH)?:\n");
            choice = readString();
        }
        return choice;
    }

    public Integer requestNextPosition() {
        Integer position = 0;
        while (!validate(position, this::validPosition)) {
            String prompt = "Please enter the position number for your next move:\n";
            writeStream.println(prompt);
            position = readInput();
        }
        return position;
    }

    public boolean requestPlayAgain() {
        Integer instruction = 0;
        while (!validate(instruction, this::validInstruction)) {
            writeStream.println("Do you want to play again? Quit(1) or Replay(2) :\n");
            instruction = readInput();
        }
        return doPlayAgain(instruction);
    }

    public void outputToUI(String output) {
        writeStream.println(output);
    }

    public void displayResult(Counter winner) {
        if (winner.isEmpty()) {
            announceDraw();
        } else {
            announceWinner(winner);
        }
    }

    public String displayBoard(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i), board);
        }
        writeStream.println(output);
        return output;
    }

    public boolean validate(Integer choiceFromInput, IntPredicate isValidChoice) {
        return choiceFromInput != null && isValidChoice.test(choiceFromInput);
    }

    @Override
    public boolean validatePlayerTypes(String choice) {
        return PlayerFactory.validPlayerTypes(choice);
    }

    private void announceWinner(Counter winner) {
        writeStream.println(String.format("We have a Winner! Player: %s\n", winner.toString()));
    }

    private void announceDraw() {
        writeStream.println("The game is a draw!");
    }

    private String readString() {
        try {
            return readStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            return null;
        }
        return 0;
    }


    private boolean doPlayAgain(Integer instruction) {
        return 2 == instruction;
    }

    private boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    private boolean validPosition(int position) {
        return position > 0;
    }

    private boolean validInstruction(int instruction) {
        return 0 < instruction && instruction < 3;
    }

    private String convertRowToString(int index, Counter cellValue, Board board) {
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
