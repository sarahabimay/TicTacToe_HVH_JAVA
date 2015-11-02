import java.util.function.IntPredicate;

public interface UserInterface {
    Integer requestBoardSize();

    String requestPlayerTypes();

    Integer requestNextPosition();

    boolean requestPlayAgain();

    void displayResult(Counter winner);

    String displayBoard(Board board);

    boolean validate(Integer choiceFromInput, IntPredicate isValidChoice);

    void outputToUI(String format);

    boolean validatePlayerTypes(String choice);
}
