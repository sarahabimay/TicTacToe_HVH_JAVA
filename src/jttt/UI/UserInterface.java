package jttt.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

import java.util.function.IntPredicate;

public interface UserInterface {

    int displayGreetingRequest();

    int requestBoardDimension();

    int requestGameType();

    int requestNextPosition(Board board);

    boolean requestPlayAgain();

    void displayResult(Mark winner);

    boolean validate(int choiceFromInput, IntPredicate isValidChoice);

    boolean validGameType(int choice);

    boolean validateDimension(int dimension);

    boolean validBoardPosition(int oneIndexedPosition, Board board);

    boolean validReplayChoice(int instruction);

    boolean validateContinueChoice(int playOrQuit);

    void start();
}
