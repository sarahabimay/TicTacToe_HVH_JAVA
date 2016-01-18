package jttt.UI;

import jttt.core.board.Board;
import jttt.core.board.Mark;

import java.util.function.IntPredicate;

public interface UserInterface {

    int displayGreetingRequest();

    int requestBoardDimension();

    int requestGameType();

    int requestNextPosition(Board board);

    int requestPlayAgain();

    void displayResult(Mark winner);

    boolean validate(int choiceFromInput, IntPredicate isValidChoice);

    boolean validGameType(int choice);

    boolean validateDimension(int dimension);

    boolean validBoardPosition(int oneIndexedPosition, Board board);

    boolean validReplayChoice(int instruction);

    boolean validateContinueChoice(int playOrQuit);

    void start();

    void displayBoardToUser(String boardForDisplay);

    void createNewGameFromOptions(int gameType, int defaultDimension);
}
