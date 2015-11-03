import java.util.function.IntPredicate;

public interface UserInterface {
    Integer requestBoardSize();

    String requestGameType();

    Integer requestNextPosition();

    boolean requestPlayAgain();

    void displayResult(Counter winner);

    String displayBoard(Board board);

    void outputToUI(String format);

    boolean validate(Integer choiceFromInput, IntPredicate isValidChoice);

    boolean validateGameType(String choice);
}
