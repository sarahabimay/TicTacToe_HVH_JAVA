package jttt.UI;

import jttt.Core.Mark;

import java.util.function.IntPredicate;

public interface UserInterface {

    int requestBoardDimension();

    int requestGameType();

    int requestNextPosition();

    boolean requestPlayAgain();

    void displayResult(Mark winner);

    boolean validate(int choiceFromInput, IntPredicate isValidChoice);

    boolean validGameType(int choice);

    boolean validateDimension(int dimension);

    boolean validPosition(int position);

    boolean validInstruction(int instruction);

    void start();
}
