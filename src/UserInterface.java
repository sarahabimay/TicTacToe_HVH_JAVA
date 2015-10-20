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
        return "Please enter the position number for your next move";
    }
    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
