import java.util.function.IntPredicate;

public interface UserInterface {
    Integer requestBoardSize();

    Integer requestGameType();

    Integer requestNextPosition();

    boolean requestPlayAgain();

    void displayResult(Counter winner);

    String displayBoard(Board board);

    void outputToUI(String format);

    boolean validate(Integer choiceFromInput, IntPredicate isValidChoice);

    void printCurrentCounter(Counter currentCounter);

    boolean validGameType(int choice);

    boolean validateDimension(int dimension);

    boolean validPosition(int position);

    boolean validInstruction(int instruction);
}
