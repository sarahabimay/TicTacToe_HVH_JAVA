import java.util.function.IntPredicate;

public interface UserInterface {
    int requestBoardSize();

    int requestGameType();

    int requestNextPosition();

    boolean requestPlayAgain();

    void displayResult(Counter winner);

    String displayBoard(Board board);

    boolean validate(Integer choiceFromInput, IntPredicate isValidChoice);

    void printCurrentCounter(Counter currentCounter);

    boolean validGameType(int choice);

    boolean validateDimension(int dimension);

    boolean validPosition(int position);

    boolean validInstruction(int instruction);
}
