import java.io.*;

public class UserInterface {
    private BufferedReader readStream;
    private PrintStream writeStream;

    public UserInterface() {
        this.readStream = new BufferedReader(new InputStreamReader(System.in));
        this.writeStream = new PrintStream(System.out);
    }

    public int requestBoardSize() {
        writeStream.println("Please provide the dimensions of the board:\n");
        return readInput();
    }

    public String requestNextPosition() {
        String prompt = "Please enter the position number for your next move:\n";
        writeStream.println(prompt);
        int nextPosition = readInput();
        // want to return this to caller, which will pass to the game
        return prompt;
    }

    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String displayBoard(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.cellValue(i), board);
        }
        writeStream.println(output);
        return output;
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
        return (index + 1) % board.getDimension() == 0;
    }
}
