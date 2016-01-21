package jttt.UI;

import jttt.core.board.Board;
import jttt.core.board.Mark;

public interface UserInterface {

    int displayGreetingRequest();

    int requestBoardDimension();

    int requestGameType();

    int requestNextPosition(Board board);

    int requestPlayAgain();

    void displayResult(Mark winner);

    void displayBoardToUser(String boardForDisplay);

    void start();
}
